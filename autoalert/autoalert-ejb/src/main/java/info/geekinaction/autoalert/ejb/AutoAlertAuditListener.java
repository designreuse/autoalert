/**
 * 
 */
package info.geekinaction.autoalert.ejb;

import static info.geekinaction.autoalert.ejb.EJBConstants.AUTOALERT_AUDIT_QUEUE;
import info.geekinaction.autoalert.model.domain.AuditTrail;

import javax.ejb.MessageDriven;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

/**
 * @author lcsontos
 *
 */
@MessageDriven(mappedName = AUTOALERT_AUDIT_QUEUE)
public class AutoAlertAuditListener implements MessageListener {
	
	private static final Logger logger = Logger.getLogger(AutoAlertAuditListener.class);
	
	@PersistenceContext
	private EntityManager em;
	

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message msg) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) msg;
			AuditTrail auditTrail = (AuditTrail) objectMessage.getObject();
			em.persist(auditTrail);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
