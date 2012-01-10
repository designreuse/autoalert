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
 * 
 * This is an EJB interceptor for instrumenting and auditing of execution
 * of EJB object of this application.
 * 
 * @author lcsontos
 * 
 */
public class AutoAlertAuditInterceptor {

	private static final Logger logger = Logger.getLogger(AutoAlertAuditInterceptor.class);

	/**
	 * MBean proxy for that MBean which gathers runtime statistics.
	 */
	private IAutoAlertInstrumentation mbeanProxy;

	/**
	 * JMS queue factory of audit queue.
	 */
	@Resource(mappedName = AUTOALERT_AUDIT_QUEUE_FACTORY)
	private ConnectionFactory auditQueueFactory;

	/**
	 * JMS queue for auditing.
	 */
	@Resource(mappedName = AUTOALERT_AUDIT_QUEUE)
	private Queue auditQueue;

	/**
	 * 
	 */
	public AutoAlertAuditInterceptor() {
		// Create a proxy for the instrumentation MBean.
		try {
			mbeanProxy = MBeanUtil.getProxyForMBean(IAutoAlertInstrumentation.class);
		} catch (JMException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * This method intercepts any calls for that EJB object to which this interceptor has been registered.
	 * 
	 * @param ctx Context of business method invocation passed by the container.
	 * @return The return value of the intercepted call.
	 * @throws Exception The exception which the intercepted call has raised.
	 */
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		
		// Convert the target object to its base class.
		AbstractBusinessObject businessObject = (AbstractBusinessObject) ctx.getTarget();

		// Name of the EJB component and the intercepted method.
		String componentName = businessObject.getClass().getSimpleName();
		String methodName = ctx.getMethod().getName();

		// Log that we have just started to intercept the call.
		logger.debug("Intercepting method call for " + methodName + " done.");

		// Millis from the EPOC before and after the execution of the business method.
		long before = 0L;
		long after = 0L;
		
		try {
			before = System.currentTimeMillis();

			// Proceed with the execution of the original call.
			Object retval = ctx.proceed();

			after = System.currentTimeMillis();
			long elapsed = after - before;

			// Notify the instrumentation MBean about the circumstances of the execution.
			mbeanProxy.instrumentMethod(methodName, elapsed);

			return retval;
			
		} finally {

			// Create an audit trail about the execution.
			AuditTrail auditTrail = new AuditTrail();
			auditTrail.setComponentName(componentName);
			auditTrail.setMethodName(methodName);
			auditTrail.setExecBeginTime(before == 0L ? null : before);
			auditTrail.setExecEndTime(after == 0L ? null : after);
			
			// Query on behalf of who has been the method executed.
			String username = businessObject.getCurrentUser();
			auditTrail.setUsername(username != null ? username.toUpperCase() : username);
			
			// Put the audit event to a JMS queue for future processing.
			sendAuditTrail(auditTrail);

			logger.debug("Intercepting method call for " + methodName + " done.");
		}

	}

	/**
	 * This interceptor uses a JMS queue for auditing method calls,
	 * which happens asynchronously and thus does not hinders the execution of
	 * the intercepted call.
	 * 
	 * @param auditTrail Audit trail object
	 */
	private void sendAuditTrail(AuditTrail auditTrail) {
		Connection connection = null;
		try {
			// Acquire a connection factory object.
			connection = auditQueueFactory.createConnection();
			// Create JMS session.
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(auditQueue);
			// Create ObjectMessage
			ObjectMessage message = session.createObjectMessage(auditTrail);
			// Send AuditTrail to the AuditListener MDB.
			messageProducer.send(message);
			logger.debug("Message sent.");
		} catch (JMSException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					logger.warn(e.getMessage(), e);
				}
			}
		}
	}

}
