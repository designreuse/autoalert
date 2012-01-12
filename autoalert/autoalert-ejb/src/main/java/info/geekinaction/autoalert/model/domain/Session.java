package info.geekinaction.autoalert.model.domain;

/**
 * 
 * Concrete class for representing a database session.
 * 
 * @author lcsontos
 * @since 1.0
 */
public class Session extends AbstractSession {

	private static final long serialVersionUID = 1L;

	// private String status;
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
