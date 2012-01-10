/**
 * 
 */
package info.geekinaction.autoalert.view;

import static info.geekinaction.autoalert.view.ViewConstants.HEIGHT;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import static info.geekinaction.autoalert.view.ViewConstants.WIDTH;
import info.geekinaction.autoalert.model.domain.Database;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;
import info.geekinaction.autoalert.view.ui.AutoAlertApp;
import info.geekinaction.autoalert.view.ui.InstanceInfoPanel;
import info.geekinaction.autoalert.view.ui.InstanceStatusPanel;
import info.geekinaction.autoalert.view.ui.SessionStatusPanel;
import info.geekinaction.autoalert.view.ui.StorageStatusPanel;

import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author lcsontos
 * 
 */
public class AutoAlertViewImpl extends AbstractAutoAlertPanel<Object> implements IAutoAlertView, SelectionHandler<Integer> {

	private IAutoAlertModelAsync model;
	private IAutoAlertController controller;

	private DecoratedTabPanel mainTabPanel;
	
	private InstanceInfoPanel instanceInfoPanel;
	private InstanceStatusPanel instanceStatusPanel;
	private StorageStatusPanel storageStatusPanel;
	private SessionStatusPanel sessionStatusPanel;
	
	final boolean initializedViews[] = new boolean[4];
	
	/**
	 * 
	 */
	public AutoAlertViewImpl(IAutoAlertModelAsync model) {
		super(false);
		this.model = model;
	}

	/**
	 * 
	 */
	public void setActionListener(IAutoAlertController controller) {
		this.controller = controller;
	}

	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {
		
		mainTabPanel = new DecoratedTabPanel();
		mainTabPanel.setAnimationEnabled(true);
		mainTabPanel.setWidth(WIDTH);
		mainTabPanel.setHeight(HEIGHT);

		instanceInfoPanel = new InstanceInfoPanel();
		instanceStatusPanel = new InstanceStatusPanel();
		storageStatusPanel = new StorageStatusPanel();
		sessionStatusPanel = new SessionStatusPanel();
		
		instanceInfoPanel.registerController(controller);
		instanceStatusPanel.registerController(controller);
		storageStatusPanel.registerController(controller);
		sessionStatusPanel.registerController(controller);

		mainTabPanel.add(instanceInfoPanel, "Instance info");
		mainTabPanel.add(instanceStatusPanel, MESSAGES.instance());
		mainTabPanel.add(storageStatusPanel, MESSAGES.storage());
		mainTabPanel.add(sessionStatusPanel, MESSAGES.sessions());
		mainTabPanel.addSelectionHandler(this);
		mainTabPanel.selectTab(0);

		return mainTabPanel;
	}
	
	@Override
	public void onSelection(SelectionEvent<Integer> event) {
		
		Integer tabId = event.getSelectedItem();
		if (initializedViews[tabId]) {
			return;
		}
		
		switch (tabId) {
			case 0: instanceInfoPanel.refresh(); break; 
			case 1: instanceStatusPanel.refresh(); break;
			case 2:	storageStatusPanel.refresh(); break; 
			case 3: sessionStatusPanel.refresh(); break;
		}

		initializedViews[tabId] = true;
	}

	/**
	 * 
	 */
	public void display(AutoAlertDisplay display, Object data) { }

	/**
	 * 
	 */
	public void showDisplay(AutoAlertDisplay display, Object data) {
		switch (display) {
		case INSTANCE_INFO:
			Database instance = (Database) data;
			instanceInfoPanel.display(display, instance);
			break;
		case STORAGE_TABLESPACES:
			List<Tablespace> tablespaces = (List<Tablespace>) data;
			storageStatusPanel.display(display, tablespaces);
			break;
		case STORAGE_DATAFILES:
			List<Datafile> datafiles = (List<Datafile>) data;
			storageStatusPanel.display(display, datafiles);
			break;
		case INSTANCE_CPU:
			List<InstanceCpuUsage> instanceCpuUsages = (List<InstanceCpuUsage>) data;
			instanceStatusPanel.display(display, instanceCpuUsages);
			break;
		case INSTANCE_IO:
			List<InstanceIoUsage> instanceIoUsages = (List<InstanceIoUsage>) data;
			instanceStatusPanel.display(display, instanceIoUsages);
			break;
		case SESSIONS_BY_CPU:
			List<SessionCpuUsage> sessionCpuUsages = (List<SessionCpuUsage>) data;
			sessionStatusPanel.display(display, sessionCpuUsages);
			break;
		case SESSION_BY_IO:
			List<SessionIoUsage> sessionIoUsages = (List<SessionIoUsage>) data;
			sessionStatusPanel.display(display, sessionIoUsages);
			break;
		}

	}

	/**
	 * 
	 */
	@Override
	public void showError(Throwable t) {
		AutoAlertApp.showError(t);
	}

	/**
	 * 
	 */
	public void refresh() {
		instanceStatusPanel.refresh();
		storageStatusPanel.refresh();
		sessionStatusPanel.refresh();
	}

	/**
	 * 
	 */
	public void init() {
		setVisible(true);
	}

}
