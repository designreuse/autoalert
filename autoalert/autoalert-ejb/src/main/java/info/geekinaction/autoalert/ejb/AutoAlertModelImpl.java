/**
 * 
 */
package info.geekinaction.autoalert.ejb;

import static info.geekinaction.autoalert.common.CommonConstants.ISO_TIMESTAMP_FORMAT;

import static info.geekinaction.autoalert.ejb.AutoAlertQuery.FIND_DATABASE;
import static info.geekinaction.autoalert.ejb.AutoAlertQuery.FIND_DATAFILES;
import static info.geekinaction.autoalert.ejb.AutoAlertQuery.FIND_INSTANCE_CPU_USAGE;
import static info.geekinaction.autoalert.ejb.AutoAlertQuery.FIND_INSTANCE_IO_USAGE;
import static info.geekinaction.autoalert.ejb.AutoAlertQuery.FIND_SESSION;
import static info.geekinaction.autoalert.ejb.AutoAlertQuery.FIND_SESSION_CPU_USAGE;
import static info.geekinaction.autoalert.ejb.AutoAlertQuery.FIND_SESSION_IO_USAGE;
import static info.geekinaction.autoalert.ejb.AutoAlertQuery.FIND_TABLESPACES;

import static info.geekinaction.autoalert.ejb.EJBConstants.AUTOALERT_MODEL_JNDI;
import static info.geekinaction.autoalert.ejb.EJBConstants.AUTOALERT_MODEL_NAME;
import static info.geekinaction.autoalert.ejb.EJBConstants.TIMER_NAME;
import static info.geekinaction.autoalert.ejb.EJBConstants.TIMER_INTERVAL;
import static info.geekinaction.autoalert.ejb.EJBConstants.TIME_SLICE;
import static info.geekinaction.autoalert.ejb.EJBConstants.TIME_SLICE_MSEC;
import static info.geekinaction.autoalert.ejb.EJBConstants.TIME_SLICE_SEC;

import static info.geekinaction.autoalert.model.domain.ParameterName.AUTOALERT_MAIL_FROM;
import static info.geekinaction.autoalert.model.domain.ParameterName.AUTOALERT_RCPT_TO;
import static info.geekinaction.autoalert.model.domain.ParameterName.AUTOALERT_SUBJECT;

import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import info.geekinaction.autoalert.common.AbstractBusinessObject;
import info.geekinaction.autoalert.common.util.DateUtil;
import info.geekinaction.autoalert.common.util.LogUtil;
import info.geekinaction.autoalert.jmx.IAutoAlertManagement;
import info.geekinaction.autoalert.model.domain.Database;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.model.domain.Parameter;
import info.geekinaction.autoalert.model.domain.ParameterName;
import info.geekinaction.autoalert.model.domain.Session;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.model.incident.AutoAlertIncident;
import info.geekinaction.autoalert.model.incident.AutoAlertIncidentListener;
import info.geekinaction.autoalert.model.incident.IAutoAlertIncidentHandler;
import info.geekinaction.autoalert.model.service.IAutoAlertModel;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author lcsontos
 * 
 */
