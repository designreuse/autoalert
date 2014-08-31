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

/**
 * Holds additional fields which are specific for data files.
 * 
 * @author lcsontos
 *
 */
public class Datafile extends AbstractStorage {

	private static final long serialVersionUID = 1L;

	private String autoext;
	private Float incrByMb;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return getKey();
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		setKey(fileName);
	}

	/**
	 * @return the autoext
	 */
	public String getAutoext() {
		return autoext;
	}

	/**
	 * @param autoext
	 *            the autoext to set
	 */
	public void setAutoext(String autoext) {
		this.autoext = autoext;
	}

	/**
	 * @return the incrByMb
	 */
	public Float getIncrByMb() {
		return incrByMb;
	}

	/**
	 * @param incrByMb
	 *            the incrByMb to set
	 */
	public void setIncrByMb(Float incrByMb) {
		this.incrByMb = incrByMb;
	}

}
