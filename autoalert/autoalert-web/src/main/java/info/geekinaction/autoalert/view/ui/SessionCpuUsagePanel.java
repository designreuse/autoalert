/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.view.AbstractAutoAlertListPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.model.SessionResourceUsageModel;

/**
 * @author lcsontos
 * 
 */
public class SessionCpuUsagePanel extends AbstractAutoAlertListPanel<SessionCpuUsage, SessionResourceUsageModel<SessionCpuUsage>> {
	
	public SessionCpuUsagePanel() {
		super(AutoAlertDisplay.SESSIONS_BY_CPU, new SessionResourceUsageModel<SessionCpuUsage>());
	}
	
	@Override
	protected void controllerCallback() {
		controller.onSessionByCpuRefresh();
	}
	
}
