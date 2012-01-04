/**
 * 
 */
package info.geekinaction.autoalert.view;

import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import static info.geekinaction.autoalert.view.ViewConstants.WIDTH;
import static info.geekinaction.autoalert.view.ViewConstants.HEIGHT;

import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;
import info.geekinaction.autoalert.view.ui.InstanceStatusPanel;
import info.geekinaction.autoalert.view.ui.SessionStatusPanel;
import info.geekinaction.autoalert.view.ui.StorageStatusPanel;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratedTabPanel;

/**
 * @author lcsontos
 * 
 */
public class AutoAlertViewImpl extends AbstractAutoAlertPanel implements IAutoAlertView {

	private IAutoAlertModelAsync model;
	private IAutoAlertController controller;

	private DecoratedTabPanel mainTabPanel;
	private AbstractAutoAlertPanel instanceStatusPanel;
	private AbstractAutoAlertPanel storageStatusPanel;
	private AbstractAutoAlertPanel sessionStatusPanel;

	/**
	 * 
	 */
	public AutoAlertViewImpl(IAutoAlertModelAsync model) {
		this.model = model;
	}

	/**
	 * 
	 */
	public void addActionListener(IAutoAlertController controller) {
		this.controller = controller;
		instanceStatusPanel.registerController(controller);
		storageStatusPanel.registerController(controller);
		sessionStatusPanel.registerController(controller);
	}

	/**
	 * 
	 * @return
	 */
	public AbstractAutoAlertPanel getInstanceStatusPanel() {
		return instanceStatusPanel;
	}

	/**
	 * 
	 * @return
	 */
	public AbstractAutoAlertPanel getStorageStatusPanel() {
		return storageStatusPanel;
	}

	/**
	 * 
	 * @return
	 */
	public AbstractAutoAlertPanel getSessionStatusPanel() {
		return sessionStatusPanel;
	}

	/**
	 * 
	 */
	@Override
	public void buildPanel() {
		mainTabPanel = new DecoratedTabPanel();
		mainTabPanel.setAnimationEnabled(true);
		mainTabPanel.setWidth(WIDTH);
		mainTabPanel.setHeight(HEIGHT);

		instanceStatusPanel = new InstanceStatusPanel();
		storageStatusPanel = new StorageStatusPanel();
		sessionStatusPanel = new SessionStatusPanel();

		mainTabPanel.add(instanceStatusPanel, MESSAGES.instance());
		mainTabPanel.add(storageStatusPanel, MESSAGES.storage());
		mainTabPanel.add(sessionStatusPanel, MESSAGES.sessions());
		mainTabPanel.selectTab(0);

		add(mainTabPanel);
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
		}

	}

	/**
	 * 
	 */
	@Override
	public void showError(Throwable t) {
		StringBuffer sb = new StringBuffer();
		sb.append(t.getMessage());
		sb.append('\n');
		for (StackTraceElement se : t.getStackTrace()) {
			sb.append(se.toString());
			sb.append('\n');
		}
		Window.alert(sb.toString());
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
		refresh();
	}

}
