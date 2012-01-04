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
import com.google.gwt.user.client.ui.Widget;

/**
 * @author lcsontos
 * 
 */
public class DataTable<E> extends FlexTable {


	
	protected DataTableModel<E> model;

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
	public DataTable(DataTableModel<E> model) {
		this();
		this.model = model;
		load();
	}

	/**
	 * 
	 * @return
	 */
	public DataTableModel<E> getModel() {
		return model;
	}

	/**
	 * 
	 * @param model
	 */
	public void setModel(DataTableModel<E> model) {
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
