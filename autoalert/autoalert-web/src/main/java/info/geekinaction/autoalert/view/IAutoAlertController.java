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

import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Interface of the view's controller.
 * 
 * @author lcsontos
 *
 */
public interface IAutoAlertController extends ClickHandler {

	////////////////////////////////////////////////////////////////
	// Login / Logout
	////////////////////////////////////////////////////////////////
	
	/**
	 * NOT YET IMPLEMENTED.
	 * 
	 * Executed on login.
	 * 
	 */
	void onLogin();
	
	/**
	 * NOT YET IMPLEMENTED.
	 * 
	 * Executed on logout.
	 */
	void onLogout();
	
	////////////////////////////////////////////////////////////////
	// Refresh instance information
	////////////////////////////////////////////////////////////////

	/**
	 * Executed by the view when it wants to show information about the instance.
	 */
	void onInstanceInfoRefresh();
	
	/**
	 * Executed by the view when it wants to show instance CPU usage.
	 */
	void onInstanceCpuUsageRefresh();
	
	/**
	 * Executed by the view when it wants to show instance I/O usage.
	 */
	void onInstanceIoUsageRefresh();
	
	////////////////////////////////////////////////////////////////
	// Refresh storage information
	////////////////////////////////////////////////////////////////
	
	/**
	 * Executed by the view when it wants to show tablespaces
	 */
	void onStorageTablespacesRefresh();
	
	/**
	 * Executed by the view when it wants to show data files.
	 */
	void onStorageDatafilesRefresh();

	////////////////////////////////////////////////////////////////
	// Refresh session information
	////////////////////////////////////////////////////////////////

	/**
	 * NOT YET IMPLEMENTED.
	 * 
	 * Executed by the view when it wants to show all the sessions.
	 */
	void onSessionAllRefresh();
	
	/**
	 * Executed by the view when it wants to show session CPU usage.
	 */
	void onSessionByCpuRefresh();
	
	/**
	 * Executed by the view when it wants to show session I/O usage.
	 */
	void onSessionByIoRefresh();
}
