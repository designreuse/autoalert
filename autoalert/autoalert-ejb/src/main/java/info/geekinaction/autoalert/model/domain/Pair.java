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
 * General class for holding two arbitrary values.
 * 
 * @author lcsontos
 *
 */
public class Pair<T1, T2> {

	protected T1 part1;
	protected T2 part2;

	public Pair() { }
	
	public Pair(T1 part1, T2 part2) {
		this.part1 = part1;
		this.part2 = part2;
	}
	
	public T1 getPart1() {
		return part1;
	}
	
	public T2 getPart2() {
		return part2;
	}
	
	public void setPart1(T1 part1) {
		this.part1 = part1;
	}
	
	public void setPart2(T2 part2) {
		this.part2 = part2;
	}

}
