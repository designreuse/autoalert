/**
 * 
 */
package info.geekinaction.autoalert.view;

/**
 * 
 * View interface.
 * 
 * @author lcsontos
 *
 */
public interface IAutoAlertView {

	/**
	 * Binds a controller to this view.
	 *  
	 * @param controller Controller to be bound.
	 */
	public void setActionListener(IAutoAlertController controller);
	
	/**
	 * Called by the controller when a view should show a specified display.
	 * 
	 * @param display Display to show.
	 * @param data Addition data.
	 */
	public void selectDisplay(AutoAlertDisplay display, Object data);
	
	/**
	 * Called by the controller when the specified display should be refreshed.
	 * 
	 * @param display  Display to refresh.
	 * @param obj Data to show on the refreshed view.
	 */
	public void refreshDisplay(AutoAlertDisplay display, Object data);
	
	/**
	 * Initialize this view. 
	 */
	public void init();
	
}
