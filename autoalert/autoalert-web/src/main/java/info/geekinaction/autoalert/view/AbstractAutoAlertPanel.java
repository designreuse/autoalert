/**
 * 
 */
package info.geekinaction.autoalert.view;

import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * @author lcsontos
 *
 */
public abstract class AbstractAutoAlertPanel extends SimplePanel implements IAutoAlertPanel<List<?>> {

	protected AutoAlertMessages messages;
	protected IAutoAlertController controller;
	
	/**
	 * 
	 */
	public AbstractAutoAlertPanel() {
		buildPanel();
	}

	/**
	 * @param child
	 */
	public AbstractAutoAlertPanel(Element child) {
		super(child);
		buildPanel();
	}
	
	/*
	public AutoAlertMessages getMessages() {
		if (messages == null) {
			messages = GWT.create(AutoAlertMessages.class);
		}
		return messages;
	}
	*/
	
	/**
	 * 
	 */
	public Panel getPanel() {
		return this;
	}
	
	/**
	 * 
	 */
	public void registerController(IAutoAlertController controller) {
		this.controller = controller;
	}
	
	@Override
	public void display(AutoAlertDisplay display, List<?> obj) {
		if (display == null || obj == null || obj.size() == 0) {
			return;
		}
	}
	
	/**
	 * 
	 */
	public abstract void buildPanel();
	
}
