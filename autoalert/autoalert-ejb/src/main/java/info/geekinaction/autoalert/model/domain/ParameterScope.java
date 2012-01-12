/**
 * 
 */
package info.geekinaction.autoalert.model.domain;

/**
 * 
 * Scope of on-line parameter change.
 * @author lcsontos
 *
 */
public enum ParameterScope {

	/**
	 * In Memory only.
	 */
	MEMORY,
	
	/**
	 * Only store, do not modifiy.
	 */
	DATABASE,
	
	/**
	 * Both.
	 */
	BOTH;
	
}
