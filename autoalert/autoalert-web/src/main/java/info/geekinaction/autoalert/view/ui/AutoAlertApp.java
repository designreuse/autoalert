/**
 * 
 */
package info.geekinaction.autoalert.view.ui;

import static info.geekinaction.autoalert.view.ViewConstants.APP_DIV_ID;
import info.geekinaction.autoalert.model.service.IAutoAlertModel;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;
import info.geekinaction.autoalert.security.IAutoAlertSecurity;
import info.geekinaction.autoalert.security.IAutoAlertSecurityAsync;
import info.geekinaction.autoalert.view.AutoAlertControllerImpl;
import info.geekinaction.autoalert.view.AutoAlertViewImpl;
import info.geekinaction.autoalert.view.IAutoAlertController;
import info.geekinaction.autoalert.view.IAutoAlertView;

import java.util.Iterator;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.UmbrellaException;

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
