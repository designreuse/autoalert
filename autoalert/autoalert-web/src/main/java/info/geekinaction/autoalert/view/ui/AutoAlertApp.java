/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.ViewConstants.MAIN_DIV_ID;

import info.geekinaction.autoalert.model.service.IAutoAlertModel;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;
import info.geekinaction.autoalert.view.AutoAlertControllerImpl;
import info.geekinaction.autoalert.view.AutoAlertViewImpl;
import info.geekinaction.autoalert.view.IAutoAlertController;
import info.geekinaction.autoalert.view.IAutoAlertView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author lcsontos
 *
 */
public final class AutoAlertApp implements EntryPoint {

	/**
	 * 
	 */
	public AutoAlertApp() { }

	/**
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		final IAutoAlertModelAsync model = GWT.create(IAutoAlertModel.class);
		final IAutoAlertView view = new AutoAlertViewImpl(model);
		final IAutoAlertController controller = new AutoAlertControllerImpl(model, view);
		
		view.addActionListener(controller);
		view.init();
		
		RootPanel.get(MAIN_DIV_ID).add((Widget) view);
	}
	

}
