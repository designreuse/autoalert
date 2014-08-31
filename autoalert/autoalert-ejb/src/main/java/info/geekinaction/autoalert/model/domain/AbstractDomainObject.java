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

import java.io.Serializable;

/**
 * Highest level base class of all domain objects.
 * It represents an arbitrary domain object with a key type K and
 * implements lazy hashCode() computation for the given K key.
 * 
 * @author lcsontos
 *
 */
public abstract class AbstractDomainObject<K> implements Serializable {

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
	public K getKey() {
		return key;
	}
	
	/**
	 * 
	 * @param key
	 */
	public void setKey(K key) {
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
	public int hashCode() {
		if (compareAndSet(true, false)) {
			hashCode = (getKey() == null) ? 0 : getKey().hashCode(); 
		}
		return hashCode;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this.key == null) { return false; }
		if (obj == null) { return false; }
		if (!(obj instanceof AbstractDomainObject<?>)) { return false; }
		K key = ((AbstractDomainObject<K>) obj).getKey();
		if (key == null) { return false; }
		return this.key.equals(key);
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
