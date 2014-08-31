/*
 * Copyright (C) 2010 - present, Laszlo Csontos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