@Stateless(name = AUTOALERT_MODEL_NAME, mappedName = AUTOALERT_MODEL_JNDI)
@Local({ IAutoAlertModel.class, IAutoAlertManagement.class, IAutoAlertIncidentHandler.class })
@Interceptors({ AutoAlertAuditInterceptor.class })
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AutoAlertModelImpl extends AbstractBusinessObject implements IAutoAlertModel, IAutoAlertManagement, IAutoAlertIncidentHandler {
	
	private static final Logger logger = Logger.getLogger(AutoAlertModelImpl.class);

	@PersistenceContext(unitName = "AutoAlertPU")
	private EntityManager em;

	// @Resource(name = "mail/aaSession")
	private javax.mail.Session mailSession;
	
	///// EJB lifecycle methods. /////
	
	@PostConstruct
	@Override
	public void init() {
		logger.debug(this.toString() + " initialized.");
	}
	
	@PreDestroy
	@Override
	public void destroy() {
		logger.debug(this.toString() + " destroyed.");
	}
	
	/**
	 * 
	 */
	@Resource
	@Override
	protected void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Map<ParameterName, Parameter> findParameters() {
		Query query = createQuery(AutoAlertQuery.FIND_PARAMETERS);
		List<Parameter> params = (List<Parameter>) query.getResultList();
		
		// Convert to proper collection format.
		Map<ParameterName, Parameter> retval = new HashMap<ParameterName, Parameter>();
		for (Parameter param : params) {
			
			// Name of the current parameter.
			ParameterName pname = ParameterName.valueOf(param.getParamName());
			
			retval.put(pname, param);
		}
		
		params.clear();
		
		return retval;
	}
	
	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findDatabase()
	 */
	public Database findDatabase() {
		Query query = createQuery(FIND_DATABASE);
		Database database = (Database) query.getSingleResult();
		return database;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findDatafiles(boolean)
	 */
	public List<Datafile> findDatafiles(boolean alertsOnly) {
		int alertParam = alertsOnly ? 0 : -1;

		Query query = createQuery(FIND_DATAFILES);
		query.setParameter(1, alertParam);

		List<Datafile> retval = (List<Datafile>) query.getResultList();
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findInstanceCpuUsage(boolean)
	 */
	public List<InstanceCpuUsage> findInstanceCpuUsage(boolean alertsOnly) {
		int alertParam = alertsOnly ? 0 : -1;

		Query query = createQuery(FIND_INSTANCE_CPU_USAGE);
		query.setParameter(1, alertParam);
		query.setParameter(2, alertParam);

		List<InstanceCpuUsage> retval = (List<InstanceCpuUsage>) query.getResultList();
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findInstanceIoUsage(boolean)
	 */
	public List<InstanceIoUsage> findInstanceIoUsage(boolean alertsOnly) {
		int alertParam = alertsOnly ? 0 : -1;

		Query query = createQuery(FIND_INSTANCE_IO_USAGE);
		query.setParameter(1, alertParam);
		query.setParameter(2, alertParam);

		List<InstanceIoUsage> retval = (List<InstanceIoUsage>) query.getResultList();
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findSession()
	 */
	public List<Session> findSession() {
		Query query = createQuery(FIND_SESSION);
		List<Session> retval = (List<Session>) query.getResultList();
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findSessionCpuUsage()
	 */
	public List<SessionCpuUsage> findSessionCpuUsage() {
		Query query = createQuery(FIND_SESSION_CPU_USAGE);
		List<SessionCpuUsage> retval = (List<SessionCpuUsage>) query.getResultList();
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findSessionIoUsage()
	 */
	public List<SessionIoUsage> findSessionIoUsage() {
		Query query = createQuery(FIND_SESSION_IO_USAGE);
		List<SessionIoUsage> retval = (List<SessionIoUsage>) query.getResultList();
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findTablespaces(boolean)
	 */
	public List<Tablespace> findTablespaces(boolean alertsOnly) {
		int alertParam = alertsOnly ? 0 : -1;

		Query query = createQuery(FIND_TABLESPACES);
		query.setParameter(1, alertParam);

		List<Tablespace> retval = (List<Tablespace>) query.getResultList();
		return retval;
	}
	
	/**
	 * 
	 */
	@Override
	public void reloadConfiguration() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 */
	@Override
	public String getParameter(String paramName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public void setParameter(String paramName, String value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean storeIncident(AutoAlertIncident incident) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -4);

		int checkSum = new AutoAlertIncidentListener().createCheckSum(incident);
		Query query = createQuery(AutoAlertQuery.COUNT_INCIDENTS);
		query.setParameter(1, checkSum); // Checksum
		query.setParameter(2, calendar.getTime());
		
		int result = ((Number) query.getSingleResult()).intValue();
		boolean similarIncidentExists = result > 0;
		
		if (!similarIncidentExists) {
			em.persist(incident);
		}
		
		return similarIncidentExists;
	}
	
	/**
	 * 
	 * @param incident
	 * @return
	 */
	private boolean storeIncident0(AutoAlertIncident incident) {
		IAutoAlertIncidentHandler handler = sessionContext.getBusinessObject(IAutoAlertIncidentHandler.class);
		return handler.storeIncident(incident);
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	private Query createQuery(AutoAlertQuery query) {
		String queryName = query.getQueryName();
		return em.createNamedQuery(queryName);

	}
	
	@SuppressWarnings("unchecked")
	private Timer findTimer() {
		TimerService timerService = sessionContext.getTimerService();
		Collection<Timer> timers = (Collection<Timer>) timerService.getTimers();
		for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {
			Timer timer = iterator.next();
			Serializable info = timer.getInfo();
			if (info.equals(TIMER_NAME)) {
				return timer;
			}
		}
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public void startScheduler() {
		TimerService timerService = sessionContext.getTimerService();
		
		// Find a former timer first
		if (findTimer() != null) {
			LogUtil.log(this, Level.WARN, "startScheduler(): Timer {0} has already been scheduled.", new Object[] { TIMER_NAME });
			return;
		}

		// Start time
		Calendar startTime = Calendar.getInstance();

		/*
		 * Give the RDBMS 5 minutes to gather its internal statistics for the
		 * first time. Thereafter they will be check in every minute.
		 */
		int min = startTime.get(MINUTE);
		min = (min / TIME_SLICE) * TIME_SLICE;
		startTime.set(MINUTE, min);
		startTime.add(MINUTE, TIME_SLICE + 1);
		startTime.set(SECOND, TIME_SLICE_SEC);
		startTime.set(MILLISECOND, TIME_SLICE_MSEC);

		// Create timer
		timerService.createTimer(startTime.getTime(), TIMER_INTERVAL, TIMER_NAME);

		Object[] msgParams = new Object[] { DateUtil.toChar(startTime.getTime(), ISO_TIMESTAMP_FORMAT), new Integer(TIMER_INTERVAL) };
		LogUtil.log(this, Level.INFO, "startScheduler(): Timer has been set. Start time: {0}, interval: {1} msec.", msgParams);
	}
	
	/**
	 * 
	 */
	@Override
	public void stopScheduler() {
		Timer timer = findTimer();
		if (timer == null) {
			LogUtil.log(this, Level.WARN, "stopScheduler(): Timer {0} has not been found.", new Object[] { TIMER_NAME });
			return;
		}
		timer.cancel();
		LogUtil.log(this, Level.INFO, "stopScheduler(): Timer {0} has already been canceled.", new Object[] { TIMER_NAME });
	}
	
	/**
	 * 
	 */
	@Override
	public void triggerScheduler() {
		Timer timer = findTimer();
		if (timer == null) {
			LogUtil.log(this, Level.WARN, "triggerScheduler(): Timer {0} has not been found.", new Object[] { TIMER_NAME });
			return;
		}
		timerHandle(timer);
		LogUtil.log(this, Level.INFO, "triggerScheduler(): Timer {0} has been manually triggered.", new Object[] { TIMER_NAME });
	}

	/**
	 * 
	 * @param timer
	 */
	@Timeout
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void timerHandle(Timer timer) {

		try {
			// Log next timeout
			Date next = timer.getNextTimeout();
			LogUtil.log(this, Level.INFO, "timerHandle(): Timer {1} next expiration will be: {0}.", new Object[] { TIMER_NAME, DateUtil.toChar(next, ISO_TIMESTAMP_FORMAT) });

			//////////////////////
			// Check for alerts.
			//////////////////////

			// Info about RDBMS instance
			Database database = findDatabase();

			// Storage
			List<Tablespace> tablespaces = findTablespaces(true);
			List<Datafile> datafiles = findDatafiles(true);

			// Resources
			List<InstanceCpuUsage> cpuUsageList = findInstanceCpuUsage(true);
			List<InstanceIoUsage> ioUsageList = findInstanceIoUsage(true);
			InstanceCpuUsage cpuUsage = cpuUsageList.size() > 0 ? cpuUsageList.get(0) : null;
			InstanceIoUsage ioUsage = ioUsageList.size() > 0 ? ioUsageList.get(0) : null;

			// Assable alert message
			if (tablespaces.size() > 0 || cpuUsage != null || ioUsage != null) {
				
				AutoAlertIncident autoAlertIncident = new AutoAlertIncident(database, tablespaces, datafiles, cpuUsage, ioUsage);
				
				boolean similarIncidentExists = storeIncident0(autoAlertIncident);
				if (!similarIncidentExists) {
					// Message body
					// String message = VelocityHelper.initVelocity().createMessage(incident);

					// Get sender, recipients and subject
					Map<ParameterName, Parameter> parameters = findParameters();
					String from = Parameter.getParameterAsString(parameters, AUTOALERT_MAIL_FROM);
					String rcptCsv = Parameter.getParameterAsString(parameters, AUTOALERT_RCPT_TO);
					String[] rcpts = rcptCsv.split(",");
					String _subject = Parameter.getParameterAsString(parameters, AUTOALERT_SUBJECT);

					/*
					 * Subject
					 * 
					 *  [SIDNAME] Automatic Alert
					 *  
					 */
					StringBuilder subject = new StringBuilder();
					subject.append('['); subject.append(database.getDbUniqueName()); subject.append(']');
					subject.append(' ');
					subject.append(_subject);
					
					// Send alert message
					// MailUtil.sendMessage(mailSession, from, rcpts, subject.toString(), message);				
				}
				

			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
