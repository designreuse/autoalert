/**
 * 
 */
package info.geekinaction.autoalert.model.domain;

/**
 * @author csontosbl
 * 
 */
public abstract class AbstractAlertable<K> extends AbstractDomainObject<K> implements Comparable<AbstractAlertable<K>> {

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
	
	/**
	 * 
	 */
	@Override
	public int compareTo(AbstractAlertable<K> other) {
		if (other == null) {
			return 1; // Nulls last.
		}
		int retval;
		if ( (retval = compareByClass(other.getClass())) == 0 ) {
			K otherKey = other.getKey();
			retval = compareByKey(otherKey);
		}
		return retval;
	}
	
	/**
	 * 
	 * @param otherClass
	 * @return
	 */
	private int compareByClass(Class<?> otherClass) {
		String otherClassName = otherClass.getName();
		String thisClassName = this.getClass().getName();
		return thisClassName.compareTo(otherClassName);
	}
	
	/**
	 * 
	 * @param otherKey
	 * @return
	 */
	private int compareByKey(K otherKey) {
		if (otherKey instanceof Comparable<?>) {
			return ((Comparable<K>) getKey()).compareTo(otherKey);
		}
		String thisKeyAS = getKey().toString();
		String otherKeyAS = getKey().toString();
		return thisKeyAS.compareTo(otherKeyAS);
	}
	
}
