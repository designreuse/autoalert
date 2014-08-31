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
package info.geekinaction.autoalert.mail;

/**
 * 
 * Constants of the mail sender subsystem.
 * 
 * @author lcsontos
 *
 */
public interface MailConstants {

	/**
	 * MIME type of messages.
	 */
	public static final String MIME_TYPE = "text/html";

	/**
	 * Location of mail template.
	 */
	public static final String MAIL_TEMPLATE = "/info/geekinaction/autoalert/mail/mail_template.html";
	
	/**
	 * Location of Velocity's configuration.
	 */
	public static final String VELOCITY_CONFIG_FILE = "/info/geekinaction/autoalert/mail/velocity.properties";

	// Place-holder names in the template.
	public static final String VM_DATABASE = "database";
	public static final String VM_TABLESPACES = "tablespaces";
	public static final String VM_DATAFILES = "datafiles";
	public static final String VM_CPU_USAGE = "cpu_usage";
	public static final String VM_IO_USAGE = "io_usage";
	
}
