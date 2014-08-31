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
package info.geekinaction.autoalert.model.domain;

/**
 * 
 * General class for holding three arbitrary values.
 * 
 * @author lcsontos
 *
 */
public class Triple<T1, T2, T3> extends Pair<T1, T2> {

	protected T3 part3;
	
	public Triple() { }

	public Triple(T1 part1, T2 part2, T3 part3) { 
		super(part1, part2);
		this.part3 = part3;
	}
	
	public T3 getPart3() {
		return part3;
	}
	
	public void setPart3(T3 part3) {
		this.part3 = part3;
	}

}
