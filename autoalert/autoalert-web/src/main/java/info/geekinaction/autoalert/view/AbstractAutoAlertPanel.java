/**
 * 
 */
package info.geekinaction.autoalert.view;

import static info.geekinaction.autoalert.view.ViewConstants.IMG_URL_REFRESH;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_URL_WARNING;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_URL_OK;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_WIDTH;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_HEIGHT;

import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
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
	
	/**
	 * 
	 * @return
	 */
	protected ButtonBase createRefreshButton(ClickHandler clickHandler) {
		ButtonBase button = new PushButton(new Image(IMG_URL_REFRESH), clickHandler);
		button.setWidth(IMG_WIDTH);
		button.setHeight(IMG_HEIGHT);
		return button;
	}
	
	/**
	 * 
	 * @param alert
	 * @return
	 */
	protected Image createAlertImage(int alert) {
		return createAlertImage(alert > 0);
	}

	/**
	 * 
	 * @param alert
	 * @return
	 */
	protected Image createAlertImage(boolean alert) {
		Image image = alert ? new Image(IMG_URL_WARNING) : new Image(IMG_URL_OK);
		return image;
	}
}
