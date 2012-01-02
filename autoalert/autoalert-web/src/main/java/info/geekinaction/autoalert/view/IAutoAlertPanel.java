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
    void registerController(IAutoAlertController controller);
    
    /**
     * 
     * @param obj
     */
    void display(AutoAlertDisplay display, T obj);
    
    /**
     * 
     */
    void refresh();
    
    /**
     * 
     * @return
     */
    Panel getPanel();
    
    /**
     * 
     * @return
     */
    AutoAlertMessages getMessages();
    
}
