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
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.FormatUtil.formatDate;
import static info.geekinaction.autoalert.view.FormatUtil.formatBoolean;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_CELL;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_EVEN_ROW;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_FIRST_ROW;
import static info.geekinaction.autoalert.view.ViewConstants.CSS_TABLE_ODD_ROW;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;

import info.geekinaction.autoalert.model.domain.Database;
import info.geekinaction.autoalert.view.AbstractAutoAlertPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.AutoAlertPanelUtil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

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
		
		ftInstanceInfo.setText(0, 0, MESSAGES.instanceName());
		ftInstanceInfo.setText(1, 0, MESSAGES.dateCreated());
		ftInstanceInfo.setText(2, 0, MESSAGES.resetlogsTime());
		ftInstanceInfo.setText(3, 0, MESSAGES.startupTime());
		ftInstanceInfo.setText(4, 0, "Current SCN"); // TODO
		ftInstanceInfo.setText(5, 0, MESSAGES.logMode());
		ftInstanceInfo.setText(6, 0, MESSAGES.openMode());
		ftInstanceInfo.setText(7, 0, MESSAGES.flashbackOn());
		ftInstanceInfo.setText(8, 0, MESSAGES.loginsAllowed());
		ftInstanceInfo.setText(9, 0, MESSAGES.shutdownInProgress());
		ftInstanceInfo.setText(10, 0, MESSAGES.platformName());
		ftInstanceInfo.setText(11, 0, MESSAGES.version());
		ftInstanceInfo.setText(12, 0, MESSAGES.banner());
		
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
		ftInstanceInfo.setText(1, 1, formatDate(instance.getCreated()));
		ftInstanceInfo.setText(2, 1, formatDate(instance.getResetlogsTime()));
		ftInstanceInfo.setText(3, 1, formatDate(instance.getStartupTime()));
		ftInstanceInfo.setText(4, 1, instance.getCurrentScn().toString());
		ftInstanceInfo.setText(5, 1, instance.getLogMode());
		ftInstanceInfo.setText(6, 1, instance.getOpenMode());
		ftInstanceInfo.setText(7, 1, formatBoolean(instance.getFlashbackOn()));
		ftInstanceInfo.setText(8, 1, formatBoolean(instance.getLoginsAllowed()));
		ftInstanceInfo.setText(9, 1, formatBoolean(instance.getShutdownPending()));
		ftInstanceInfo.setText(10, 1, instance.getPlatformName());
		ftInstanceInfo.setText(11, 1, instance.getVersion());
		ftInstanceInfo.setText(12, 1, instance.getBanner());
		
		for(int row = 0; row < ftInstanceInfo.getRowCount(); row++) {
			ftInstanceInfo.getCellFormatter().setStyleName(row, 1, (row % 2 == 0) ? CSS_TABLE_EVEN_ROW : CSS_TABLE_ODD_ROW);
			ftInstanceInfo.getCellFormatter().addStyleName(row, 1, CSS_TABLE_CELL);
		}
		
		ftInstanceInfo.setVisible(true);
		showLoaderImage(false);
		
	}

}
