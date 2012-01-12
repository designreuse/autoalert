package info.geekinaction.autoalert.view.model;

import static info.geekinaction.autoalert.view.FormatUtil.formatNumber;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.view.AutoAlertPanelUtil;
import info.geekinaction.autoalert.view.dt.AbstractDataTableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Table model for data files.
 * 
 * @author lcsontos
 *
 */
public class DatafileDataModel  extends AbstractDataTableModel<Datafile> {

	public DatafileDataModel() { }
	
	/**
	 * 
	 * @param datafiles
	 */
	public DatafileDataModel(List<Datafile> datafiles) {
		super(datafiles);
	}

	/**
	 * 
	 */
	@Override
	protected void processTitles() {
		// Column titles
		//addTitle(MESSAGES.tablespaceName()); // 1
		addTitle(MESSAGES.datafiles()); // 2
		addTitle(MESSAGES.sizeMb()); // 3
		addTitle(MESSAGES.usedMb()); // 4
		addTitle(MESSAGES.usedPer()); // 5
		addTitle(MESSAGES.freeMb()); // 6
		addTitle(MESSAGES.sizeRemainMb()); // 7
		addTitle(MESSAGES.sizeRemainPer()); // 8
		addTitle(MESSAGES.alert()); // 9
	}
	
	@Override
	protected void processData() {
		for (Datafile rec : data) {
			List<Object> record = new ArrayList<Object>();

			//record.add(rec.getTablespaceName());
			record.add(rec.getFileName());
			record.add(formatNumber(rec.getSizeMb()));
			record.add(formatNumber(rec.getUsedMb()));
			record.add(formatNumber(rec.getUsedPer()));
			record.add(formatNumber(rec.getFreeMb()));
			record.add(formatNumber(rec.getSizeRemainMb()));
			record.add(formatNumber(rec.getSizeRemainPer()));
			record.add(AutoAlertPanelUtil.createAlertImage(rec.getAlert()));
			cells.add(record);
		}		
	}
	
}
