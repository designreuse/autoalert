/**
 * 
 */
package info.geekinaction.autoalert.view;

import static info.geekinaction.autoalert.view.ViewConstants.HEIGHT;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_HEIGHT;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_URL_OK;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_URL_REFRESH;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_URL_WARNING;
import static info.geekinaction.autoalert.view.ViewConstants.IMG_WIDTH;
import static info.geekinaction.autoalert.view.ViewConstants.WIDTH;

import info.geekinaction.autoalert.view.dt.DataTable;

import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author lcsontos
 *
 */
public abstract class AbstractAutoAlertPanel extends LazyPanel implements IAutoAlertPanel<List<?>> {

	protected AutoAlertMessages messages;
	protected IAutoAlertController controller;
	
	public AbstractAutoAlertPanel() {
		this(false);
	}
	
	public AbstractAutoAlertPanel(boolean visible) {
		setVisible(visible);
	}

	protected abstract Widget createWidget();
	
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
	
	/**
	 * 
	 * @param dataTable
	 * @return
	 */
	protected Panel createContainer(DataTable<?> dataTable, ClickHandler clickHandler) {
		ScrollPanel sp = new ScrollPanel(dataTable);
		sp.setHeight(HEIGHT);
		sp.setWidth(WIDTH);
		
		ButtonBase btnRefresh = createRefreshButton(clickHandler);
		
		VerticalPanel vpTablespaces = new VerticalPanel();
		vpTablespaces.setSpacing(10);
		vpTablespaces.add(btnRefresh);
		vpTablespaces.add(sp);
		
		return vpTablespaces;
	}
}
