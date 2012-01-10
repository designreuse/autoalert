/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.FormatUtil.formatDate;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;

import info.geekinaction.autoalert.model.domain.AbstractSessionResourceUsage;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
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
public class SessionStatusPanel extends AbstractAutoAlertPanel<List<? extends AbstractSessionResourceUsage>> {

	private DataTable<SessionCpuUsage> dtSessionCpuUsage;
	private DataTable<SessionIoUsage> dtSessionIoUsage;

	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {
		DecoratedTabPanel tabPanel = new DecoratedTabPanel();

		// Tablespaces
		dtSessionCpuUsage = new DataTable<SessionCpuUsage>();
		Panel vpSessionCpuUsage = createContainer(dtSessionCpuUsage, new ClickHandler() {
			public void onClick(ClickEvent event) {
				controller.onSessionByCpuRefresh();
			}
		});
		
		// Datafiles
		dtSessionIoUsage = new DataTable<SessionIoUsage>();
		Panel vpSessionIoUsage = createContainer(dtSessionIoUsage, new ClickHandler() {
			public void onClick(ClickEvent event) {
				controller.onSessionByIoRefresh();
			}
		});
		
		tabPanel.add(vpSessionCpuUsage, MESSAGES.cpuUsageHistory());
		tabPanel.add(vpSessionIoUsage, MESSAGES.ioUsagehistory());
		tabPanel.selectTab(0);
		
		return tabPanel;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void display(AutoAlertDisplay display, List<? extends AbstractSessionResourceUsage> obj) {
		super.display(display, obj);
		switch (display) {
		case SESSIONS_BY_CPU:
			List<SessionCpuUsage> data1 = (List<SessionCpuUsage>) obj;
			SessionResourceUsageModel<SessionCpuUsage> model1 = new SessionResourceUsageModel<SessionCpuUsage>(data1);
			dtSessionCpuUsage.setModel(model1);			
			break;
		case SESSION_BY_IO:
			List<SessionIoUsage> data2 = (List<SessionIoUsage>) obj;
			SessionResourceUsageModel<SessionIoUsage> model2 = new SessionResourceUsageModel<SessionIoUsage>(data2);
			dtSessionIoUsage.setModel(model2);			
			break;
		}
	}

	/**
	 * 
	 */
	public void refresh() {
		controller.onSessionByCpuRefresh();
		controller.onSessionByIoRefresh();
	}
	
	/**
	 * 
	 * @author lcsontos
	 *
	 */
	private class SessionResourceUsageModel<T extends AbstractSessionResourceUsage> extends AbstractDataTableModel<T> {
		
		public SessionResourceUsageModel(List<T> data) {
			super(data);
		}

		@Override
		protected void processTitles() {
			addTitle(MESSAGES.osProcessId()); // 1
			addTitle(MESSAGES.sessionId()); // 2
			addTitle(MESSAGES.serial()); // 3
			addTitle(MESSAGES.status()); // 4
			addTitle(MESSAGES.username()); // 5
			// addTitle(MESSAGES.program()); // 6
			addTitle(MESSAGES.module()); // 7
			addTitle(MESSAGES.machine()); // 8
			addTitle(MESSAGES.terminal()); // 9
			addTitle(MESSAGES.logonTime()); // 10
			addTitle("Value");
		}

		@Override
		protected void processData() {
			for (AbstractSessionResourceUsage rec : data) {
				List<Object> record = new ArrayList<Object>();
				record.add(rec.getSid()); // 1
				record.add(rec.getSid()); // 2
				record.add(rec.getSerial()); // 3
				record.add(rec.getStatus()); // 4 
				record.add(rec.getUsername()); // 5 
				// record.add(rec.getProgram()); // 6 
				record.add(rec.getModule()); // 7
				record.add(rec.getMachine()); // 8
				record.add(rec.getTerminal()); // 9 
				record.add(formatDate(rec.getLogonTime())); // 10
				record.add(rec.getValue());
				cells.add(record);
			}
		}
	}

}
