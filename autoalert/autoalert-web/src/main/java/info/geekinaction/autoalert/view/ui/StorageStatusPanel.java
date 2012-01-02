/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.Constants.MESSAGES;
import static info.geekinaction.autoalert.view.IAutoAlertView.HEIGHT;
import static info.geekinaction.autoalert.view.IAutoAlertView.WIDTH;

import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.view.AbstractAutoAlertPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.dt.AbstractDataTableModel;
import info.geekinaction.autoalert.view.dt.DataTable;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author lcsontos
 * 
 */
public class StorageStatusPanel extends AbstractAutoAlertPanel {

	private DataTable<Tablespace> dtTablespaces;
	private DataTable<Datafile> dtDatafiles;

	public StorageStatusPanel() {
		super();
	}

	/**
	 * 
	 */
	@Override
	public void buildPanel() {
		DecoratedTabPanel tabPanel = new DecoratedTabPanel();

		// Tablespaces
		dtTablespaces = new DataTable<Tablespace>();
		ScrollPanel spTablespaces = new ScrollPanel(dtTablespaces);
		spTablespaces.setHeight(HEIGHT);
		spTablespaces.setWidth(WIDTH);
		
		Button btnRefreshTablespaces = new Button(MESSAGES.refresh());
		btnRefreshTablespaces.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				controller.onStorageTablespacesRefresh();
			}
		});

		VerticalPanel vpTablespaces = new VerticalPanel();
		vpTablespaces.add(btnRefreshTablespaces);
		vpTablespaces.add(spTablespaces);

		// Datafiles
		dtDatafiles = new DataTable<Datafile>();
		ScrollPanel spDatafiles = new ScrollPanel(dtDatafiles);
		spDatafiles.setHeight(HEIGHT);
		spDatafiles.setWidth(WIDTH);

		Button btnRefreshDatafiles = new Button(MESSAGES.refresh());
		btnRefreshDatafiles.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				controller.onStorageDatafilesRefresh();
			}
		});

		VerticalPanel vpDatafiles = new VerticalPanel();
		vpDatafiles.add(btnRefreshDatafiles);
		vpDatafiles.add(spDatafiles);

		tabPanel.add(vpTablespaces, getMessages().tablespaces());
		tabPanel.add(vpDatafiles, getMessages().datafiles());
		tabPanel.selectTab(0);

		add(tabPanel);
	}

	public void display(AutoAlertDisplay display, List<?> obj) {
		if (!(obj instanceof List))
			return;

		List list = (List) obj;
		if (list == null || list.size() == 0)
			return;

		Object first = list.get(0);
		if (first instanceof Tablespace) {
			List<Tablespace> tablespaces = (List<Tablespace>) obj;
			TablespaceDataModel model = new TablespaceDataModel(tablespaces);
			dtTablespaces.setModel(model);
		} else if (first instanceof Datafile) {
			List<Datafile> datafiles = (List<Datafile>) obj;
			DatafileDataModel model = new DatafileDataModel(datafiles);
			dtDatafiles.setModel(model);
		} else
			return;

	}

	/**
	 * 
	 */
	public void refresh() {
		controller.onStorageTablespacesRefresh();
		controller.onStorageDatafilesRefresh();
	}

	/**
	 * 
	 * @author csontosbl
	 * 
	 */
	private static class TablespaceDataModel extends AbstractDataTableModel<Tablespace> {

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
		protected void init() {
			clear();

			// Column titles
			addTitle(MESSAGES.tablespaceName()); // 1
			addTitle(MESSAGES.sizeMb()); // 2
			addTitle(MESSAGES.usedMb()); // 3
			addTitle(MESSAGES.usedPer()); // 4
			addTitle(MESSAGES.freeMb()); // 5
			addTitle(MESSAGES.sizeRemainMb()); // 6
			addTitle(MESSAGES.sizeRemainPer()); // 7
			addTitle(MESSAGES.alert()); // 8

			// Data
			for (Tablespace ts : data) {
				List<Object> record = new ArrayList<Object>();

				record.add(ts.getTablespaceName());
				record.add(format(ts.getSizeMb()));
				record.add(format(ts.getUsedMb()));
				record.add(format(ts.getUsedPer()));
				record.add(format(ts.getFreeMb()));
				record.add(format(ts.getSizeRemainMb()));
				record.add(format(ts.getSizeRemainPer()));
				record.add("X");
				cells.add(record);
			}
		}

		//
		private String format(Float number/* , NumberFormat format */) {
			if (number == null) {
				return null;
			}
			return NumberFormat.getDecimalFormat().format(number);
		}

	}

	/**
	 * 
	 * @author csontosbl
	 * 
	 */
	private static class DatafileDataModel extends AbstractDataTableModel<Datafile> {

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
		protected void init() {
			// TODO Auto-generated method stub

		}

	}

}
