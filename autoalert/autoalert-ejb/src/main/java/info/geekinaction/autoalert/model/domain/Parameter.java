package info.geekinaction.autoalert.model.domain;

import java.util.Map;

/**
 * 
 * @author lcsontos
 *
 */
public class Parameter extends AbstractDomainObject<String> {

	private static final long serialVersionUID = 1L;

	private String paramDesc;

	private String paramType;

	private Float paramNumValue;

	private String paramVcharValue;

	/**
	 * 
	 */
	public Parameter() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public String getParamName() {
		return getKey();
	}

	/**
	 * 
	 * @param paramName
	 */
	public void setParamName(String paramName) {
		setKey(paramName);
	}

	/**
	 * 
	 * @return
	 */
	public String getParamDesc() {
		return this.paramDesc;
	}

	/**
	 * 
	 * @param paramDesc
	 */
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	/**
	 * 
	 * @return
	 */
	public String getParamType() {
		return this.paramType;
	}

	/**
	 * 
	 * @param paramType
	 */
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	/**
	 * 
	 * @return
	 */
	public Float getParamNumValue() {
		return this.paramNumValue;
	}

	/**
	 * 
	 * @param paramNumValue
	 */
	public void setParamNumValue(Float paramNumValue) {
		this.paramNumValue = paramNumValue;
	}

	/**
	 * 
	 * @return
	 */
	public String getParamVcharValue() {
		return this.paramVcharValue;
	}

	/**
	 * 
	 * @param paramVcharValue
	 */
	public void setParamVcharValue(String paramVcharValue) {
		this.paramVcharValue = paramVcharValue;
	}
	
	/**
	 * 
	 * @param parameters
	 * @param parameterName
	 * @return
	 */
	public static String getParameterAsString(Map<ParameterName, Parameter> parameters, ParameterName parameterName) {
		if (parameters == null || parameterName == null || !parameterName.getParamType().equals("V")) {
			throw new IllegalArgumentException("Neither argument can be null and parameterName must indicate a character based parameter value.");
		}
		
		String value = parameters.get(parameterName).getParamVcharValue();
		return value;
	}

	/**
	 * 
	 * @param parameters
	 * @param parameterName
	 * @return
	 */
	public static Float getParameterAsFloat(Map<ParameterName, Parameter> parameters, ParameterName parameterName) {
		if (parameters == null || parameterName == null || !parameterName.getParamType().equals("N")) {
			throw new IllegalArgumentException("Neither argument can be null and parameterName must indicate a numeric based parameter value.");
		}

		Float value = parameters.get(parameterName).getParamNumValue();
		return value;
	}

}
