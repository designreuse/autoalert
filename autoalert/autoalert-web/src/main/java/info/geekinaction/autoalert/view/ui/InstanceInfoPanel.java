/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_CELL;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_EVEN_ROW;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_FIRST_ROW;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_ODD_ROW;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import info.geekinaction.autoalert.model.domain.Database;
import info.geekinaction.autoalert.view.AbstractAutoAlertPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;

/**
 * @author lcsontos
 *
 */
public class InstanceInfoPanel extends AbstractAutoAlertPanel<Database> {

	private FlexTable ftInstanceInfo;
	
	/**
	 * @see info.geekinaction.autoalert.view.IAutoAlertPanel#refresh()
	 */
	@Override
	public void refresh() {
		showLoaderImage(true);
		controller.onInstanceInfoRefresh();
	}

	/**
	 * @see info.geekinaction.autoalert.view.AbstractAutoAlertPanel#createWidget()
	 */
	@Override
	protected Widget createWidget() {
		ftInstanceInfo = new FlexTable();
		ftInstanceInfo.setStyleName(CSS_TABLE);
		ftInstanceInfo.setVisible(false);
		
		ftInstanceInfo.setText(0, 0, "Instance name");
		ftInstanceInfo.setText(1, 0, "Date created");
		ftInstanceInfo.setText(2, 0, "Resetlogs time");
		ftInstanceInfo.setText(3, 0, "Startup time");
		ftInstanceInfo.setText(4, 0, "Log mode");
		ftInstanceInfo.setText(5, 0, "Open mode");
		ftInstanceInfo.setText(6, 0, "Flashback on");
		ftInstanceInfo.setText(7, 0, "Logins allowed");
		ftInstanceInfo.setText(8, 0, "Shutdown in progress");
		ftInstanceInfo.setText(9, 0, "Platform name");
		ftInstanceInfo.setText(10, 0, "Version");
		ftInstanceInfo.setText(11, 0, "Banner");
		
		for(int row = 0; row < ftInstanceInfo.getRowCount(); row++) {
			ftInstanceInfo.getCellFormatter().setStyleName(row, 0, CSS_TABLE_FIRST_ROW);
			ftInstanceInfo.getCellFormatter().addStyleName(row, 0, CSS_TABLE_CELL);
			ftInstanceInfo.getCellFormatter().setHorizontalAlignment(row, 0, HasAlignment.ALIGN_LEFT);
			
		}
		
		Panel container = AutoAlertPanelUtil.createContainer(ftInstanceInfo, this.getClass().getName(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				refresh();
			}
		});
		
		return container;
	}
	
	/**
	 * 
	 */
	@Override
	public void display(AutoAlertDisplay display, Database instance) {
		if (!display.equals(AutoAlertDisplay.INSTANCE_INFO)) {
			return;
		}
		
		ftInstanceInfo.setText(0, 1, instance.getName());
		ftInstanceInfo.setText(1, 1, instance.getCreated().toString());
		ftInstanceInfo.setText(2, 1, instance.getResetlogsTime().toString());
		ftInstanceInfo.setText(3, 1, instance.getStartupTime().toString());
		ftInstanceInfo.setText(4, 1, instance.getLogMode());
		ftInstanceInfo.setText(5, 1, instance.getOpenMode());
		ftInstanceInfo.setText(6, 1, instance.getFlashbackOn().toString());
		ftInstanceInfo.setText(7, 1, instance.getLoginsAllowed().toString());
		ftInstanceInfo.setText(8, 1, instance.getShutdownPending().toString());
		ftInstanceInfo.setText(9, 1, instance.getPlatformName());
		ftInstanceInfo.setText(10, 1, instance.getVersion());
		ftInstanceInfo.setText(11, 1, instance.getBanner());
		
		for(int row = 0; row < ftInstanceInfo.getRowCount(); row++) {
			ftInstanceInfo.getCellFormatter().setStyleName(row, 1, (row % 2 == 0) ? CSS_TABLE_EVEN_ROW : CSS_TABLE_ODD_ROW);
			ftInstanceInfo.getCellFormatter().addStyleName(row, 1, CSS_TABLE_CELL);
		}
		
		ftInstanceInfo.setVisible(true);
		showLoaderImage(false);
		
	}

}
