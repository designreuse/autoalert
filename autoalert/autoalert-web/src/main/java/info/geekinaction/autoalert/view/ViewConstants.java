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
public interface ViewConstants {

	public static final String MAIN_DIV_ID = "MAIN";
	
	public static final String WIDTH = "1000";
	public static final String HEIGHT = "400";
	
	public static final AutoAlertMessages MESSAGES = GWT.create(AutoAlertMessages.class);
	public static final NumberFormat NF_DECIMAL = NumberFormat.getDecimalFormat();
	public static final NumberFormat NF_PERCENT = NumberFormat.getDecimalFormat();
	
	public static final String CSS_TABLE = "DataTable";
	public static final String CSS_TABLE_FIRST_ROW = "DataTable-FirstRow";
	public static final String CSS_TABLE_EVEN_ROW = "DataTable-EvenRow";
	public static final String CSS_TABLE_ODD_ROW = "DataTable-OddRow";
	public static final String CSS_TABLE_CELL = "DataTable-Cell";
	
	public static final String IMG_URL_REFRESH = "images/refresh.png";
	public static final String IMG_URL_WARNING = "images/warning.png";
	public static final String IMG_URL_OK = "images/ok.png";
	
	public static final String IMG_WIDTH = "28";
	public static final String IMG_HEIGHT = "28";

}
