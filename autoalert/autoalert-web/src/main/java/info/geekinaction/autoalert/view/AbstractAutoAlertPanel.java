/**
 * 
 */
package info.geekinaction.autoalert.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base panel implementation of all other panels of this application.
 * 
 * @author lcsontos
 *
 * @param <D> Object type to display (eg. List<Tablespace>).
 */
public abstract class AbstractAutoAlertPanel<D> extends LazyPanel implements IAutoAlertPanel<D> {

	protected AutoAlertMessages messages;
	protected IAutoAlertController controller;
	
	public AbstractAutoAlertPanel() {
		this(false);
	}
	
	public AbstractAutoAlertPanel(boolean visible) {
		setVisible(visible);
	}

	/**
	 * Initializes contents of this
	 */
	protected abstract Widget createWidget();
	
	public void registerController(IAutoAlertController controller) {
		this.controller = controller;
	}
	
	@Override
	public void display(AutoAlertDisplay display, D obj) {
		if (display == null || obj == null) {
			return;
		}
	}
	
	protected void showLoaderImage(boolean show) {
		String id = this.getClass().getName();
		Element imgLoaderElement = DOM.getElementById(id);
		if (imgLoaderElement != null) {
			UIObject.setVisible(imgLoaderElement, show);
		}
	}
	
}
