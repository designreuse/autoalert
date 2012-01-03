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
	public void addTitle(String title);

	/**
	 * 
	 * @param title
	 */
	public void removeTitle(String title);

	/**
	 * 
	 * @return
	 */
	public List<String> getTitles();
	
	/**
	 * 
	 * @param obj
	 */
	public void setData(List<T> obj);
	
	/**
	 * 
	 * @return
	 */
	public List<T> getData();
	
	/**
	 * 
	 * @return
	 */
	public int getRowNum();
	
	/**
	 * 
	 * @return
	 */
	public int getColumnNum();
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public Object getObject(int row, int column);

}
