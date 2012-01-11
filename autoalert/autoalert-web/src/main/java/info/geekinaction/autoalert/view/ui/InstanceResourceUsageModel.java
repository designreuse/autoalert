/**
 * 
 */
package info.geekinaction.autoalert.view.ui;
import static info.geekinaction.autoalert.view.FormatUtil.formatDate;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import info.geekinaction.autoalert.model.domain.AbstractInstanceResourceUsage;
import info.geekinaction.autoalert.view.dt.AbstractDataTableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lcsontos
 *
 */
public class InstanceResourceUsageModel<T extends AbstractInstanceResourceUsage> extends AbstractDataTableModel<T> {
	
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
			record.add(AutoAlertPanelUtil.createAlertImage(rec.getAlert()));
			cells.add(record);
		}
	}
}

