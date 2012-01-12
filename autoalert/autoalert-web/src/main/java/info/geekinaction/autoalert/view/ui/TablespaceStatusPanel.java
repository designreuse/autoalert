/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.view.AbstractAutoAlertListPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.model.TablespaceDataModel;

/**
 * @author lcsontos
 * 
 */
public class TablespaceStatusPanel extends AbstractAutoAlertListPanel<Tablespace, TablespaceDataModel> {

	public TablespaceStatusPanel() {
		super(AutoAlertDisplay.STORAGE_TABLESPACES, new TablespaceDataModel());
	}
	
	@Override
	protected void controllerCallback() {
		controller.onStorageTablespacesRefresh();
	}

}
