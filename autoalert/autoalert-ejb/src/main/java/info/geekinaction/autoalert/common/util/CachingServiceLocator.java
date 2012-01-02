package info.geekinaction.autoalert.common.util;

import info.geekinaction.autoalert.common.LoggableRuntimeException;

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
	private CachingServiceLocator() {
		cache = new ConcurrentHashMap<String, Object>();
		context = createInitialContext();
	}

	/**
	 * Creates a single instance of this class.
	 * 
	 * @return A single instance of this class.
	 */
	private static CachingServiceLocator getInstance() {
		if (me == null)
			me = new CachingServiceLocator();
		return me;
	}

	/**
	 * 
	 * @param jndi
	 * @return
	 */
	public static DataSource lookupDataSource(String jndi) {
		Object result = getInstance().lookup(jndi, false);
		if (!(result instanceof DataSource)) {
			throw new IllegalArgumentException("Result class is not a DataSource instance: " + result.getClass());
		}

		return (DataSource) result;
	}

	/**
	 * 
	 * @param jndi
	 * @return
	 */
	public static QueueConnectionFactory lookupConnectionFactory(String jndi) {
		Object result = getInstance().lookup(jndi, true);
		if (!(result instanceof QueueConnectionFactory)) {
			throw new IllegalArgumentException("Result class is not a QueueConnectionFactory instance: " + result.getClass());
		}

		return (QueueConnectionFactory) result;

	}

	/**
	 * 
	 * @param jndi
	 * @return
	 */
	public static TopicConnectionFactory lookupTopicConnectionFactory(
			String jndi) {
		Object result = getInstance().lookup(jndi, true);
		if (!(result instanceof TopicConnectionFactory)) {
			throw new IllegalArgumentException("Result class is not a TopicConnectionFactory instance: " + result.getClass());
		}

		return (TopicConnectionFactory) result;

	}

	/**
	 * 
	 * @param jndi
	 * @return
	 */
	public static Queue lookupQueue(String jndi) {
		Object result = getInstance().lookup(jndi, false);
		if (!(result instanceof Queue)) {
			throw new IllegalArgumentException("Result class is not a Queue instance: " + result.getClass());
		}

		return (Queue) result;
	}

	/**
	 * 
	 * @param jndi
	 * @return
	 */
	public static Topic lookupTopic(String jndi) {
		Object result = getInstance().lookup(jndi, false);
		if (!(result instanceof Topic)) {
			throw new IllegalArgumentException("Result class is not a Topic instance: " + result.getClass());
		}

		return (Topic) result;
	}

	/**
	 * 
	 * @param jndi
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T lookupStatelessEJB(String jndi, Class<T> clazz) {
		if (getInstance().cache.containsKey(jndi)) {
			return (T) getInstance().cache.get(jndi);
		}

		Object obj = getInstance().lookup(jndi, false);
		try {
			obj = PortableRemoteObject.narrow(obj, clazz);
		} catch (ClassCastException cce) {
			throw new LoggableRuntimeException(cce);
		}
		
		getInstance().cache.put(jndi, obj);
		
		return (T) obj;
	}

	/**
	 * 
	 * @param jndi
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T lookupStatefulEJB(String jndi, Class<T> clazz) {
		Object obj = getInstance().lookup(jndi, false);
		try {
			obj = PortableRemoteObject.narrow(obj, clazz);
		} catch (ClassCastException cce) {
			throw new LoggableRuntimeException(cce);
		}
		return (T) obj;
	}

	/**
	 * 
	 * @return
	 */
	private Context createInitialContext() {
		Context ctx = null;
		try {
			ctx = new InitialContext();
		} catch (NamingException ne) {
			throw new LoggableRuntimeException(ne);
		}

		return ctx;
	}

	/**
	 * 
	 * @param jndi
	 * @return
	 */
	private Object lookup(String jndi, boolean cached) {
		Object result = null;

		if (cached && (result = cache.get(jndi)) != null)
			return result;

		try {
			result = context.lookup(jndi);
			cache.put(jndi, result);
		} catch (NamingException ne) {
			throw new LoggableRuntimeException(ne);
		}

		return result;
	}

}
