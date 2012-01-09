/**
 * 
 */
package info.geekinaction.autoalert.common.util;

import static info.geekinaction.autoalert.mail.MailConstants.MIME_TYPE;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author lcsontos
 *
 */
public final class MailUtil {

	/**
	 * 
	 */
	private MailUtil() { }
	
	/**
	 * 
	 */
	public static void sendMessage(Session mailSession, String _from, String[] _rcpts, String subject, String text) throws MessagingException {
		
		// Sender
		Address from = createAddress(_from);

		// Recipients
		Address[] rcpts = new Address[_rcpts.length];
		for (int index = 0; index < _rcpts.length; index++ ) {
			rcpts[index] = createAddress(_rcpts[index]);
		}
		
		// Send alert message
		Message msg = new MimeMessage(mailSession);
		msg.setSubject(subject);
		msg.setSentDate(DateUtil.currentTime());
		msg.setFrom(from);
		msg.setRecipients(Message.RecipientType.TO, rcpts);
		msg.setContent(text, MIME_TYPE);
		Transport.send(msg);
		
	}
	
	/**
	 * 
	 * @param address
	 * @return
	 */
	private static Address createAddress(String address) throws AddressException {
		Address inetAddr = new InternetAddress(address);
		return inetAddr;
	}

}
