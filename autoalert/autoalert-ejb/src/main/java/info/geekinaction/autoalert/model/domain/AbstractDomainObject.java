/**
 * 
 */
package info.geekinaction.autoalert.model.domain;

import java.io.Serializable;

/**
 * @author lcsontos
 *
 */
public abstract class AbstractDomainObject<K extends Serializable> implements Serializable {

	private K key;
	
	private volatile boolean hashCodeStale;
	private int hashCode;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public AbstractDomainObject() {
		this.hashCodeStale = true;
	}
	
	/**
	 * 
	 * @param key
	 */
	public AbstractDomainObject(K key) {
		setKey(key);
	}

	/**
	 * 
	 * @return
	 */
	public final K getKey() {
		return key;
	}
	
	/**
	 * 
	 * @param key
	 */
	public final void setKey(K key) {
		if (key == null) {
			throw new NullPointerException("Key cannot be null.");
		}
		this.hashCodeStale = true;
		this.key = key;
	}
	
	/**
	 * 
	 */
	@Override
	public final int hashCode() {
		if (compareAndSet(true, false)) {
			hashCode = getKey().hashCode(); 
		}
		return hashCode;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getName());
		sb.append('@');
		sb.append(getKey().toString());
		return sb.toString();
	}
	
	/**
	 * 
	 * @param expected
	 * @param update
	 * @return
	 */
	private synchronized boolean compareAndSet(boolean expected, boolean update) {
		if (hashCodeStale != expected) {
			return false;
		}
		hashCodeStale = update;
		return true;
	}
	
}
