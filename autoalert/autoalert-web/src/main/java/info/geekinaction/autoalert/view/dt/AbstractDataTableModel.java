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

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * 
 * Base class of all datatable models.
 * 
 * @author lcsontos
 * 
 */
public abstract class AbstractDataTableModel<T> implements IDataTableModel<T> {

	/**
	 * Data of the model.
	 */
	protected List<T> data;
	
	/**
	 * Titles of the corresponding data table.
	 */
	protected List<String> titles;
	
	/**
	 * Cells of the data table
	 */
	protected List<List<Object>> cells;
	
	/**
	 * Creates the model with no data.
	 */
	public AbstractDataTableModel() {
		this(null);
	}

	/**
	 * Creates the model with the specified data set.
	 */
	public AbstractDataTableModel(List<T> data) {
		this.data = (data == null) ? new ArrayList<T>() : data;
		titles = new ArrayList<String>();
		cells = new ArrayList<List<Object>>();
		clear();
		processTitles();
		processData();
	}

	/**
	 * @see info.geekinaction.autoalert.view.dt.IDataTableModel#addTitle(java.lang.String, java.lang.Integer[])
	 */
	public void addTitle(String title) {
		titles.add(title);
	}
	
	/**
	 * @see info.geekinaction.autoalert.view.dt.IDataTableModel#insertTitle(int, String)
	 */
	@Override
	public void insertTitle(int index, String title) {
		titles.add(index, title);
	}

	/**
	 * @see info.geekinaction.autoalert.view.dt.IDataTableModel#removeTitle(String)
	 */
	public void removeTitle(String title) {
		Stack<Integer> toRemove = new Stack<Integer>();

		// Search for the given title
		for (int index = 0; index < titles.size(); index++) {
			String item = titles.get(index);
			if (item.equals(title))
				toRemove.push(index);
		}

		// Remove found titles
		Integer key = null;
		do {
			try {
				key = toRemove.pop();
				titles.remove(key);
			} catch (EmptyStackException e) {
				key = null;
			}
		} while (key != null);

	}

	/**
	 * @see info.geekinaction.autoalert.view.dt.IDataTableModel#getTitles()
	 */
	public List<String> getTitles() {
		return titles;
	}

	/**
	 * @see info.geekinaction.autoalert.view.dt.IDataTableModel#getData()
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @see info.geekinaction.autoalert.view.dt.IDataTableModel#setData(List)
	 */
	public void setData(List<T> obj) {
		data = obj;
		clear();
		processTitles();
		processData();
	}

	/**
	 * Clears all data of this model.
	 */
	protected void clear() {
		titles.clear();
		
		for (List<?> record : cells) {
			record.clear();
		}
		
		cells.clear();
	}
	
	/**
	 * Descendant classes must implement this method to populate model with appropriate titles.
	 */
	protected abstract void processTitles();
	
	/**
	 * Descendant classes must implement this method to populate model's cells with data.
	 */
	protected abstract void processData();

	/**
	 * @see info.geekinaction.autoalert.view.dt.IDataTableModel#getRowNum()
	 */
	public int getRowNum() {
		return cells.size() + 1;
	}

	/**
	 * @see info.geekinaction.autoalert.view.dt.IDataTableModel#getColumnNum()
	 */
	public int getColumnNum() {
		return titles.size();
	}

	/**
	 * @see info.geekinaction.autoalert.view.dt.IDataTableModel#getObject(int, int)
	 */
	public Object getObject(int row, int column) {
		if (row > 0) {
			List<Object> record = cells.get(row - 1);
			return record.get(column);
		} else {
			return titles.get(column);
		}
	}

}
