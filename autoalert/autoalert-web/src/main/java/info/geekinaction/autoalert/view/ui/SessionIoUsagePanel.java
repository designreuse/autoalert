/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.AbstractSessionResourceUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
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
public class SessionIoUsagePanel extends AbstractAutoAlertPanel<List<? extends AbstractSessionResourceUsage>> {
	private DataTable<SessionIoUsage> dtSessionIoUsage;

	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {

		// Tablespaces
		dtSessionIoUsage = new DataTable<SessionIoUsage>();
		Panel vpSessionIoUsage = AutoAlertPanelUtil.createContainer(dtSessionIoUsage, this.getClass().getName(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				refresh();
			}
		});
		
		return vpSessionIoUsage;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void display(AutoAlertDisplay display, List<? extends AbstractSessionResourceUsage> obj) {
		super.display(display, obj);
		List<SessionIoUsage> data1 = (List<SessionIoUsage>) obj;
		SessionResourceUsageModel<SessionIoUsage> model1 = new SessionResourceUsageModel<SessionIoUsage>(data1);
		dtSessionIoUsage.setModel(model1);
		showLoaderImage(false);
	}

	/**
	 * 
	 */
	public void refresh() {
		showLoaderImage(true);
		controller.onSessionByIoRefresh();
	}

}
