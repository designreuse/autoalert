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
 * Enumerates the different displays.
 * 
 * @author lcsontos
 *
 */
public enum AutoAlertDisplay {

	DATABASE_INFO,
	INSTANCE_INFO,
	INSTANCE_CPU,
	INSTANCE_IO,
	STORAGE_TABLESPACES,
	STORAGE_DATAFILES,
	SESSIONS_ALL,
	SESSIONS_BY_CPU,
	SESSION_BY_IO
	
}
