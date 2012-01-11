/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.FormatUtil.formatNumber;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;

import info.geekinaction.autoalert.model.domain.AbstractStorage;
import info.geekinaction.autoalert.model.domain.Tablespace;
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
public class TablespaceStatusPanel extends AbstractAutoAlertPanel<List<? extends AbstractStorage>> {

	private DataTable<Tablespace> dtTablespaces;

	/**
	 * 
	 */
	@Override
	protected Widget createWidget() {

		// Tablespaces
		dtTablespaces = new DataTable<Tablespace>();
		Panel vpTablespaces = AutoAlertPanelUtil.createContainer(dtTablespaces, this.getClass().getName(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				refresh();
			}
		});
		
		return vpTablespaces;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void display(AutoAlertDisplay display, List<? extends AbstractStorage> obj) {
		super.display(display, obj);
		List<Tablespace> tablespaces = (List<Tablespace>) obj;
		TablespaceDataModel tablespaceDataModel = new TablespaceDataModel(tablespaces);
		dtTablespaces.setModel(tablespaceDataModel);
		showLoaderImage(false);
	}

	/**
	 * 
	 */
	public void refresh() {
		showLoaderImage(true);
		controller.onStorageTablespacesRefresh();
	}

	/**
	 * 
	 * @author csontosbl
	 * 
	 */
	private class TablespaceDataModel extends AbstractDataTableModel<Tablespace> {

		/**
		 * 
		 * @param tablespaces
		 */
		public TablespaceDataModel(List<Tablespace> tablespaces) {
			super(tablespaces);
		}

		/**
		 * 
		 */
		@Override
		protected void processTitles() {
			// Column titles
			addTitle(MESSAGES.tablespaceName()); // 1
			addTitle(MESSAGES.sizeMb()); // 2
			addTitle(MESSAGES.usedMb()); // 3
			addTitle(MESSAGES.usedPer()); // 4
			addTitle(MESSAGES.freeMb()); // 5
			addTitle(MESSAGES.sizeRemainMb()); // 6
			addTitle(MESSAGES.sizeRemainPer()); // 7
			addTitle(MESSAGES.alert()); // 8
		}
		
		/**
		 * 
		 */
		@Override
		protected void processData() {
			for (Tablespace rec : data) {
				List<Object> record = new ArrayList<Object>();

				record.add(rec.getTablespaceName());
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
