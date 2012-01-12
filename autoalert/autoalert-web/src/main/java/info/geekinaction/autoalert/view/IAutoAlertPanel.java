/**
 * 
 */
package info.geekinaction.autoalert.view;

/**
 * 
 * Interface of panels
 * 
 * @param <T> Object type to display (eg. List<Tablespace>).
 *
 */
public interface IAutoAlertPanel<T> {

	/**
	 * Register the specified controller to this panel.
	 * 
	 * @param controller Application's controller.
	 */
	public void registerController(IAutoAlertController controller);
    
    /**
     * Show the given panel with the given display name within this panel.
     * @param display Display to show.
     * @param obj Object to display.
     */
	public void display(AutoAlertDisplay display, T obj);
    
    /**
     * Refresh the contents of this panel.
     */
	public void refresh();
    
}
