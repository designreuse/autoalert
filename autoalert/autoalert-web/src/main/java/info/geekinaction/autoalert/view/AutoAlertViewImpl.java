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
package info.geekinaction.autoalert.view;

import static info.geekinaction.autoalert.view.ViewConstants.APP_DIV_ID;
import static info.geekinaction.autoalert.view.ViewConstants.SIDEBAR_ANCHOR_NAME;
import static info.geekinaction.autoalert.view.ViewConstants.*;

import info.geekinaction.autoalert.model.domain.Database;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;

import info.geekinaction.autoalert.view.ui.DatafileStatusPanel;
import info.geekinaction.autoalert.view.ui.InstanceCpuUsagePanel;
import info.geekinaction.autoalert.view.ui.InstanceInfoPanel;
import info.geekinaction.autoalert.view.ui.InstanceIoUsagePanel;
import info.geekinaction.autoalert.view.ui.SessionCpuUsagePanel;
import info.geekinaction.autoalert.view.ui.SessionIoUsagePanel;
import info.geekinaction.autoalert.view.ui.TablespaceStatusPanel;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * Implementation of main view.
 * 
 * @author lcsontos
 * 
 */
public class AutoAlertViewImpl extends AbstractAutoAlertPanel<Object> implements IAutoAlertView {

	private IAutoAlertModelAsync model;
	private IAutoAlertController controller;

	private HTML contentTitle;
	private Panel contents;
	private AbstractAutoAlertPanel<?> currentContent;
	
	private InstanceInfoPanel instanceInfoPanel;
	private InstanceCpuUsagePanel instanceCpuUsagePanel;
	private InstanceIoUsagePanel instanceIoUsagePanel;
	private SessionCpuUsagePanel sessionCpuUsagePanel;
	private SessionIoUsagePanel sessionIoUsagePanel;
	private TablespaceStatusPanel tablespaceStatusPanel;
	private DatafileStatusPanel datafileStatusPanel;
	
	/**
	 * 
	 */
	public AutoAlertViewImpl(IAutoAlertModelAsync model) {
		super(false);
		this.model = model;
	}

	/**
	 * @see IAutoAlertView#setActionListener(IAutoAlertController) 
	 */
	public void setActionListener(IAutoAlertController controller) {
		this.controller = controller;
	}

	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {
		
		instanceInfoPanel = new InstanceInfoPanel();
		instanceCpuUsagePanel = new InstanceCpuUsagePanel();
		instanceIoUsagePanel = new InstanceIoUsagePanel();
		sessionCpuUsagePanel = new SessionCpuUsagePanel();
		sessionIoUsagePanel = new SessionIoUsagePanel();
		tablespaceStatusPanel = new TablespaceStatusPanel();
		datafileStatusPanel = new DatafileStatusPanel();
		
		instanceInfoPanel.registerController(controller);
		instanceCpuUsagePanel.registerController(controller);
		instanceIoUsagePanel.registerController(controller);
		sessionCpuUsagePanel.registerController(controller);
		sessionIoUsagePanel.registerController(controller);
		tablespaceStatusPanel.registerController(controller);
		datafileStatusPanel.registerController(controller);
		
		contentTitle = new HTML();
		contents = new SimplePanel();
		return contents;
	}
	
	/**
	 * @see IAutoAlertView#selectDisplay(AutoAlertDisplay, Object)
	 */
	public void selectDisplay(AutoAlertDisplay display, Object title) {
		
		// Remove any contents if any.
		if (currentContent != null) {
			contents.remove(currentContent);
		}
		
		// Set up new content.
		switch (display) {
			case INSTANCE_INFO:	currentContent = instanceInfoPanel; break;
			case STORAGE_TABLESPACES: currentContent = tablespaceStatusPanel; break;
			case STORAGE_DATAFILES: currentContent = datafileStatusPanel; break;
			case INSTANCE_CPU: currentContent = instanceCpuUsagePanel; break;
			case INSTANCE_IO: currentContent = instanceIoUsagePanel; break;
			case SESSIONS_BY_CPU: currentContent = sessionCpuUsagePanel; break;
			case SESSION_BY_IO: currentContent = sessionIoUsagePanel; break;
		}
		
		// Change title if non-empty.
		if (title != null) {
			contentTitle.setHTML(title.toString());
		}
	
		// Add to this view.
		contents.add(currentContent);

		// Refresh new view.
		currentContent.setVisible(true);
		currentContent.refresh();
		
	}

	/**
	 * @see IAutoAlertView#refreshDisplay(AutoAlertDisplay, Object)
	 */
	public void refreshDisplay(AutoAlertDisplay display, Object data) {
		
		switch (display) {
		case INSTANCE_INFO:
			Database instance = (Database) data;
			instanceInfoPanel.display(display, instance);
			break;
		case STORAGE_TABLESPACES:
			List<Tablespace> tablespaces = (List<Tablespace>) data;
			tablespaceStatusPanel.display(display, tablespaces);
			break;
		case STORAGE_DATAFILES:
			List<Datafile> datafiles = (List<Datafile>) data;
			datafileStatusPanel.display(display, datafiles);
			break;
		case INSTANCE_CPU:
			List<InstanceCpuUsage> instanceCpuUsages = (List<InstanceCpuUsage>) data;
			instanceCpuUsagePanel.display(display, instanceCpuUsages);
			break;
		case INSTANCE_IO:
			List<InstanceIoUsage> instanceIoUsages = (List<InstanceIoUsage>) data;
			instanceIoUsagePanel.display(display, instanceIoUsages);
			break;
		case SESSIONS_BY_CPU:
			List<SessionCpuUsage> sessionCpuUsages = (List<SessionCpuUsage>) data;
			sessionCpuUsagePanel.display(display, sessionCpuUsages);
			break;
		case SESSION_BY_IO:
			List<SessionIoUsage> sessionIoUsages = (List<SessionIoUsage>) data;
			sessionIoUsagePanel.display(display, sessionIoUsages);
			break;
		}

	}

	/**
	 * @see IAutoAlertPanel#refresh()
	 */
	public void refresh() {
		instanceInfoPanel.refresh();
		instanceCpuUsagePanel.refresh();
		instanceIoUsagePanel.refresh();
		sessionCpuUsagePanel.refresh();
		sessionIoUsagePanel.refresh();
		tablespaceStatusPanel.refresh();
		datafileStatusPanel.refresh();
	}

	/**
	 * @see IAutoAlertView#init()
	 */
	public void init() {
		
		// Register click handlers for native anchors.
		NodeList<Element> anchorList = getElementsByName(SIDEBAR_ANCHOR_NAME);
		for(int index = 0; index < anchorList.getLength(); index++) {
			Element elem = anchorList.getItem(index);
			ElementWrapperPanel wrapper = new ElementWrapperPanel(elem);
			wrapper.addClickHandler(controller);
		}
		
		// Make this panel visible.
		setVisible(true);

		// Add composite widgets to root panels. 
		RootPanel.get(APP_DIV_ID).add(this);
		RootPanel.get(TITLE_DIV_ID).add(contentTitle);
		
		selectDisplay(AutoAlertDisplay.INSTANCE_INFO, MESSAGES.sidebarGeneralInfo());
		
	}
	
	/**
	 * GWT unfortunately does not have a getElementsByName() method,
	 * thus we must interface with native JavaScript directly.
	 * 
	 * @param name Name of the element to be found.
	 * @return List of matching nodes.
	 * 
	 */
	private native NodeList<Element> getElementsByName(String name) /*-{
	    return $doc.getElementsByName(name);
	}-*/;
	
}
