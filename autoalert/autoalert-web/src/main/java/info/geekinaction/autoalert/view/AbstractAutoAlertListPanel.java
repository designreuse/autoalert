/**
 * 
 */
package info.geekinaction.autoalert.view;

import info.geekinaction.autoalert.view.dt.DataTable;
import info.geekinaction.autoalert.view.dt.IDataTableModel;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base implementation of all List Panel within this application
 * @author lcsontos
 *
 * @param <E> Record type of the list's data model.
 * @param <M> Data model of the underlying list.
 */
public abstract class AbstractAutoAlertListPanel<E, M extends IDataTableModel<E>> extends AbstractAutoAlertPanel<List<E>> {

	/**
	 * Datatable component.
	 */
	private DataTable<E> dtList;
	
	/**
	 * Display name of this list.
	 */
	private AutoAlertDisplay display;
	
	/**
	 * Table-model of this list.
	 */
	private M model;

	/**
	 * Constructor.
	 * 
	 * @param display Display name
	 * @param model Table model
	 */
	public AbstractAutoAlertListPanel(AutoAlertDisplay display, M model) {
		this.display = display;
		this.model = model;
	}
	
	@Override
	protected Widget createWidget() {
		// Tablespaces
		dtList = new DataTable<E>();
		Panel container = AutoAlertPanelUtil.createContainer(dtList, this.getClass().getName(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				refresh();
			}
		});
		
		return container;
	}
	
	@Override
	public void display(AutoAlertDisplay display, List<E> data) {
		super.display(display, data);

		if (!this.display.equals(display)) {
			return;
		}

		model.setData(data);
		dtList.setModel(model);
		
		showLoaderImage(false);
	}
	
	@Override
	public void refresh() {
		showLoaderImage(true);
		controllerCallback();
	}
	
	/**
	 * The implementor class must override this method to indicate
	 * which method of the controller should be called.
	 */
	protected abstract void controllerCallback();
	
}
