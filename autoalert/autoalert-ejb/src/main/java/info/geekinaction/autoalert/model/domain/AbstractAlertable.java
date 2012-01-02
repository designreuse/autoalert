/**
 * 
 */
package info.geekinaction.autoalert.model.domain;


import java.io.Serializable;

/**
 * @author csontosbl
 * 
 */
public abstract class AbstractAlertable<K extends Serializable> extends AbstractDomainObject<K> {

	private static final long serialVersionUID = 1L;
	
	protected Integer threshold;
	protected Integer alert;

	/**
	 * 
	 * @return
	 */
	public Integer getAlert() {
		return alert;
	}

	/**
	 * 
	 * @param alert
	 */
	public void setAlert(Integer alert) {
		this.alert = alert;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getThreshold() {
		return threshold;
	}
	
	/**
	 * 
	 * @param threshold
	 */
	public void setThreshold(Integer treshold) {
		this.threshold = treshold;
	}
	
}
