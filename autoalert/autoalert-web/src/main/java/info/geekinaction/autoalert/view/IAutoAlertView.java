/**
 * 
 */
package info.geekinaction.autoalert.view;

/**
 * @author lcsontos
 *
 */
public interface IAutoAlertView {

	/**
	 * 
	 * @param controller
	 */
	public void setActionListener(IAutoAlertController controller);
	
	/**
	 * 
	 * @param obj
	 */
	public void showDisplay(AutoAlertDisplay display, Object data);
	
	/**
	 * 
	 * @param t
	 */
	public void showError(Throwable t);
	
	/**
	 * 
	 */
	public void init();
	
}
