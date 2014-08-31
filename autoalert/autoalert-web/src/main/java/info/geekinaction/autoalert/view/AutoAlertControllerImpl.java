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

import info.geekinaction.autoalert.model.domain.Database;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * Controller implementation.
 * 
 * @author lcsontos
 * 
 */
public class AutoAlertControllerImpl implements IAutoAlertController  {

	private IAutoAlertModelAsync model;
	private IAutoAlertView view;
	
	/**
	 * 
	 * @author csontosbl
	 *
	 */
	private class AutoAlertControllerAsyncCallback<T> implements AsyncCallback<T> {
		
		private AutoAlertDisplay display;
		
		/**
		 * 
		 * @param display
		 */
		public AutoAlertControllerAsyncCallback(AutoAlertDisplay display) {
			this.display = display;
		}
		
		/**
		 * 
		 */
		public void onFailure(Throwable throwable) {
			AutoAlertApp.showError(throwable);
		}
		
		/**
		 * 
		 */
		public void onSuccess(T data) {
			view.refreshDisplay(display, data);
		}
		
	}

	/**
	 * 
	 */
	public AutoAlertControllerImpl(IAutoAlertModelAsync model, IAutoAlertView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * @see IAutoAlertController#onLogin()
	 */
	public void onLogin() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IAutoAlertController#onLogout()
	 */
	public void onLogout() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IAutoAlertController#onInstanceCpuUsageRefresh()
	 */
	public void onInstanceCpuUsageRefresh() {
		AsyncCallback<List<InstanceCpuUsage>> callback = new AutoAlertControllerAsyncCallback<List<InstanceCpuUsage>>(AutoAlertDisplay.INSTANCE_CPU);
		model.findInstanceCpuUsage(false, callback);
	}

	/**
	 * @see IAutoAlertController#onInstanceInfoRefresh()
	 */
	public void onInstanceInfoRefresh() {
		AsyncCallback<Database> callback = new AutoAlertControllerAsyncCallback<Database>(AutoAlertDisplay.INSTANCE_INFO);
		model.findDatabase(callback);
	}

	/**
	 * @see IAutoAlertController#onInstanceIoUsageRefresh()
	 */
	public void onInstanceIoUsageRefresh() {
		AsyncCallback<List<InstanceIoUsage>> callback = new AutoAlertControllerAsyncCallback<List<InstanceIoUsage>>(AutoAlertDisplay.INSTANCE_IO);
		model.findInstanceIoUsage(false, callback);
	}

	/**
	 * @see IAutoAlertController#onSessionAllRefresh()
	 */
	public void onSessionAllRefresh() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see IAutoAlertController#onSessionByCpuRefresh()
	 */
	public void onSessionByCpuRefresh() {
		AsyncCallback<List<SessionCpuUsage>> callback = new AutoAlertControllerAsyncCallback<List<SessionCpuUsage>>(AutoAlertDisplay.SESSIONS_BY_CPU);
		model.findSessionCpuUsage(callback);
	}

	/**
	 * @see IAutoAlertController#onSessionByIoRefresh()
	 */
	public void onSessionByIoRefresh() {
		AsyncCallback<List<SessionIoUsage>> callback = new AutoAlertControllerAsyncCallback<List<SessionIoUsage>>(AutoAlertDisplay.SESSION_BY_IO);
		model.findSessionIoUsage(callback);
	}

	/**
	 * @see IAutoAlertController#onStorageDatafilesRefresh()
	 */
	public void onStorageDatafilesRefresh() {
		AsyncCallback<List<Datafile>> callback = new AutoAlertControllerAsyncCallback<List<Datafile>>(AutoAlertDisplay.STORAGE_DATAFILES);
		model.findDatafiles(false, callback);
	}

	/**
	 * @see IAutoAlertController#onStorageTablespacesRefresh()
	 */
	public void onStorageTablespacesRefresh() {
		AsyncCallback<List<Tablespace>> callback = new AutoAlertControllerAsyncCallback<List<Tablespace>>(AutoAlertDisplay.STORAGE_TABLESPACES);
		model.findTablespaces(false, callback);
	}
	
	/**
	 * Execute by the view when the user clicks on
	 * one of the links on the sidebar.
	 */
	@Override
	public void onClick(ClickEvent event) {
		
		// Sanity check.
		Object src = event.getSource();
		if (!(src instanceof ElementWrapperPanel)) {
			return;
		}
		
		// Decide with panel to show.
		ElementWrapperPanel wrapper = (ElementWrapperPanel) src;
		Element elem = wrapper.getElement();
		String id = elem.getId();
		
		try {
			view.selectDisplay(AutoAlertDisplay.valueOf(id), elem.getInnerHTML());
		} catch (Exception e) {
			AutoAlertApp.showError(e);
		}
		
	}
	

}
