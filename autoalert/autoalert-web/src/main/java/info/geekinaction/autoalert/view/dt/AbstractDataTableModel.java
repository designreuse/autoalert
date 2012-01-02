/**
 * 
 */
package info.geekinaction.autoalert.view.dt;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * @author lcsontos
 * 
 */
public abstract class AbstractDataTableModel<T> implements DataTableModel<T> {

	protected List<T> data;
	protected List<String> titles;
	protected List<List<Object>> cells;

	/**
	 * 
	 */
	public AbstractDataTableModel(List<T> data) {
		this.data = data;
		titles = new ArrayList<String>();
		cells = new ArrayList<List<Object>>();
		init();
	}

	/**
	 * @see info.geekinaction.autoalert.view.dt.DataTableModel#addTitle(java.lang.String,
	 *      java.lang.Integer[])
	 */
	public void addTitle(String title) {
		titles.add(title);
	}

	/**
	 * 
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
	 * @see info.geekinaction.autoalert.view.dt.DataTableModel#getTitles()
	 */
	public List<String> getTitles() {
		return titles;
	}

	/**
	 * 
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * 
	 */
	public void setData(List<T> obj) {
		data = obj;
		init();
	}

	/**
	 * 
	 */
	protected void clear() {
		titles.clear();
		
		for (List<?> record : cells) {
			record.clear();
		}
		
		cells.clear();
	}
	
	/**
	 * 
	 */
	protected abstract void init();

	/**
	 * 
	 */
	public int getRowNum() {
		return cells.size() + 1;
	}

	/**
	 * 
	 */
	public int getColumnNum() {
		return titles.size();
	}

	/**
	 * 
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
