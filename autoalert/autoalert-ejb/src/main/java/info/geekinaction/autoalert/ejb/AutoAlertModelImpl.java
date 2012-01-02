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
import info.geekinaction.autoalert.mail.AlertMessage;
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
import info.geekinaction.autoalert.model.service.IAutoAlertModel;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * @author lcsontos
 * 
 */
@Stateless(name = AUTOALERT_MODEL_NAME, mappedName = AUTOALERT_MODEL_JNDI)
@Interceptors({ AutoAlertModelInterceptor.class })
public class AutoAlertModelImpl extends AbstractBusinessObject implements IAutoAlertModel {
	
	private static final Logger logger = Logger.getLogger(AutoAlertModelImpl.class);

	/**
	 * Measured time slice in minutes
	 */
	private static final int TIME_SLICE = 5;

	/**
	 * 
	 */
	private static final int TIME_SLICE_SEC = 0;

	/**
	 * 
	 */
	private static final int TIME_SLICE_MSEC = 0;

	/**
	 * Timeout interval (5min)
	 */
	private static final int INTERVAL = 1 * 60 * 1000;

	@PersistenceContext(unitName = "AutoAlertPU")
	private EntityManager em;

	//private SessionContext sessionContext;

	// @Resource(name = "mail/aaSession")
	private javax.mail.Session mailSession;
	
	private IAutoAlertManagement mbean;
	
	///// EJB lifecycle methods. /////
	
	@PostConstruct
	@Override
	public void init() {

	}
	
	@PreDestroy
	@Override
	public void destroy() {
		try {
			// TODO Implement destroy() here.
			logger.info("Destroy successful.");
		} catch (Exception e) {
			logger.error("Error during deinitialization; reason: " + e.getMessage(), e);
		}	
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

		List<InstanceCpuUsage> retval = (List<InstanceCpuUsage>) query
				.getResultList();
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

		List<InstanceIoUsage> retval = (List<InstanceIoUsage>) query
				.getResultList();
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
		List<SessionCpuUsage> retval = (List<SessionCpuUsage>) query
				.getResultList();
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
	
	@Override
	public void reloadConfiguration() {
		// TODO Auto-generated method stub
		
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

	/**
	 * 
	 */
	public void schedule() {
		TimerService timerService = sessionContext.getTimerService();

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
		timerService.createTimer(Calendar.getInstance().getTime(), INTERVAL, null);

		LogUtil.log(this, Level.INFO,
				"Timer has been set. Start time: {0}, interval: {1} msec.",
				new Object[] {
						DateUtil.toChar(startTime.getTime(),
								ISO_TIMESTAMP_FORMAT), new Integer(INTERVAL) });

	}

	/**
	 * 
	 * @param timer
	 */
	@Timeout
	public void timerHandle(Timer timer) {

		// Log next timeout
		Date next = timer.getNextTimeout();
		LogUtil.log(this, Level.INFO, "Timer expired. Next time will be: {0}",
				new Object[] { DateUtil.toChar(next, ISO_TIMESTAMP_FORMAT) });

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
		InstanceCpuUsage cpuUsage = cpuUsageList.size() > 0 ? cpuUsageList
				.get(0) : null;
		InstanceIoUsage ioUsage = ioUsageList.size() > 0 ? ioUsageList.get(0)
				: null;

		// Assable alert message
		if (tablespaces.size() > 0 || cpuUsage != null || ioUsage != null) {
			AlertMessage alertMessage = new AlertMessage(database, tablespaces,
					datafiles, cpuUsage, ioUsage);
			
			// Message body
			String message = alertMessage.toString();

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
			AlertMessage.sendMessage(mailSession, from, rcpts, subject.toString(), message);
		}

	}

}
