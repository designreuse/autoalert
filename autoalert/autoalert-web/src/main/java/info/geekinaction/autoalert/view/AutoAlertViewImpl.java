/**
 * 
 */
package info.geekinaction.autoalert.view;

import static info.geekinaction.autoalert.view.ViewConstants.HEIGHT;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import static info.geekinaction.autoalert.view.ViewConstants.WIDTH;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;
import info.geekinaction.autoalert.view.ui.AutoAlertApp;
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
public class AutoAlertViewImpl extends AbstractAutoAlertPanel implements IAutoAlertView, SelectionHandler<Integer> {

	private IAutoAlertModelAsync model;
	private IAutoAlertController controller;

	private DecoratedTabPanel mainTabPanel;
	
	private AbstractAutoAlertPanel instanceStatusPanel;
	private AbstractAutoAlertPanel storageStatusPanel;
	private AbstractAutoAlertPanel sessionStatusPanel;
	
	final boolean initializedViews[] = new boolean[3];
	
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

		instanceStatusPanel = new InstanceStatusPanel();
		storageStatusPanel = new StorageStatusPanel();
		sessionStatusPanel = new SessionStatusPanel();
		
		instanceStatusPanel.registerController(controller);
		storageStatusPanel.registerController(controller);
		sessionStatusPanel.registerController(controller);

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
			case 0: instanceStatusPanel.refresh(); break;
			case 1:	storageStatusPanel.refresh(); break; 
			case 2: sessionStatusPanel.refresh(); break;
		}

		initializedViews[tabId] = true;
	}

	/**
	 * 
	 */
	public void display(AutoAlertDisplay display, List<?> data) { }

	/**
	 * 
	 */
	public void showDisplay(AutoAlertDisplay display, Object data) {
		switch (display) {
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
