/**
 * 
 */
package info.geekinaction.autoalert.model.exception;

/**
 * 
 * General application exception which means some technical failure.
 * 
 * @author lcsontos
 *
 */
public class AutoAlertSystemException extends AutoAlertException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AutoAlertSystemException() { }

	/**
	 * @param message
	 */
	public AutoAlertSystemException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AutoAlertSystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AutoAlertSystemException(String message, Throwable cause) {
		super(message, cause);
	}

}
