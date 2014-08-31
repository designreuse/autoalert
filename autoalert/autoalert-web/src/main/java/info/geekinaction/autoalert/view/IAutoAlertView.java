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

/**
 * 
 * View interface.
 * 
 * @author lcsontos
 *
 */
public interface IAutoAlertView {

	/**
	 * Binds a controller to this view.
	 *  
	 * @param controller Controller to be bound.
	 */
	public void setActionListener(IAutoAlertController controller);
	
	/**
	 * Called by the controller when a view should show a specified display.
	 * 
	 * @param display Display to show.
	 * @param data Addition data.
	 */
	public void selectDisplay(AutoAlertDisplay display, Object data);
	
	/**
	 * Called by the controller when the specified display should be refreshed.
	 * 
	 * @param display  Display to refresh.
	 * @param obj Data to show on the refreshed view.
	 */
	public void refreshDisplay(AutoAlertDisplay display, Object data);
	
	/**
	 * Initialize this view. 
	 */
	public void init();
	
}
