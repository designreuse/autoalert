/**
 * 
 */
package info.geekinaction.autoalert.ejb;

import static info.geekinaction.autoalert.ejb.EJBConstants.AUTOALERT_AUDIT_QUEUE;
import static info.geekinaction.autoalert.ejb.EJBConstants.AUTOALERT_AUDIT_QUEUE_FACTORY;

import info.geekinaction.autoalert.common.AbstractBusinessObject;
import info.geekinaction.autoalert.common.util.MBeanUtil;
import info.geekinaction.autoalert.jmx.IAutoAlertInstrumentation;
import info.geekinaction.autoalert.model.domain.AuditTrail;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import javax.management.JMException;

import org.apache.log4j.Logger;

/**
 * @author lcsontos
 * 
 */
public class AutoAlertAuditInterceptor {

	private static final Logger logger = Logger.getLogger(AutoAlertAuditInterceptor.class);

	private IAutoAlertInstrumentation mbeanProxy;

	@Resource(mappedName = AUTOALERT_AUDIT_QUEUE_FACTORY)
	private ConnectionFactory auditQueueFactory;

	@Resource(mappedName = AUTOALERT_AUDIT_QUEUE)
	private Queue auditQueue;

	/**
	 * 
	 */
	public AutoAlertAuditInterceptor() {
		try {
			mbeanProxy = MBeanUtil.getProxyForMBean(IAutoAlertInstrumentation.class);
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
		
		AbstractBusinessObject businessObject = (AbstractBusinessObject) ctx.getTarget();

		String componentName = businessObject.getClass().getSimpleName();
		String methodName = ctx.getMethod().getName();

		logger.debug("Intercepting method call for " + methodName + " done.");

		long before = 0L;
		long after = 0L;
		try {
			before = System.currentTimeMillis();

			Object retval = ctx.proceed();

			after = System.currentTimeMillis();
			long elapsed = after - before;

			mbeanProxy.instrumentMethod(methodName, elapsed);

			return retval;
			
		} finally {

			AuditTrail auditTrail = new AuditTrail();
			auditTrail.setComponentName(componentName);
			auditTrail.setMethodName(methodName);
			auditTrail.setExecBeginTime(before == 0L ? null : before);
			auditTrail.setExecEndTime(after == 0L ? null : after);
			
			String username = businessObject.getCurrentUser();
			auditTrail.setUsername(username != null ? username.toUpperCase() : username);
			
			sendAuditTrail(auditTrail);

			logger.debug("Intercepting method call for " + methodName + " done.");
		}

	}

	/**
	 * 
	 * @param auditTrail
	 */
	private void sendAuditTrail(AuditTrail auditTrail) {
		try {
			Connection connection = auditQueueFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(auditQueue);
			ObjectMessage message = session.createObjectMessage(auditTrail);
			messageProducer.send(message);
			logger.debug("Message sent.");
		} catch (JMSException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
