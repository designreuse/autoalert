/**
 * 
 */
package info.geekinaction.autoalert.model.exception;

/**
 * 
 * General application exception.
 * 
 * @author lcsontos
 *
 */
public class AutoAlertException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AutoAlertException() { }

	/**
	 * @param message
	 */
	public AutoAlertException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AutoAlertException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AutoAlertException(String message, Throwable cause) {
		super(message, cause);
	}

}
