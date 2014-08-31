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

package info.geekinaction.autoalert.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

/**
 * 
 * This utility class implements the Service Locator pattern.
 * 
 * It is intended to use in places where built-in dependency injection
 * of the Java EE container cannot be utilized.
 * 
 * @author csontosbl
 * @version $LastChangedRevision: 2369 $
 */
public final class CachingServiceLocator {
	
	/*
	 * Single static instance.
	 */
	private static CachingServiceLocator me;

	/*
	 * Object cache used for stateless EJBs.
	 */
	private final Map<String, Object> cache;

	/*
	 * Initial context.
	 */
	private final Context context;

	/**
	 * This class is using singleton pattern thus default constructor was made
	 * private to prohibit call it directly.
	 */
	private CachingServiceLocator() throws NamingException {
		cache = new ConcurrentHashMap<String, Object>();
		context = createInitialContext();
	}

	/**
	 * Creates a single instance of this class.
	 * 
	 * @return A single instance of this class.
	 */
	private static CachingServiceLocator getInstance() throws NamingException {
		if (me == null)
			me = new CachingServiceLocator();
		return me;
	}

	/**
	 * 
	 * Looks up a JDBC data source by its JNDI name.
	 * 
	 * @param jndi JNDI name of the resource.
	 * @return The corresponding data source.
	 */
	public static DataSource lookupDataSource(String jndi) throws NamingException {
		Object result = getInstance().lookup(jndi, false);
		if (!(result instanceof DataSource)) {
			throw new IllegalArgumentException("Result class is not a DataSource instance: " + result.getClass());
		}

		return (DataSource) result;
	}

	/**
	 * 
	 * Looks up a JMS queue connection factory object by its JNDI name.
	 * 
	 * @param jndi JNDI name of the resource.
	 * @return The corresponding connection factory object.
	 */
	public static QueueConnectionFactory lookupConnectionFactory(String jndi) throws NamingException {
		Object result = getInstance().lookup(jndi, true);
		if (!(result instanceof QueueConnectionFactory)) {
			throw new IllegalArgumentException("Result class is not a QueueConnectionFactory instance: " + result.getClass());
		}

		return (QueueConnectionFactory) result;

	}

	/**
	 * 
	 * Looks up a JMS topic connection factory object by its JNDI name.
	 * 
	 * @param jndi JNDI name of the resource.
	 * @return The corresponding connection factory object. 
	 */
	public static TopicConnectionFactory lookupTopicConnectionFactory(String jndi) throws NamingException {
		Object result = getInstance().lookup(jndi, true);
		if (!(result instanceof TopicConnectionFactory)) {
			throw new IllegalArgumentException("Result class is not a TopicConnectionFactory instance: " + result.getClass());
		}

		return (TopicConnectionFactory) result;

	}

	/**
	 * 
	 * Looks up a JMS queue object by its JNDI name.
	 * 
	 * @param jndi JNDI name of the resource.
	 * @return The corresponding queue object.
	 *  
	 */
	public static Queue lookupQueue(String jndi) throws NamingException {
		Object result = getInstance().lookup(jndi, false);
		if (!(result instanceof Queue)) {
			throw new IllegalArgumentException("Result class is not a Queue instance: " + result.getClass());
		}

		return (Queue) result;
	}

	/**
	 * Looks up a JMS topic object by its JNDI name.
	 * 
	 * @param jndi JNDI name of the resource.
	 * @return The corresponding topic object.
	 * 
	 */
	public static Topic lookupTopic(String jndi) throws NamingException {
		Object result = getInstance().lookup(jndi, false);
		if (!(result instanceof Topic)) {
			throw new IllegalArgumentException("Result class is not a Topic instance: " + result.getClass());
		}

		return (Topic) result;
	}

	/**
	 * 
	 * Looks up a stateless session bean by its JNDI name.
	 * 
	 * @param jndi JNDI name of the resource.
	 * @param clazz Interface of the looked up object to which it should be casted.
	 * @return A proxy to the looked up object.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T lookupStatelessEJB(String jndi, Class<T> clazz) throws NamingException {
		if (getInstance().cache.containsKey(jndi)) {
			return (T) getInstance().cache.get(jndi);
		}

		Object obj = getInstance().lookup(jndi, false);
		obj = PortableRemoteObject.narrow(obj, clazz);
		getInstance().cache.put(jndi, obj);
		
		return (T) obj;
	}

	/**
	 * Looks up a stateful session bean by its JNDI name.
	 * 
	 * @param jndi JNDI name of the resource.
	 * @param clazz Interface of the looked up object to which it should be casted.
	 * @return A proxy to the looked up object.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T lookupStatefulEJB(String jndi, Class<T> clazz) throws NamingException {
		Object obj = getInstance().lookup(jndi, false);
		obj = PortableRemoteObject.narrow(obj, clazz);
		return (T) obj;
	}

	/**
	 * 
	 * INTERNAL USE ONLY
	 * 
	 * Returns a reference to an InitialContext.
	 * This method should by customized in projects where
	 * the EJB tier is accommodated by a separate system and
	 * remote lookups have to be used.
	 * 
	 * @return An InitialContext object.
	 */
	private Context createInitialContext() throws NamingException {
		Context ctx = new InitialContext();
		return ctx;
	}

	/**
	 * INTERNAL USE ONLY
	 * 
	 * Looks up an arbitrary object by its JNDI name. 
	 * 
	 * @param jndi The JNDI name of the resource.
	 * @param cached Indicated whether the looked up object can be cached for future uses.
	 * 
	 * @return The looked up object.
	 */
	private Object lookup(String jndi, boolean cached) throws NamingException {
		Object result = null;

		if (cached && (result = cache.get(jndi)) != null)
			return result;

		result = context.lookup(jndi);
		
		if (cached) {
			cache.put(jndi, result);
		}

		return result;
	}

}
