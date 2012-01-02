/**
 * 
 */
package info.geekinaction.autoalert.ejb;

import info.geekinaction.autoalert.common.util.MBeanUtil;
import info.geekinaction.autoalert.jmx.IAutoAlertManagement;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.management.JMException;

import org.apache.log4j.Logger;

/**
 * @author lcsontos
 * 
 */
public final class AutoAlertModelInterceptor {
	
	private static final Logger logger = Logger.getLogger(AutoAlertModelInterceptor.class); 
	
	private IAutoAlertManagement mbeanProxy;
	
	/**
	 * 
	 */
	public AutoAlertModelInterceptor() {
		try {
			mbeanProxy = MBeanUtil.getProxyForMBean(IAutoAlertManagement.class);
		} catch (JMException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		System.out.println("*** DefaultInterceptor intercepting " + ctx.getMethod().getName());
		try {
			long before = System.currentTimeMillis();
			
			Object retval = ctx.proceed();
			
			long after = System.currentTimeMillis();
			long elapsed = after - before;
			
			return retval;
		} finally {
			System.out.println("*** DefaultInterceptor exiting");
		}

	}
}
