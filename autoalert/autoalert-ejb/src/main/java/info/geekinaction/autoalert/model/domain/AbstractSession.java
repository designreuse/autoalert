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


import java.util.Date;

/**
 * 
 * Base class of representing database sessions.
 * 
 * @author lcsontos
 * 
 */
public abstract class AbstractSession extends AbstractDomainObject<Long> {

	private static final long serialVersionUID = 1L;

	protected String spid;
	protected Long serial;
	protected String status;
	protected String sqlText;
	protected String terminal;
	protected String username;
	protected String program;
	protected String module;
	protected String machine;
	protected Date logonTime;

	/**
	 * 
	 * @return
	 */
	public String getSpid() {
		return spid;
	}

	/**
	 * 
	 * @param spid
	 */
	public void setSpid(String spid) {
		this.spid = spid;
	}

	/**
	 * 
	 * @return
	 */
	public Long getSid() {
		return getKey();
	}

	/**
	 * 
	 * @param sid
	 */
	public void setSid(Long sid) {
		setKey(sid);
	}

	/**
	 * 
	 * @return
	 */
	public Long getSerial() {
		return serial;
	}

	/**
	 * 
	 * @param serial
	 */
	public void setSerial(Long serial) {
		this.serial = serial;
	}

	/**
	 * 
	 * @return
	 */
	public String getSqlText() {
		return sqlText;
	}

	/**
	 * 
	 * @param sqlText
	 */
	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	/**
	 * 
	 * @return
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * 
	 * @param terminal
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return
	 */
	public String getProgram() {
		return program;
	}

	/**
	 * 
	 * @param program
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	/**
	 * 
	 * @return
	 */
	public String getModule() {
		return module;
	}

	/**
	 * 
	 * @param module
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * 
	 * @return
	 */
	public String getMachine() {
		return machine;
	}

	/**
	 * 
	 * @param machine
	 */
	public void setMachine(String machine) {
		this.machine = machine;
	}

	/**
	 * 
	 * @return
	 */
	public Date getLogonTime() {
		return logonTime;
	}

	/**
	 * 
	 * @param logonTime
	 */
	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
