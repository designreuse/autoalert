/**
 * 
 */
package info.geekinaction.autoalert.view;

import static info.geekinaction.autoalert.view.ViewConstants.*;
import static info.geekinaction.autoalert.view.AutoAlertDisplay.*;

import info.geekinaction.autoalert.model.domain.Database;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;
import info.geekinaction.autoalert.view.ui.AutoAlertApp;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
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
			view.showError(throwable);
		}
		
		/**
		 * 
		 */
		public void onSuccess(T data) {
			view.showDisplay(display, data);
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
	 * 
	 */
	public void onLogin() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public void onLogout() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public void onInstanceCpuUsageRefresh() {
		AsyncCallback<List<InstanceCpuUsage>> callback = new AutoAlertControllerAsyncCallback<List<InstanceCpuUsage>>(AutoAlertDisplay.INSTANCE_CPU);
		model.findInstanceCpuUsage(false, callback);
	}

	/**
	 * 
	 */
	public void onInstanceInfoRefresh() {
		AsyncCallback<Database> callback = new AutoAlertControllerAsyncCallback<Database>(AutoAlertDisplay.INSTANCE_INFO);
		model.findDatabase(callback);
	}

	/**
	 * 
	 */
	public void onInstanceIoUsageRefresh() {
		AsyncCallback<List<InstanceIoUsage>> callback = new AutoAlertControllerAsyncCallback<List<InstanceIoUsage>>(AutoAlertDisplay.INSTANCE_IO);
		model.findInstanceIoUsage(false, callback);
	}

	/**
	 * 
	 */
	public void onSessionAllRefresh() {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 */
	public void onSessionByCpuRefresh() {
		AsyncCallback<List<SessionCpuUsage>> callback = new AutoAlertControllerAsyncCallback<List<SessionCpuUsage>>(AutoAlertDisplay.SESSIONS_BY_CPU);
		model.findSessionCpuUsage(callback);
	}

	/**
	 * 
	 */
	public void onSessionByIoRefresh() {
		AsyncCallback<List<SessionIoUsage>> callback = new AutoAlertControllerAsyncCallback<List<SessionIoUsage>>(AutoAlertDisplay.SESSION_BY_IO);
		model.findSessionIoUsage(callback);
	}

	/**
	 * 
	 */
	public void onStorageDatafilesRefresh() {
		AsyncCallback<List<Datafile>> callback = new AutoAlertControllerAsyncCallback<List<Datafile>>(AutoAlertDisplay.STORAGE_DATAFILES);
		model.findDatafiles(false, callback);
	}

	/**
	 * 
	 */
	public void onStorageTablespacesRefresh() {
		AsyncCallback<List<Tablespace>> callback = new AutoAlertControllerAsyncCallback<List<Tablespace>>(AutoAlertDisplay.STORAGE_TABLESPACES);
		model.findTablespaces(false, callback);
	}
	
	/**
	 * 
	 */
	@Override
	public void onClick(ClickEvent event) {
		AutoAlertDisplay display = null;

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
			view.display(AutoAlertDisplay.valueOf(id), elem.getInnerHTML());
		} catch (Exception e) {
			AutoAlertApp.showError(e);
		}
	}
	

}
