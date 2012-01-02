/**
 * 
 */
package info.geekinaction.autoalert.model.domain;

/**
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
