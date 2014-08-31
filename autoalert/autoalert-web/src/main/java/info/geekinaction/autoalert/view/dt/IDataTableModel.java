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
package info.geekinaction.autoalert.view.dt;

import java.util.List;

/**
 * 
 * Data table interface.
 * 
 * @author lcsontos
 *
 */
public interface IDataTableModel<T> {

	/**
	 * Adds the given title to the model. 
	 * @param title Title to add.
	 */
	public void addTitle(String title);
	
	/**
	 * Inserts the given title to the specified position.
	 * @param index Position
	 * @param title Title
	 */
	public void insertTitle(int index, String title);

	/**
	 * Removes the given title from the model.
	 * @param title Title to remove.
	 */
	public void removeTitle(String title);

	/**
	 * Returns all the titles.
	 * @return Titles.
	 */
	public List<String> getTitles();
	
	/**
	 * Populates the model with data.
	 * @param obj New data.
	 */
	public void setData(List<T> obj);
	
	/**
	 * Returns the current data set.
	 * @return
	 */
	public List<T> getData();
	
	/**
	 * Returns the number of rows.
	 * @return Number of rows.
	 */
	public int getRowNum();
	
	/**
	 * Returns the number of columns.
	 * @return Number of columns.
	 */
	public int getColumnNum();
	
	/**
	 * Returns an object in the specified row and column.
	 * @param row Number of row.
	 * @param column Number of column.
	 * @return The object in the specified row and column.
	 */
	public Object getObject(int row, int column);

}
