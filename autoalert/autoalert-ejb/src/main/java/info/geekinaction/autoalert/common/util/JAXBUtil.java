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


/**
 * 
 */
package info.geekinaction.autoalert.common.util;

import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * Utility class for marshaling and unmarshaling
 * a Java object graph to XML or an XML document back to Java object graph.   
 * 
 * @author lcsontos
 *
 */
public final class JAXBUtil {

	/**
	 * 
	 */
	private JAXBUtil() { }
	
	/**
	 * Converts this response object to its XML representation unsing JAXB.
	 * 
	 * @param context A formerly initialized JAXB Context.
	 * @param obj An object to be marshaled.
	 * @return XML representation of this object.
	 * @throws JAXBException On error.
	 */
	public static <T> String toXML(JAXBContext context, T obj) throws JAXBException {

		// Convert writer to string
		StringWriter sw = new StringWriter();

		Marshaller m = context.createMarshaller();
		m.setProperty(JAXB_FORMATTED_OUTPUT, true);
		m.marshal(obj, sw);

		return sw.toString();
	}

	/**
	 * Unmarshalls the specified XML document to a call request object.
	 * 
	 * @param context A formerly initialized JAXB Context.
	 * @param An XML representation of a previously marshaled Java object.
	 * @return Unmarshaled Java object.
	 * @throws JAXBException On error.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXML(JAXBContext context, String requestXML) throws JAXBException {

		// Convert String to Reader
		StringReader sr = new StringReader(requestXML);

		// Unmarshall object
		T result = null;
		Unmarshaller um = context.createUnmarshaller();
		result = (T) um.unmarshal(sr);

		return result;
	}

}
