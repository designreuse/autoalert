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
 * 
 * Utility class for sending an SMTP message by using JavaMail API.
 * 
 * @author lcsontos
 *
 */
public final class MailUtil {

	/**
	 * 
	 */
	private MailUtil() { }

	/**
	 * Sends an SMTP message with the following parameters.
	 * 
	 * @param mailSession A reference to a JavaMail Session object.
	 * @param _from Sender of the message.
	 * @param _rcpts Recipients of the message.
	 * @param subject Subject of the message.
	 * @param text Message body.
	 * @throws MessagingException Indicates an error during sending the message.
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
	 * Creates an Address object from a String representation.
	 * 
	 * @param address E-mail address as String
	 * @return The address object
	 * @throws AddressException On Error (eg. invalid address).
	 */
	private static Address createAddress(String address) throws AddressException {
		Address inetAddr = new InternetAddress(address);
		return inetAddr;
	}

}
