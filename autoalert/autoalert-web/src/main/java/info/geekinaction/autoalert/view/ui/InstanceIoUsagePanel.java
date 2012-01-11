/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.AbstractInstanceResourceUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
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
public class InstanceIoUsagePanel extends AbstractAutoAlertPanel<List<? extends AbstractInstanceResourceUsage>> {

	private DataTable<InstanceIoUsage> dtInstanceIoUsage;
	
	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {
		// Tablespaces
		dtInstanceIoUsage = new DataTable<InstanceIoUsage>();
		Panel vpInstanceIoUsage = AutoAlertPanelUtil.createContainer(dtInstanceIoUsage, this.getClass().getName(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				refresh();
			}
		});
		
		return vpInstanceIoUsage;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void display(AutoAlertDisplay display, List<? extends AbstractInstanceResourceUsage> obj) {
		super.display(display, obj);

		if (!AutoAlertDisplay.INSTANCE_IO.equals(display)) {
			return;
		}
		
		List<InstanceIoUsage> data1 = (List<InstanceIoUsage>) obj;
		InstanceResourceUsageModel<InstanceIoUsage> model1 = new InstanceResourceUsageModel<InstanceIoUsage>(data1);
		dtInstanceIoUsage.setModel(model1);
		
		showLoaderImage(false);
		
	}

	/**
	 * 
	 */
	public void refresh() {
		showLoaderImage(true);
		controller.onInstanceIoUsageRefresh();
	}
	
}
