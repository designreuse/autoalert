/**
 * 
 */
package info.geekinaction.autoalert.jmx;

import static info.geekinaction.autoalert.ejb.EJBConstants.AUTOALERT_MODEL_JNDI;
import info.geekinaction.autoalert.common.util.CachingServiceLocator;
import info.geekinaction.autoalert.model.service.IAutoAlertModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.log4j.Logger;

/**
 * @author lcsontos
 *
 */
public class AutoAlertManagementImpl implements IAutoAlertManagement, MBeanRegistration {
	
	private static final Logger logger = Logger.getLogger(AutoAlertManagementImpl.class);

	// Call counters.
	private final Map<String, AtomicInteger> ejbCallCounterMap;
	
	// Counters of runtime statistics.
	private final Map<String, AtomicLong> ejbRuntimeStatsMap;

	// Incoming runtime statistics.
	private final Queue<Object> statisticsQueue;

	// Local EJB proxy.
	private final IAutoAlertModel model;
	
	// Thread for updating runtime statistics.
	private final Thread statisticsUpdaterThread;
	
	// Indicates if the updater thread should be stopped.
	private volatile boolean stopStatisticsUpdaterThread = false;
	
	// This is my ObjectName.
	private ObjectName name;
	
	/**
	 * 
	 */
	public AutoAlertManagementImpl() {
		ejbCallCounterMap = new HashMap<String, AtomicInteger>();
		ejbRuntimeStatsMap = new HashMap<String, AtomicLong>();
		statisticsQueue = new ConcurrentLinkedQueue<Object>();
		model = CachingServiceLocator.lookupStatelessEJB(AUTOALERT_MODEL_JNDI, IAutoAlertModel.class);
		statisticsUpdaterThread = this.new StatisticsUpdaterThread();
	}

	/**
	 *
	 */
	@Override
	public boolean reloadConfiguration() {
		try {
			System.out.println("MODEL: " + model);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getUptime() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void instrumentMethod(String methodName, long elapsedTime) {
	// TODO
		
	}

	@Override
	public List<String> getEJBMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCallCountForMethod(String methodName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getRuntimeStatForMethod(String methodName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 */
	private boolean updateStatistics() {
		boolean updated = false;
		while (!statisticsQueue.isEmpty()) {
			synchronized (this) {
				updated = true;
				// TODO
			}
		}
		return updated;
	}
	
	/**
	 * 
	 * @author lcsontos
	 *
	 */
	private final class StatisticsUpdaterThread extends Thread {
		@Override
		public void run() {
			do {
				if (!stopStatisticsUpdaterThread && !updateStatistics()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						logger.debug(e.getMessage(), e);
					}
				}
			} while (!stopStatisticsUpdaterThread);
		}
	}

	/**
	 * @see javax.management.MBeanRegistration#preRegister(javax.management.MBeanServer, javax.management.ObjectName)
	 */
	@Override
	public ObjectName preRegister(MBeanServer server, ObjectName name) throws Exception {
		logger.info("MBean " + name.toString() + " registration has started.");
		this.name = name;
		return name;
	}

	/**
	 * @see javax.management.MBeanRegistration#postRegister(java.lang.Boolean)
	 */
	@Override
	public void postRegister(Boolean registrationDone) {
		try {
			if (registrationDone) {
				statisticsUpdaterThread.start();
			} else {
				throw new IllegalStateException("MBean server returned with error, see log for details.");
			}
			logger.info("MBean " + name.toString() + " registration has finished.");
		} catch (Exception e) {
			logger.error("MBean " + name.toString() + " registration has failed.");
		}
	}

	/**
	 * @see javax.management.MBeanRegistration#preDeregister()
	 */
	@Override
	public void preDeregister() throws Exception {
		try {
			stopStatisticsUpdaterThread = true;
			ejbCallCounterMap.clear();
			ejbRuntimeStatsMap.clear();
			logger.info("MBean " + name.toString() + " deregistration has started.");
		} catch (Exception e) {
			logger.error("MBean " + name.toString() + " deregistration has failed; reason: " + e.getMessage(), e);
		}
	}

	/**
	 * @see javax.management.MBeanRegistration#postDeregister()
	 */
	@Override
	public void postDeregister() {
		logger.info("MBean " + name.toString() + " deregistration has finished.");
	}
	
}
