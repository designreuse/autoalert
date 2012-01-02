/**
 * 
 */
package info.geekinaction.autoalert.view;

import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author lcsontos
 * 
 */
public class AutoAlertControllerImpl implements IAutoAlertController {

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
		AsyncCallback<List<InstanceCpuUsage>> callback = new AutoAlertControllerAsyncCallback<List<InstanceCpuUsage>>(AutoAlertDisplay.STORAGE_DATAFILES);
		model.findInstanceCpuUsage(false, callback);
	}

	/**
	 * 
	 */
	public void onInstanceInfoRefresh() {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 */
	public void onInstanceIoUsageRefresh() {
		AsyncCallback<List<InstanceIoUsage>> callback = new AutoAlertControllerAsyncCallback<List<InstanceIoUsage>>(AutoAlertDisplay.STORAGE_DATAFILES);
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
		AsyncCallback<List<SessionCpuUsage>> callback = new AutoAlertControllerAsyncCallback<List<SessionCpuUsage>>(AutoAlertDisplay.STORAGE_DATAFILES);
		model.findSessionCpuUsage(callback);
	}

	/**
	 * 
	 */
	public void onSessionByIoRefresh() {
		AsyncCallback<List<SessionIoUsage>> callback = new AutoAlertControllerAsyncCallback<List<SessionIoUsage>>(AutoAlertDisplay.STORAGE_DATAFILES);
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

}
