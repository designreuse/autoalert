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
package info.geekinaction.autoalert.model.domain;

/**
 * 
 * Represents all the available parameters of this system.
 * 
 * @author lcsontos
 *
 */
public enum ParameterName {
	
	AUTOALERT_MAIL_FROM ("V"),
	AUTOALERT_RCPT_TO ("V"),
	AUTOALERT_SUBJECT ("V"),
	CPU_USAGE_THRESHOLD ("N"),
	PIO_USAGE_THRESHOLD ("N"),
	TBS_SIZERM_THRESHOLD ("N"),
	TBS_USAGE_THRESHOLD ("N");
	
	private String paramType;
	
	/**
	 * 
	 * @param _paramType
	 */
	private ParameterName(String _paramType) {
		this.paramType = _paramType;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getParamType() {
		return paramType;
	}

}
