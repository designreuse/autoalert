package info.geekinaction.autoalert.model.domain;

/**
 * 
 * @author lcsontos
 * @since 1.0
 */
public class Session extends AbstractSession {

	private static final long serialVersionUID = 1L;

	private String status;
	private String type;

	/**
	 * 
	 */
	public Session() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

}
