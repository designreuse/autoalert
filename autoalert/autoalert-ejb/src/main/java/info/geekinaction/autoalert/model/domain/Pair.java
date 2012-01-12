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
