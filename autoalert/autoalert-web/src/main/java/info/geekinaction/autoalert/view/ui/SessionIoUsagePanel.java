/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.SessionIoUsage;
import info.geekinaction.autoalert.view.AbstractAutoAlertListPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.model.SessionResourceUsageModel;

/**
 * @author lcsontos
 * 
 */
public class SessionIoUsagePanel extends AbstractAutoAlertListPanel<SessionIoUsage, SessionResourceUsageModel<SessionIoUsage>> {
	
	public SessionIoUsagePanel() {
		super(AutoAlertDisplay.SESSION_BY_IO, new SessionResourceUsageModel<SessionIoUsage>());
	}
	
	@Override
	protected void controllerCallback() {
		controller.onSessionByIoRefresh();
	}

}
