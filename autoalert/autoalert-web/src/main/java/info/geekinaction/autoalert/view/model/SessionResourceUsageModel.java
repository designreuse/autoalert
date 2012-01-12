/**
 * 
 */
package info.geekinaction.autoalert.view.model;

import static info.geekinaction.autoalert.view.FormatUtil.formatDate;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import info.geekinaction.autoalert.model.domain.AbstractSessionResourceUsage;
import info.geekinaction.autoalert.view.dt.AbstractDataTableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Table model for session resource usage screens.
 *  
 * @author lcsontos
 *
 */
public class SessionResourceUsageModel<T extends AbstractSessionResourceUsage> extends AbstractDataTableModel<T> {

	public SessionResourceUsageModel() { }
	
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
