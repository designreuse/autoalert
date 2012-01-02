/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import java.util.List;

import info.geekinaction.autoalert.view.AbstractAutoAlertPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author lcsontos
 * 
 */
public class InstanceStatusPanel extends AbstractAutoAlertPanel {

	/**
	 * 
	 */
	public InstanceStatusPanel() {
		/*
		 * add(createInfoPanel(), "Info"); add(createCpuUsagePanel(), "CPU");
		 * add(createIoUsagePanel(), "I/O");
		 * 
		 * selectTab(0);
		 */
	}

	/**
	 * 
	 * @return
	 */
	private Widget createInfoPanel() {
		Grid grid = new Grid(10, 2);
		grid.setText(0, 0, "Database ID:");
		grid.setText(0, 1, "42342134");
		grid.setText(1, 0, "Database name:");
		grid.setText(1, 1, "RIA102");
		return grid;
	}

	private Widget createCpuUsagePanel() {
		return new SimplePanel();
	}

	private Widget createIoUsagePanel() {
		return new SimplePanel();
	}

	@Override
	public void buildPanel() {
		// TODO Auto-generated method stub

	}

	public void display(AutoAlertDisplay display, List<?> obj) {
		// TODO Auto-generated method stub

	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

}
