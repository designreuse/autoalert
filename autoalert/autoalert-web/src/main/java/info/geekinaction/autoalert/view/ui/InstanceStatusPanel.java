/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.FormatUtil.formatDate;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;

import info.geekinaction.autoalert.model.domain.AbstractInstanceResourceUsage;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.view.AbstractAutoAlertPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.dt.AbstractDataTableModel;
import info.geekinaction.autoalert.view.dt.DataTable;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author lcsontos
 * 
 */
public class InstanceStatusPanel extends AbstractAutoAlertPanel {

	private DataTable<InstanceCpuUsage> dtInstanceCpuUsage;
	private DataTable<InstanceIoUsage> dtInstanceIoUsage;
	
	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {
		DecoratedTabPanel tabPanel = new DecoratedTabPanel();

		// Tablespaces
		dtInstanceCpuUsage = new DataTable<InstanceCpuUsage>();
		Panel vpInstanceCpuUsage = createContainer(dtInstanceCpuUsage, new ClickHandler() {
			public void onClick(ClickEvent event) {
				controller.onInstanceCpuUsageRefresh();
			}
		});
		
		// Datafiles
		dtInstanceIoUsage = new DataTable<InstanceIoUsage>();
		Panel vpInstanceIoUsage = createContainer(dtInstanceIoUsage, new ClickHandler() {
			public void onClick(ClickEvent event) {
				controller.onInstanceIoUsageRefresh();
			}
		});
		
		tabPanel.add(vpInstanceCpuUsage, MESSAGES.cpuUsageHistory());
		tabPanel.add(vpInstanceIoUsage, MESSAGES.ioUsagehistory());
		tabPanel.selectTab(0);
		
		return tabPanel;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void display(AutoAlertDisplay display, List<?> obj) {
		super.display(display, obj);
		switch (display) {
		case INSTANCE_CPU:
			List<InstanceCpuUsage> data1 = (List<InstanceCpuUsage>) obj;
			InstanceResourceUsageModel<InstanceCpuUsage> model1 = new InstanceResourceUsageModel<InstanceCpuUsage>(data1);
			dtInstanceCpuUsage.setModel(model1);			
			break;
		case INSTANCE_IO:
			List<InstanceIoUsage> data2 = (List<InstanceIoUsage>) obj;
			InstanceResourceUsageModel<InstanceIoUsage> model2 = new InstanceResourceUsageModel<InstanceIoUsage>(data2);
			dtInstanceIoUsage.setModel(model2);			
			break;
		}
	}

	/**
	 * 
	 */
	public void refresh() {
		controller.onInstanceCpuUsageRefresh();
		controller.onInstanceIoUsageRefresh();
	}
	
	/**
	 * 
	 * @author lcsontos
	 *
	 */
	private class InstanceResourceUsageModel<T extends AbstractInstanceResourceUsage> extends AbstractDataTableModel<T> {
		
		public InstanceResourceUsageModel(List<T> data) {
			super(data);
		}

		@Override
		protected void processTitles() {
			addTitle(MESSAGES.beginTime()); // 1
			addTitle(MESSAGES.endTime()); // 2
			// TODO
			addTitle("Resource usage"); // 3
			addTitle(MESSAGES.alert()); // 4
		}

		@Override
		protected void processData() {
			for (AbstractInstanceResourceUsage rec : data) {
				List<Object> record = new ArrayList<Object>();
				record.add(formatDate(rec.getBeginTime()));
				record.add(formatDate(rec.getEndTime()));
				record.add(rec.getValue());
				record.add(InstanceStatusPanel.this.createAlertImage(rec.getAlert()));
				cells.add(record);
			}
		}
	}
	
}
