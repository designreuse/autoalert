/**
 * 
 */
package info.geekinaction.autoalert.view;

/**
 * @author lcsontos
 *
 */
public interface IAutoAlertView {
	
	public static final String WIDTH = "800";
	public static final String HEIGHT = "400";

	/**
	 * 
	 * @param controller
	 */
	public void addActionListener(IAutoAlertController controller);
	
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
