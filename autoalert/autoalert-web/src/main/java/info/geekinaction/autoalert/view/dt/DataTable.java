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

import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_CELL;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_EVEN_ROW;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_FIRST_ROW;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_ODD_ROW;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * Datatable implementation.
 * 
 * @author lcsontos
 * 
 */
public class DataTable<E> extends FlexTable {
	
	/**
	 * Table model of this list.
	 */
	protected IDataTableModel<E> model;

	/**
	 * 
	 */
	public DataTable() {
		super();
		setStyleName(CSS_TABLE);
	}

	/**
	 * 
	 */
	public DataTable(IDataTableModel<E> model) {
		this();
		this.model = model;
		load();
	}

	/**
	 *
	 */
	public IDataTableModel<E> getModel() {
		return model;
	}

	/**
	 * 
	 * @param model
	 */
	public void setModel(IDataTableModel<E> model) {
		this.model = model;
		load();
	}

	/**
	 * 
	 */
	private void load() {
		if (model == null)
			return;

		// Clear table
		for (int index = getRowCount() - 1; index >= 0; index--) {
			removeRow(index);
		}

		// Load data
		int columnNum = model.getColumnNum();
		int rowNum = model.getRowNum();
		for (int row = 0; row < rowNum; row++) {
			
			// Apply CSS to the whole row.
			String styleName = null;
			if (row < 1) {
				styleName = CSS_TABLE_FIRST_ROW;
			} else {
				styleName = (row % 2 == 0 ? CSS_TABLE_EVEN_ROW : CSS_TABLE_ODD_ROW) ;
			}
			
			getRowFormatter().addStyleName(row, styleName);
			
			for (int column = 0; column < columnNum; column++) {
				
				// Set alignment for the first row.
				if (row < 1) {
					getCellFormatter().setHorizontalAlignment(row, column, HasHorizontalAlignment.ALIGN_CENTER);
				}
				
				// Apply CSS to an individual cell. 
				getCellFormatter().addStyleName(row, column, CSS_TABLE_CELL);
				
				Object obj = model.getObject(row, column);
				if (obj instanceof Widget) {
					setWidget(row, column, (Widget) obj);
				} else {
					String text = (obj != null) ? obj.toString() : null;
					setText(row, column, text);
				}
			}
		}
		
	}

}
