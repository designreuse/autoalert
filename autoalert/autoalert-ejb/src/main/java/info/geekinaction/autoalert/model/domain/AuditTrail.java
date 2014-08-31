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
 * Represents audit trail records.
 * 
 * @author lcsontos
 *
 */
public class AuditTrail extends AbstractDomainObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Date auditTime;
	
	private String componentName;
	private String methodName;
	
	private Date execBeginTime;
	private Date execEndTime;

	/**
	 * 
	 */
	public AuditTrail() { 
		auditTime = new Date();
	}
	
	public Long getId() {
		return getKey();
	}
	
	public void setId(Long id) {
		setKey(id);
	}

	/**
	 * @param key
	 */
	public AuditTrail(Long key) {
		super(key);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Date getExecBeginTime() {
		return execBeginTime;
	}

	public void setExecBeginTime(Long execBeginTime) {
		Date _execBeginTime = execBeginTime == null ? null : new Date(execBeginTime); 
		setExecBeginTime(_execBeginTime);
	}

	public void setExecBeginTime(Date execBeginTime) {
		this.execBeginTime = execBeginTime;
	}

	public Date getExecEndTime() {
		return execEndTime;
	}

	public void setExecEndTime(Long execEndTime) {
		Date _execEndTime = execEndTime == null ? null : new Date(execEndTime);
		setExecEndTime(_execEndTime);
	}
	
	public void setExecEndTime(Date execEndTime) {
		this.execEndTime = execEndTime;
	}
	
}
