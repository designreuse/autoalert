
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
	 * @param context
	 *            JAXB Context
	 * @return XML representation of this object.
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
	 * @param context
	 *            JAXB Context
	 * @throws JAXBException
	 *             Throws JAXBException if unmarshalling fails for some reason.
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
