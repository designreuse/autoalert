/**
 * 
 */
package info.geekinaction.autoalert.view;

import static info.geekinaction.autoalert.view.ViewConstants.CSS_BUTTON;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_LOADER;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_URL_OK;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_URL_WARNING;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Utility class for panels.
 * 
 * @author lcsontos
 *
 */
public final class AutoAlertPanelUtil {

	/**
	 * 
	 */
	private AutoAlertPanelUtil() { }
	
	/**
	 * Creates a simple refresh button.
	 * 
	 * @return A reference to the created button.
	 */
	public static ButtonBase createRefreshButton(ClickHandler clickHandler) {
		ButtonBase button = new Button(MESSAGES.refresh(), clickHandler);
		button.setStyleName(CSS_BUTTON);
		//button.setWidth(IMG_WIDTH);
		//button.setHeight(IMG_HEIGHT);
		return button;
	}
	
	/**
	 * Create an alert image.
	 * 
	 * @param alert Indicates whether the image should show an alert condition.
	 * @return Create image.
	 */
	public static Image createAlertImage(int alert) {
		return createAlertImage(alert > 0);
	}

	/**
	 * @see AutoAlertPanelUtil#createAlertImage(int) 
	 */
	public static Image createAlertImage(boolean alert) {
		Image image = alert ? new Image(IMG_URL_WARNING) : new Image(IMG_URL_OK);
		return image;
	}
	
	/**
	 * Create AJAX "loading" image.
	 *  
	 * @param id DOM id of the image to be created.
	 * @return The created image.
	 */
	public static Image createLoaderImage(String id) {
		Image image = new Image(IMG_LOADER);
		Element ie = image.getElement();
		ie.setId(id);
		return image;
	}
	
	/**
	 * Creates the inner container of the caller panel. 
	 * @param widget Widget which shows data.
	 * @return The created panel.
	 */
	public static Panel createContainer(Widget widget, String loaderId, ClickHandler clickHandler) {
		
		// Horizontal panel for the button and the AJAX loader image.
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(10);
		
		ButtonBase btnRefresh = createRefreshButton(clickHandler);
		horizontalPanel.add(btnRefresh);
		
		if (loaderId != null) {
			Image imgLoader = createLoaderImage(loaderId);
			imgLoader.setVisible(false);
			horizontalPanel.add(imgLoader);
		}
		
		VerticalPanel container = new VerticalPanel();
		container.setSpacing(10);
		container.add(horizontalPanel);
		container.add(widget);
		
		return container;
	}
	
}
