/**
 * 
 */
package info.geekinaction.autoalert.ejb;

import static info.geekinaction.autoalert.ejb.EJBConstants.AUTOALERT_AUDIT_QUEUE;
import info.geekinaction.autoalert.model.domain.AuditTrail;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

/**
 * This MDB is indended to receive serialized instances of AuditTrail objects,
 * which are going to be persisted to the database.
 * 
 * @author lcsontos
 *
 */
@MessageDriven(mappedName = AUTOALERT_AUDIT_QUEUE)
public class AutoAlertAuditListener implements MessageListener {
	
	private static final Logger logger = Logger.getLogger(AutoAlertAuditListener.class);
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * This method gets executed when an AuditTrail object is received from the queue.
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message msg) {
		try {
			// We know that it can only be an ObjectMessage.
			ObjectMessage objectMessage = (ObjectMessage) msg;
			// The message payload must be an AuditTrail object.
			AuditTrail auditTrail = (AuditTrail) objectMessage.getObject();
			em.persist(auditTrail);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			// The interceptor used CLIENT_ACKNOWLEDGE, thus we must acknowledge the message manually here. 
			try {
				msg.acknowledge();
			} catch (JMSException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

}
