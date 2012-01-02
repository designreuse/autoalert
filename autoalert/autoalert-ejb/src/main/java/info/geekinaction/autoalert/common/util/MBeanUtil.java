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
	 * 
	 * @param clazz
	 * @return
	 */
	public static ObjectName createObjectName(Class<?> clazz) throws JMException {
		ObjectName objectName = ObjectName.getInstance(MBEAN_DOMAIN_NAME, "name", clazz.getName());
		return objectName;
	}
	
	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws MalformedObjectNameException
	 */
	public static <M> M getProxyForMBean(Class<M> clazz) throws JMException {
		ObjectName objectName = createObjectName(clazz);
		M proxy = JMX.newMXBeanProxy(getPlatformMBeanServer(), objectName, clazz, false);
		return proxy;
	}
	
	/**
	 * 
	 * @param mbean
	 * @param clazz
	 * @throws JMException
	 */
	public static <I, M extends I> ObjectName registerMBean(Class<I> mbeanIface, Class<M> mbeanImpl) throws JMException, InstantiationException, IllegalAccessException {
		ObjectName objectName = createObjectName(mbeanIface);
		M mbean = mbeanImpl.newInstance();
		getPlatformMBeanServer().registerMBean(mbean, objectName);
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName
	 * @throws JMException
	 */
	public static void unregisterMBean(ObjectName objectName) throws JMException {
		getPlatformMBeanServer().unregisterMBean(objectName);
	}
	
	/**
	 * 
	 * @return
	 */
	private static MBeanServer getPlatformMBeanServer() {
		if (platformMBeanServer == null) {
			platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
		}
		return platformMBeanServer;
	}
	
}
