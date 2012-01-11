package info.geekinaction.autoalert.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Wrapper panel to be able to register GWT's event handlers to native DOM
 * elements.
 */
public class ElementWrapperPanel extends AbsolutePanel implements HasClickHandlers {
	
	public ElementWrapperPanel(Element elem) {
		super(elem.<com.google.gwt.user.client.Element> cast());
		onAttach();
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
	
}
