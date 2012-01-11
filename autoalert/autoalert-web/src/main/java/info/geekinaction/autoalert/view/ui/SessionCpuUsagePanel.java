/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.AbstractSessionResourceUsage;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
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
public class SessionCpuUsagePanel extends AbstractAutoAlertPanel<List<? extends AbstractSessionResourceUsage>> {

	private DataTable<SessionCpuUsage> dtSessionCpuUsage;

	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {

		// Tablespaces
		dtSessionCpuUsage = new DataTable<SessionCpuUsage>();
		Panel vpSessionCpuUsage = AutoAlertPanelUtil.createContainer(dtSessionCpuUsage, this.getClass().getName(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				refresh();
			}
		});
		
		
		return vpSessionCpuUsage;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void display(AutoAlertDisplay display, List<? extends AbstractSessionResourceUsage> obj) {
		super.display(display, obj);
		List<SessionCpuUsage> data1 = (List<SessionCpuUsage>) obj;
		SessionResourceUsageModel<SessionCpuUsage> model1 = new SessionResourceUsageModel<SessionCpuUsage>(data1);
		dtSessionCpuUsage.setModel(model1);
		showLoaderImage(false);
	}

	/**
	 * 
	 */
	public void refresh() {
		showLoaderImage(true);
		controller.onSessionByCpuRefresh();
	}
	
}
