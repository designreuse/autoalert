/*
 * Copyright (C) 2010 - present, Laszlo Csontos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

/**
 * 
 */
package info.geekinaction.autoalert.view;

import info.geekinaction.autoalert.model.service.IAutoAlertModel;
import info.geekinaction.autoalert.model.service.IAutoAlertModelAsync;
import info.geekinaction.autoalert.security.IAutoAlertSecurity;
import info.geekinaction.autoalert.security.IAutoAlertSecurityAsync;

import java.util.Iterator;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.UmbrellaException;

/**
 * 
 * Entry point of this application
 * 
 * @author lcsontos
 *
 */
public final class AutoAlertApp implements EntryPoint {

	/**
	 * 
	 */
	public AutoAlertApp() {
		// Register uncaught exception handler
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
		
		// Instantiate async interfaces to RPC 
		final IAutoAlertModelAsync model = GWT.create(IAutoAlertModel.class);
		final IAutoAlertSecurityAsync securityAsync = GWT.create(IAutoAlertSecurity.class);
		
		// Create view
		final IAutoAlertView view = new AutoAlertViewImpl(model);
		
		// Create controller
		final IAutoAlertController controller = new AutoAlertControllerImpl(model, view);
		
		// Initialize view
		view.setActionListener(controller);
		view.init();
		
	}
	
	/**
	 * Primitive implementation of showing client-side exceptions.
	 * 
	 * @param t Throwable object to show.
	 */
	public static void showError(Throwable t) {
		// If T is an instance of UmbrellaException, show
		// every exception which it contains.
		if (t instanceof UmbrellaException) {
			Set<Throwable> causes = ((UmbrellaException) t).getCauses();
			for (Iterator<Throwable> iterator = causes.iterator(); iterator.hasNext();) {
				Throwable throwable = iterator.next();
				showError0(throwable);			
			}
			return;
		}
		
		// Otherwise show the single exception given.
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
