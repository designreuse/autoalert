/**
 * 
 */
package info.geekinaction.autoalert.model.domain;

/**
 * @author lcsontos
 *
 */
public enum ParameterName {
	
	AUTOALERT_MAIL_FROM ("V"),
	AUTOALERT_RCPT_TO ("V"),
	AUTOALERT_SUBJECT ("V"),
	CPU_USAGE_THRESHOLD ("N"),
	PIO_USAGE_THRESHOLD ("N"),
	TBS_SIZERM_THRESHOLD ("N"),
	TBS_USAGE_THRESHOLD ("N");
	
	private String paramType;
	
	/**
	 * 
	 * @param _paramType
	 */
	private ParameterName(String _paramType) {
		this.paramType = _paramType;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getParamType() {
		return paramType;
	}

}
