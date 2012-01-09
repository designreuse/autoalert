package info.geekinaction.autoalert.model.domain;

import java.util.Date;

/**
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
