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

package info.geekinaction.autoalert.model.domain;

import java.util.Date;

/**
 * Base class of database instance resource usage.
 * 
 * @author lcsontos
 * 
 */
public abstract class AbstractInstanceResourceUsage extends AbstractAlertable<String> {

	private static final long serialVersionUID = 1L;

	protected Integer lastSlice;
	// protected String timeSlice;
	protected Long sliceCount;
	protected Date beginTime;
	protected Date endTime;
	protected Long value;

	/**
	 * 
	 */
	public AbstractInstanceResourceUsage() {
		super();
	}

	/*
	 * 
	 * @return
	
	public Long getId() {
		return getKey();
	}
	 */
	/*
	 * 
	 * @param id
	public void setId(Long id) {
		setKey(id);
	}
	 */

	/**
	 * 
	 * @return
	 */
	public String getTimeSlice() {
		// return this.timeSlice;
		return getKey();
	}

	/**
	 * 
	 * @param timeSlice
	 */
	public void setTimeSlice(String timeSlice) {
		// this.timeSlice = timeSlice;
		setKey(timeSlice);
	}

	/**
	 * 
	 * @return
	 */
	public Long getSliceCount() {
		return this.sliceCount;
	}

	/**
	 * 
	 * @param sliceCount
	 */
	public void setSliceCount(Long sliceCount) {
		this.sliceCount = sliceCount;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getLastSlice() {
		return lastSlice;
	}
	
	/**
	 * 
	 * @param lastSlice
	 */
	public void setLastSlice(Integer lastSlice) {
		this.lastSlice = lastSlice;
	}

	/**
	 * 
	 * @return
	 */
	public Date getBeginTime() {
		return this.beginTime;
	}

	/**
	 * 
	 * @param beginTime
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * 
	 * @return
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * 
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 
	 * @return
	 */
	public Long getValue() {
		return this.value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(Long value) {
		this.value = value;
	}

}
