/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.view.AbstractAutoAlertListPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.model.InstanceResourceUsageModel;

/**
 * @author lcsontos
 * 
 */
public class InstanceCpuUsagePanel extends AbstractAutoAlertListPanel<InstanceCpuUsage, InstanceResourceUsageModel<InstanceCpuUsage>> {

	public InstanceCpuUsagePanel() {
		super(AutoAlertDisplay.INSTANCE_CPU, new InstanceResourceUsageModel<InstanceCpuUsage>());
	}
	
	@Override
	protected void controllerCallback() {
		controller.onInstanceCpuUsageRefresh();
	}
	
}
