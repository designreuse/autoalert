/**
 * 
 */
package info.geekinaction.autoalert.view.dt;

import java.util.List;

/**
 * @author lcsontos
 *
 */
public interface DataTableModel<T> {

	/**
	 * 
	 * @param title
	 */
	void addTitle(String title);

	/**
	 * 
	 * @param title
	 */
	public void removeTitle(String title);

	/**
	 * 
	 * @return
	 */
	List<String> getTitles();
	
	/**
	 * 
	 * @param obj
	 */
	void setData(List<T> obj);
	
	/**
	 * 
	 * @return
	 */
	List<T> getData();
	
	/**
	 * 
	 * @return
	 */
	int getRowNum();
	
	/**
	 * 
	 * @return
	 */
	int getColumnNum();
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	Object getObject(int row, int column);

}
