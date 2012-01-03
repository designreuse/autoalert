/**
 * 
 */
package info.geekinaction.autoalert.view;

import com.google.gwt.user.client.ui.Panel;

/**
 * @author lcsontos
 *
 */
public interface IAutoAlertPanel<T> {

	/**
	 * 
	 * @param controller
	 */
	public void registerController(IAutoAlertController controller);
    
    /**
     * 
     * @param obj
     */
	public void display(AutoAlertDisplay display, T obj);
    
    /**
     * 
     */
	public void refresh();
    
    /**
     * 
     * @return
     */
	public Panel getPanel();
    
    /**
     * 
     * @return
     */
	public AutoAlertMessages getMessages();
    
}
