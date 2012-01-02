/**
 * 
 */
package info.geekinaction.autoalert.jmx;

import static info.geekinaction.autoalert.common.CommonConstants.ISO_TIMESTAMP_FORMAT;
import static info.geekinaction.autoalert.ejb.EJBConstants.AUTOALERT_MODEL_JNDI;

import info.geekinaction.autoalert.common.util.CachingServiceLocator;
import info.geekinaction.autoalert.common.util.DateUtil;
import info.geekinaction.autoalert.model.domain.Pair;
import info.geekinaction.autoalert.model.service.IAutoAlertModel;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

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
	private final Map<String, Integer> ejbCallCounterMap;
	
	// Counters of runtime statistics.
	private final Map<String, Double> ejbRuntimeStatsMap;

	// Incoming runtime statistics.
	private final Queue<RuntimeInfo> statisticsQueue;

	// Thread for updating runtime statistics.
	private final Thread statisticsUpdaterThread;
	
	// Indicates if the updater thread should be stopped.
	private volatile boolean stopStatisticsUpdaterThread = false;

	// Local EJB proxy.
	private IAutoAlertModel model;

	// This is my ObjectName.
	private ObjectName name;
	
	// Uptime.
	private String uptime;
	
	/**
	 * 
	 */
	public AutoAlertManagementImpl() {
		ejbCallCounterMap = new HashMap<String, Integer>();
		ejbRuntimeStatsMap = new HashMap<String, Double>();
		statisticsQueue = new ConcurrentLinkedQueue<RuntimeInfo>();
		statisticsUpdaterThread = this.new StatisticsUpdaterThread();
	}

	/**
	 *
	 */
	@Override
	public boolean reloadConfiguration() {
		try {
			model.reloadConfiguration();
			logger.info("Configuration reloaded successfully.");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 
	 */
	@Override
	public String getUptime() {
		return uptime;
	}
	
	/**
	 * 
	 */
	@Override
	public void instrumentMethod(String methodName, long elapsedTime) {
		RuntimeInfo runtimeInfo = new RuntimeInfo(methodName, elapsedTime);
		statisticsQueue.add(runtimeInfo);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public synchronized Set<String> getEJBMethods() {
		updateStatistics();
		return Collections.unmodifiableSet(ejbCallCounterMap.keySet());
	}

	/**
	 * 
	 * @param methodName
	 * @return
	 */
	@Override
	public int getCallCountForMethod(String methodName) {
		updateStatistics();
		return ejbCallCounterMap.get(methodName);
	}

	/**
	 * 
	 * @param methodName
	 * @return
	 */
	@Override
	public double getRuntimeStatForMethod(String methodName) {
		updateStatistics();
		return ejbRuntimeStatsMap.get(methodName);
	}
	
	private IAutoAlertModel getAutoAlertModel() {
		if (model == null) {
			model = CachingServiceLocator.lookupStatelessEJB(AUTOALERT_MODEL_JNDI, IAutoAlertModel.class);
		}
		return model;
	}
	
	/**
	 * 
	 */
	private boolean updateStatistics() {
		boolean updated = false;
		while (!statisticsQueue.isEmpty()) {
			RuntimeInfo runtimeInfo = statisticsQueue.remove();
			
			synchronized (this) {
				updated = true;
				
				String methodName = runtimeInfo.getMethodName();
				Long elapsedTime = runtimeInfo.getElapsedTime();
				int currentCount = ejbCallCounterMap.containsKey(methodName) ? ejbCallCounterMap.get(methodName) : 0;
				double currentElapsedTime = ejbRuntimeStatsMap.containsKey(methodName) ? ejbRuntimeStatsMap.get(methodName) : 0d;
				
				int newCount = currentCount + 1;
				double newElapsedTime = ((currentElapsedTime * currentCount) + elapsedTime.doubleValue()) / newCount;
				
				ejbCallCounterMap.put(methodName, newCount);
				ejbRuntimeStatsMap.put(methodName, newElapsedTime);
			}
			
		}
		return updated;
	}
	
	/**
	 * 
	 * @author lcsontos
	 *
	 */
	private static class RuntimeInfo extends Pair<String, Long> {
		
		public RuntimeInfo(String methodName, Long elapsedTime) {
			setMethodName(methodName);
			setElapsedTime(elapsedTime);
		}
		
		public void setMethodName(String methodName) {
			setPart1(methodName);
		}
		
		public String getMethodName() {
			return getPart1();
		}
		
		public void setElapsedTime(Long elapsedTime) {
			setPart2(elapsedTime);
		}
		
		public Long getElapsedTime() {
			return getPart2();
		}
		
	}
	
	/**
	 * 
	 * @author lcsontos
	 *
	 */
	private class StatisticsUpdaterThread extends Thread {
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
				Date _uptime = DateUtil.currentTime();
				uptime = DateUtil.toChar(_uptime, ISO_TIMESTAMP_FORMAT);
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
