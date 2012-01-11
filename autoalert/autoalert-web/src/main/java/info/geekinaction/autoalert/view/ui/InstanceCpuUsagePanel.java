/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.AbstractInstanceResourceUsage;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.view.AbstractAutoAlertPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.dt.DataTable;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author lcsontos
 * 
 */
public class InstanceCpuUsagePanel extends AbstractAutoAlertPanel<List<? extends AbstractInstanceResourceUsage>> {

	private DataTable<InstanceCpuUsage> dtInstanceCpuUsage;
	
	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {
		// Tablespaces
		dtInstanceCpuUsage = new DataTable<InstanceCpuUsage>();
		Panel vpInstanceCpuUsage = AutoAlertPanelUtil.createContainer(dtInstanceCpuUsage, this.getClass().getName(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				refresh();
			}
		});
		
		return vpInstanceCpuUsage;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void display(AutoAlertDisplay display, List<? extends AbstractInstanceResourceUsage> obj) {
		super.display(display, obj);

		if (!AutoAlertDisplay.INSTANCE_CPU.equals(display)) {
			return;
		}
		
		List<InstanceCpuUsage> data1 = (List<InstanceCpuUsage>) obj;
		InstanceResourceUsageModel<InstanceCpuUsage> model1 = new InstanceResourceUsageModel<InstanceCpuUsage>(data1);
		dtInstanceCpuUsage.setModel(model1);
		
		showLoaderImage(false);
		
	}

	/**
	 * 
	 */
	public void refresh() {
		showLoaderImage(true);
		controller.onInstanceCpuUsageRefresh();
	}
	
}
