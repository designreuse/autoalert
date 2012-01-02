/**
 * 
 */
package info.geekinaction.autoalert.rpc;

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
import info.geekinaction.autoalert.model.exception.AutoAlertException;
import info.geekinaction.autoalert.model.exception.AutoAlertSystemException;
import info.geekinaction.autoalert.model.service.IAutoAlertModel;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author lcsontos
 * 
 */
public final class AutoAlertModelDelegate extends RemoteServiceServlet implements IAutoAlertModel {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@EJB(name = "AutoAlertModel")
	private IAutoAlertModel model;

	/**
	 * 
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		schedule();
		logger.info("Initialized.");
	}
	
	/**
	 * 
	 */
	@Override
	public void destroy() {
		super.destroy();
		logger.info("Destroyed.");
	}

	/**
	 * 
	 */
	public List<Datafile> findDatafiles(boolean alertsOnly) throws AutoAlertException {
		List<Datafile> retval = null;
		try {
			retval = model.findDatafiles(alertsOnly);
		} catch (RuntimeException e) {
			handleError(e);
		}
		return retval;
	}

	/**
	 * 
	 */
	public List<Tablespace> findTablespaces(boolean alertsOnly) throws AutoAlertException {
		List<Tablespace> retval = null;
		try {
			retval = model.findTablespaces(alertsOnly);
		} catch (RuntimeException e) {
			handleError(e);
		}
		return retval;
	}

	/**
	 * 
	 */
	public Map<ParameterName, Parameter> findParameters() throws AutoAlertException {
		Map<ParameterName, Parameter> retval = null;
		try {
			retval = model.findParameters();
		} catch (RuntimeException e) {
			handleError(e);
		}
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findDatabase()
	 */
	public Database findDatabase() throws AutoAlertException {
		Database retval = null;
		try {
			retval = model.findDatabase();
		} catch (RuntimeException e) {
			handleError(e);
		}
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findInstanceCpuUsage(boolean)
	 */
	public List<InstanceCpuUsage> findInstanceCpuUsage(boolean alertsOnly) throws AutoAlertException {
		List<InstanceCpuUsage> retval = null;
		try {
			retval = model.findInstanceCpuUsage(alertsOnly);
		} catch (RuntimeException e) {
			handleError(e);
		}
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findInstanceIoUsage(boolean)
	 */
	public List<InstanceIoUsage> findInstanceIoUsage(boolean alertsOnly) throws AutoAlertException {
		List<InstanceIoUsage> retval = null;
		try {
			retval = model.findInstanceIoUsage(alertsOnly);
		} catch (RuntimeException e) {
			handleError(e);
		}
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findSession()
	 */
	public List<Session> findSession() throws AutoAlertException {
		List<Session> retval = null;
		try {
			retval = model.findSession();
		} catch (RuntimeException e) {
			handleError(e);
		}
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findSessionCpuUsage()
	 */
	public List<SessionCpuUsage> findSessionCpuUsage() throws AutoAlertException {
		List<SessionCpuUsage> retval = null;
		try {
			retval = model.findSessionCpuUsage();
		} catch (RuntimeException e) {
			handleError(e);
		}
		return retval;
	}

	/**
	 * @see info.geekinaction.autoalert.model.service.IAutoAlertModel#findSessionIoUsage()
	 */
	public List<SessionIoUsage> findSessionIoUsage() throws AutoAlertException {
		List<SessionIoUsage> retval = null;
		try {
			retval = model.findSessionIoUsage();
		} catch (RuntimeException e) {
			handleError(e);
		}
		return retval;
	}

	/**
	 * 
	 */
	public void schedule() {
		try {
			model.schedule();
			logger.info("Scheduler initialized.");
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param e
	 */
	private void handleError(RuntimeException e) throws AutoAlertException {
		AutoAlertSystemException systemException = null;
		
		// Mostly a happens due to EJB misconfiguration
		if (e instanceof NullPointerException) {
			String message = (model == null) ? "EJB Injection failed, review configuration." : e.getMessage();
			systemException = new AutoAlertSystemException(message, e);
		// 
		} else if (e instanceof EJBException) {
			Exception causedByException = ((EJBException) e).getCausedByException();
			/* 
			 * Rethrow the cause exception as AutoAlertSystemException,
			 * because the cause might not be serializable by GWT.  
			 */
			systemException = new AutoAlertSystemException(causedByException.getCause());
		} else {
			String message = "Unexpected error: " + e.getMessage();
			systemException = new AutoAlertSystemException(message, e);
		}
		
		logger.error(systemException.getMessage(), systemException);
		throw systemException;
	}

}
