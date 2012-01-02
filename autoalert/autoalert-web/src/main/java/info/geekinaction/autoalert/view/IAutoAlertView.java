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
	void addActionListener(IAutoAlertController controller);
	
	/**
	 * 
	 * @param obj
	 */
	void showDisplay(AutoAlertDisplay display, Object data);
	
	/**
	 * 
	 * @param t
	 */
	void showError(Throwable t);
	
	/**
	 * 
	 */
	void init();
}
