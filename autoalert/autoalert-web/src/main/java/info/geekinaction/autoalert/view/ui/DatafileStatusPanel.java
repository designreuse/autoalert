/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.view.AbstractAutoAlertListPanel;
import info.geekinaction.autoalert.view.AutoAlertDisplay;
import info.geekinaction.autoalert.view.model.DatafileDataModel;

/**
 * @author lcsontos
 * 
 */
public class DatafileStatusPanel extends AbstractAutoAlertListPanel<Datafile, DatafileDataModel> {

	public DatafileStatusPanel() {
		super(AutoAlertDisplay.STORAGE_DATAFILES, new DatafileDataModel());
	}
	
	@Override
	protected void controllerCallback() {
		controller.onStorageDatafilesRefresh();
	}

}
