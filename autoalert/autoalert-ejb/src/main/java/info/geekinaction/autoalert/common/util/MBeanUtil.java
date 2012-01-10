/**
 * 
 */
package info.geekinaction.autoalert.common.util;

import static info.geekinaction.autoalert.common.CommonConstants.MBEAN_DOMAIN_NAME;

import java.lang.management.ManagementFactory;

import javax.management.JMException;
import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * 
 * Utility class for handling MBeans (MXBeans to be more specific).
 * 
 * @author lcsontos
 *
 */
public final class MBeanUtil {

	private static MBeanServer platformMBeanServer;
	
	/**
	 * 
	 */
	private MBeanUtil() { }
	

	/**
	 * Constructs an ObjectName to the specified MBean interface.
	 * 
	 * @param clazz Interface class of the MBean.
	 * @return The ObjectName for the given MBean.
	 * @throws JMException On error (eg. malformed ObjectName);
	 */
	public static ObjectName createObjectName(Class<?> clazz) throws JMException {
		ObjectName objectName = ObjectName.getInstance(MBEAN_DOMAIN_NAME, "name", clazz.getName());
		return objectName;
	}
	
	/**
	 * Creates a proxy object for the given MBean.
	 * 
	 * @param clazzInterface class of the MBean.
	 * @return Proxy for the specified MBean.
	 * @throws JMException On error (eg. malformed ObjectName);
	 */
	public static <M> M getProxyForMBean(Class<M> clazz) throws JMException {
		ObjectName objectName = createObjectName(clazz);
		M proxy = JMX.newMXBeanProxy(getPlatformMBeanServer(), objectName, clazz, false);
		return proxy;
	}
	
	/**
	 * 
	 * Registers the given MBean into the JDK's Platform MBean server.
	 * 
	 * @param mbeanIface Interface class of the MBean.
	 * @param mbeanImpl Implementation class of the MBean.
	 * @return ObjectName of the registered MBean.
	 * @throws JMException On registration error.
	 * @throws InstantiationException, IllegalAccessException If the given MBean implementation cannot be instantiated.
	 */
	public static <I, M extends I> ObjectName registerMBean(Class<I> mbeanIface, Class<M> mbeanImpl) throws JMException, InstantiationException, IllegalAccessException {
		ObjectName objectName = createObjectName(mbeanIface);
		M mbean = mbeanImpl.newInstance();
		getPlatformMBeanServer().registerMBean(mbean, objectName);
		return objectName;
	}
	
	/**
	 * Removes the given MBean from the JDK's Platform MBean server.
	 * 
	 * @param objectName ObjectName of the MBean to remove.
	 * @throws JMException On error.
	 */
	public static void unregisterMBean(ObjectName objectName) throws JMException {
		getPlatformMBeanServer().unregisterMBean(objectName);
	}
	
	/**
	 * 
	 * Returns a reference to the the JDK's Platform MBean server.
	 * 
	 * @return MBean server
	 */
	private static MBeanServer getPlatformMBeanServer() {
		if (platformMBeanServer == null) {
			platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
		}
		return platformMBeanServer;
	}
	
}
