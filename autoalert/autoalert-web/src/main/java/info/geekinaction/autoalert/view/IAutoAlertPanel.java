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
 * Interface of panels
 * 
 * @param <T> Object type to display (eg. List<Tablespace>).
 *
 */
public interface IAutoAlertPanel<T> {

	/**
	 * Register the specified controller to this panel.
	 * 
	 * @param controller Application's controller.
	 */
	public void registerController(IAutoAlertController controller);
    
    /**
     * Show the given panel with the given display name within this panel.
     * @param display Display to show.
     * @param obj Object to display.
     */
	public void display(AutoAlertDisplay display, T obj);
    
    /**
     * Refresh the contents of this panel.
     */
	public void refresh();
    
}
