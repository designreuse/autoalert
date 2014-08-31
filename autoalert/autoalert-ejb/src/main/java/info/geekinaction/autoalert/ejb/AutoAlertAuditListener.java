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
