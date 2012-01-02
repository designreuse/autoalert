/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import java.util.List;

import info.geekinaction.autoalert.view.AbstractAutoAlertPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author lcsontos
 * 
 */
public class SessionStatusPanel extends AbstractAutoAlertPanel {

	/**
	 * 
	 */
	public SessionStatusPanel() {
		/*
		 * add(createAllSessionsPanel(), "ALL"); add(createSessionsByCpuPanel(),
		 * "By CPU"); add(createSessionsByIoPanel(), "By I/O");
		 */
	}

	private Widget createAllSessionsPanel() {
		return new SimplePanel();
	}

	private Widget createSessionsByCpuPanel() {
		return new SimplePanel();
	}

	private Widget createSessionsByIoPanel() {
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
