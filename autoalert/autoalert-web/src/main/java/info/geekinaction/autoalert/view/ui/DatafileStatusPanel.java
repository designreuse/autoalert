/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.FormatUtil.formatNumber;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import info.geekinaction.autoalert.model.domain.AbstractStorage;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.view.AbstractAutoAlertPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.dt.AbstractDataTableModel;
import info.geekinaction.autoalert.view.dt.DataTable;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author lcsontos
 * 
 */
public class DatafileStatusPanel extends AbstractAutoAlertPanel<List<? extends AbstractStorage>> {

	private DataTable<Datafile> dtDatafiles;

	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {

		// Datafiles
		dtDatafiles = new DataTable<Datafile>();
		Panel vpDatafiles = AutoAlertPanelUtil.createContainer(dtDatafiles, this.getClass().getName(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				refresh();
			}
		});
		
		return vpDatafiles;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void display(AutoAlertDisplay display, List<? extends AbstractStorage> obj) {
		super.display(display, obj);
		List<Datafile> datafiles = (List<Datafile>) obj;
		DatafileDataModel model = new DatafileDataModel(datafiles);
		dtDatafiles.setModel(model);
		showLoaderImage(false);
	}

	/**
	 * 
	 */
	public void refresh() {
		showLoaderImage(true);
		controller.onStorageDatafilesRefresh();
	}

	/**
	 * 
	 * @author csontosbl
	 * 
	 */
	private class DatafileDataModel extends AbstractDataTableModel<Datafile> {

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

}
