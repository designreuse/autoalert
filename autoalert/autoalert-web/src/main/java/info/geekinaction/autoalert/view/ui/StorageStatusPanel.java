/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.FormatUtil.formatNumber;
import static info.geekinaction.autoalert.view.ViewConstants.HEIGHT;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_URL_REFRESH;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import static info.geekinaction.autoalert.view.ViewConstants.WIDTH;
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

import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
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
		
		ButtonBase btnRefreshTablespaces = new PushButton(new Image(IMG_URL_REFRESH));
		btnRefreshTablespaces.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				controller.onStorageTablespacesRefresh();
			}
		});

		VerticalPanel vpTablespaces = new VerticalPanel();
		vpTablespaces.setSpacing(10);
		vpTablespaces.add(btnRefreshTablespaces);
		vpTablespaces.add(spTablespaces);

		// Datafiles
		dtDatafiles = new DataTable<Datafile>();
		ScrollPanel spDatafiles = new ScrollPanel(dtDatafiles);
		spDatafiles.setHeight(HEIGHT);
		spDatafiles.setWidth(WIDTH);

		ButtonBase btnRefreshDatafiles = new PushButton(new Image(IMG_URL_REFRESH));
		btnRefreshDatafiles.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				controller.onStorageDatafilesRefresh();
			}
		});

		VerticalPanel vpDatafiles = new VerticalPanel();
		vpDatafiles.setSpacing(10);
		vpDatafiles.add(btnRefreshDatafiles);
		vpDatafiles.add(spDatafiles);

		tabPanel.add(vpTablespaces, MESSAGES.tablespaces());
		tabPanel.add(vpDatafiles, MESSAGES.datafiles());
		tabPanel.selectTab(0);

		add(tabPanel);
	}

	public void display(AutoAlertDisplay display, List<?> obj) {
		super.display(display, obj);
		switch (display) {
		case STORAGE_TABLESPACES:
			List<Tablespace> tablespaces = (List<Tablespace>) obj;
			TablespaceDataModel tablespaceDataModel = new TablespaceDataModel(tablespaces);
			dtTablespaces.setModel(tablespaceDataModel);			
			break;
		case STORAGE_DATAFILES:
			List<Datafile> datafiles = (List<Datafile>) obj;
			DatafileDataModel model = new DatafileDataModel(datafiles);
			dtDatafiles.setModel(model);
			break;
		}
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
				record.add(rec.getAlert());
				cells.add(record);
			}
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
		protected void processTitles() {
			// Column titles
			addTitle(MESSAGES.tablespaceName()); // 1
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

				record.add(rec.getTablespaceName());
				record.add(rec.getFileName());
				record.add(formatNumber(rec.getSizeMb()));
				record.add(formatNumber(rec.getUsedMb()));
				record.add(formatNumber(rec.getUsedPer()));
				record.add(formatNumber(rec.getFreeMb()));
				record.add(formatNumber(rec.getSizeRemainMb()));
				record.add(formatNumber(rec.getSizeRemainPer()));
				record.add(rec.getAlert());
				cells.add(record);
			}		
		}

	}

}
