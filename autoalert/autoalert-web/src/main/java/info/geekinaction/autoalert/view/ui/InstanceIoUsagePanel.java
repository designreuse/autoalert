/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.view.AbstractAutoAlertListPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.model.InstanceResourceUsageModel;

/**
 * @author lcsontos
 * 
 */
public class InstanceIoUsagePanel extends AbstractAutoAlertListPanel<InstanceIoUsage, InstanceResourceUsageModel<InstanceIoUsage>> {

	public InstanceIoUsagePanel() {
		super(AutoAlertDisplay.INSTANCE_IO, new InstanceResourceUsageModel<InstanceIoUsage>());
	}
	
	@Override
	protected void controllerCallback() {
		controller.onInstanceIoUsageRefresh();
	}
	
}