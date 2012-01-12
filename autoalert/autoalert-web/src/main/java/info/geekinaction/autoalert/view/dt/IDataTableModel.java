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
