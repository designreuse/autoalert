/**
 * 
 */
package info.geekinaction.autoalert.jmx;

import static info.geekinaction.autoalert.common.CommonConstants.ISO_TIMESTAMP_FORMAT;
import info.geekinaction.autoalert.common.util.DateUtil;
import info.geekinaction.autoalert.model.domain.Pair;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

/**
 * Instrumentation MBean implementation.
 * 
 * @author lcsontos
 *
 */
public class AutoAlertInstrumentationImpl extends MBeanSupport implements IAutoAlertInstrumentation {
	
	private static final Logger logger = Logger.getLogger(AutoAlertInstrumentationImpl.class);

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

	// Uptime.
	private String uptime;
	
	/**
	 * 
	 */
	public AutoAlertInstrumentationImpl() {
		ejbCallCounterMap = new HashMap<String, Integer>();
		ejbRuntimeStatsMap = new HashMap<String, Double>();
		statisticsQueue = new ConcurrentLinkedQueue<RuntimeInfo>();
		statisticsUpdaterThread = this.new StatisticsUpdaterThread();
	}

	/**
	 * @see IAutoAlertInstrumentation#getUptime()
	 */
	@Override
	public String getUptime() {
		return uptime;
	}
	
	/**
	 * @see IAutoAlertInstrumentation#instrumentMethod(String, long)
	 */
	@Override
	public void instrumentMethod(String methodName, long elapsedTime) {
		RuntimeInfo runtimeInfo = new RuntimeInfo(methodName, elapsedTime);
		statisticsQueue.add(runtimeInfo);
	}

	/**
	 * @see IAutoAlertInstrumentation#getEJBMethods()
	 */
	@Override
	public synchronized Set<String> getEJBMethods() {
		updateStatistics();
		return Collections.unmodifiableSet(ejbCallCounterMap.keySet());
	}

	/**
	 * @see IAutoAlertInstrumentation#getCallCountForMethod(String)
	 */
	@Override
	public int getCallCountForMethod(String methodName) {
		updateStatistics();
		return ejbCallCounterMap.get(methodName);
	}

	/**
	 * @see IAutoAlertInstrumentation#getRuntimeStatForMethod(String)
	 */
	@Override
	public double getRuntimeStatForMethod(String methodName) {
		updateStatistics();
		return ejbRuntimeStatsMap.get(methodName);
	}
	
	/**
	 * INTERNAL USE ONLY
	 * 
	 * Runtime statistics are first gathered to a queue
	 * which is frequently polled for new data by a separate thread (see below).
	 * 
	 *  This method is called by <code>StatisticsUpdaterThread</code> or
	 *  when getEJBMethods(), getCallCountForMethod(), getRuntimeStatForMethod() are executed.
	 *  
	 *  @see AutoAlertInstrumentationImpl.StatisticsUpdaterThread
	 *  @return True is there were new data the queue on the course of this execution.
	 * 
	 */
	private boolean updateStatistics() {
		boolean updated = false;
		
		while (!statisticsQueue.isEmpty()) {
			
			// Poll the queue for new data
			RuntimeInfo runtimeInfo = statisticsQueue.poll();
			if (runtimeInfo == null) {
				break;
			}
			
			// This block should be run synchronized to maintain consistency,
			// since multiple HashMaps are tocuhed.
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
	 * Gathers new statistics frequently.
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
						Thread.yield(); // Let other threads with higher priority run. 
						Thread.sleep(100); // Sleep 
					} catch (InterruptedException e) {
						logger.debug(e.getMessage(), e);
					}
				}
			} while (!stopStatisticsUpdaterThread);
		}
	}
	
	/////////////////////////////////////
	///// MBEAN life-cycle methods. /////
	/////////////////////////////////////
	
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
	
}
