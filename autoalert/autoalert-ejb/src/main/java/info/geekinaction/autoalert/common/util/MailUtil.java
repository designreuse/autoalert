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
