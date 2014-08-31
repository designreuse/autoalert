/*
 * Copyright (C) 2010 - present, Laszlo Csontos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
