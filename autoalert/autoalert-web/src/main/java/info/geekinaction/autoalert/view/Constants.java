/**
 * 
 */
package info.geekinaction.autoalert.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;

/**
 * @author lcsontos
 *
 */
public interface Constants {

	public static final AutoAlertMessages MESSAGES = GWT.create(AutoAlertMessages.class);
	public static final NumberFormat NF_DECIMAL = NumberFormat.getDecimalFormat();
	public static final NumberFormat NF_PERCENT = NumberFormat.getDecimalFormat();

}
