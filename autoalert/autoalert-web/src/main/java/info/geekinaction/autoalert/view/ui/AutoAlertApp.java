/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.ViewConstants.MAIN_DIV_ID;

import java.util.Iterator;
import java.util.Set;

import info.geekinaction.autoalert.model.service.IAutoAlertModel;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;
import info.geekinaction.autoalert.security.IAutoAlertSecurity;
import info.geekinaction.autoalert.security.IAutoAlertSecurityAsync;
import info.geekinaction.autoalert.view.AutoAlertControllerImpl;
import info.geekinaction.autoalert.view.AutoAlertViewImpl;
import info.geekinaction.autoalert.view.IAutoAlertController;
import info.geekinaction.autoalert.view.IAutoAlertView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.web.bindery.event.shared.UmbrellaException;
import com.google.gwt.user.client.Window;
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
	public AutoAlertApp() { 
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				showError(e);
			}
		});
	}

	/**
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		
		final IAutoAlertModelAsync model = GWT.create(IAutoAlertModel.class);
		final IAutoAlertSecurityAsync securityAsync = GWT.create(IAutoAlertSecurity.class);
		
		final IAutoAlertView view = new AutoAlertViewImpl(model);
		final IAutoAlertController controller = new AutoAlertControllerImpl(model, view);
		
		view.setActionListener(controller);
		view.init();
		
		RootPanel.get(MAIN_DIV_ID).add((Widget) view);
		
	}
	
	/**
	 * 
	 * @param t
	 */
	public static void showError(Throwable t) {
		if (t instanceof UmbrellaException) {
			Set<Throwable> causes = ((UmbrellaException) t).getCauses();
			for (Iterator<Throwable> iterator = causes.iterator(); iterator.hasNext();) {
				Throwable throwable = iterator.next();
				showError0(throwable);			
			}
			return;
		}
		showError0(t);
	}
	
	private static void showError0(Throwable t) {
		StringBuffer sb = new StringBuffer();
		sb.append(t.getMessage());
		sb.append('\n');
		for (StackTraceElement se : t.getStackTrace()) {
			sb.append(se.toString());
			sb.append('\n');
		}
		Window.alert(sb.toString());
	}
	
}
