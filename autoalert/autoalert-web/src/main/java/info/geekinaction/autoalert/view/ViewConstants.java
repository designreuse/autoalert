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

	public static final String APP_DIV_ID = "gwt_app";
	public static final String TITLE_DIV_ID = "gwt_title";
	
	public static final String WIDTH = "900";
	public static final String HEIGHT = "400";
	
	public static final AutoAlertMessages MESSAGES = GWT.create(AutoAlertMessages.class);
	public static final NumberFormat NF_DECIMAL = NumberFormat.getDecimalFormat();
	public static final NumberFormat NF_PERCENT = NumberFormat.getDecimalFormat();
	
	public static final String CSS_BUTTON = "search-submit";
	public static final String CSS_TABLE = "DataTable";
	public static final String CSS_TABLE_FIRST_ROW = "DataTable-FirstRow";
	public static final String CSS_TABLE_EVEN_ROW = "DataTable-EvenRow";
	public static final String CSS_TABLE_ODD_ROW = "DataTable-OddRow";
	public static final String CSS_TABLE_CELL = "DataTable-Cell";
	
	public static final String IMG_URL_REFRESH = "images/refresh.png";
	public static final String IMG_URL_WARNING = "images/warning.png";
	public static final String IMG_URL_OK = "images/ok.png";
	public static final String IMG_LOADER = "images/loader.gif";
	
	public static final String IMG_WIDTH = "28";
	public static final String IMG_HEIGHT = "28";
	
	public static final String SIDEBAR_ANCHOR_NAME = "a_sidebar";
	public static final String SIDEBAR_ANCHOR_GENERAL = "a_sidebarGeneral";
	
	/*
	public static final String SIDEBAR_ANCHOR_INSTANCE_CPU_USAGE_HISTORY = "a_sidebarInstanceCPUUsageHistory";
	public static final String SIDEBAR_ANCHOR_INSTANCE_IO_USAGE_HISTORY = "a_sidebarInstanceIOUsageHistory";
	public static final String SIDEBAR_ANCHOR_SESSION_TOP_CPU = "a_sidebarSessionTopCPU";
	public static final String SIDEBAR_ANCHOR_SESSION_TOP_IO = "a_sidebarSessionTopIO";
	public static final String SIDEBAR_ANCHOR_STORAGE_TABLESPACES = "a_sidebarStorageTablespaces";
	public static final String SIDEBAR_ANCHOR_STORAGE_DATAFILES = "a_sidebarStorageDatafiles";
	*/
}
