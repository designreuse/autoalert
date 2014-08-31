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

package info.geekinaction.autoalert.common;

/**
 * System wide constants.
 *  
 * @author lcsontos

 */
public interface CommonConstants {

	/**
	 * Prefix of all JNDI names.
	 */
	public static final String JNDI_PREFIX = "java:comp/env";
	
	/**
	 * UTF-8 character set name.
	 */
	public static final String CHARSET_UTF8 = "UTF-8";
	
	/**
	 * ISO timestamp format.
	 */
	public static final String ISO_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	/**
	 * ISO date format.
	 */
	public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Full timestamp format without any delimiters.
	 */
	public static final String BARE_TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";
	
	/**
	 * MBean domain name
	 */
	public static final String MBEAN_DOMAIN_NAME = "autoalert";

	
}
