package info.geekinaction.autoalert.common;

import java.security.Principal;

import javax.ejb.SessionContext;

/**
 * 
 * This is a base class of all session EJB's.
 * 
 * @author lcsontos
 * @since 1.0
 * 
 */
public abstract class AbstractBusinessObject {

	/**
	 * Session context.
	 */
	protected SessionContext sessionContext;
	
	/**
	 * Returns the session context for this EJB.
	 * 
	 * @return Session context.
	 */
	protected SessionContext getSessionContext() {
		return sessionContext;
	}

	/**
	 * Set the Session context for this EJB.
	 * 
	 * @param sessionContext
	 *            Session context object.
	 */
	protected abstract void setSessionContext(SessionContext sessionContext);

	/**
	 * Returns the current user.
	 * 
	 * @return User name.
	 */
	protected final String getCurrentUser() {
		Principal principal = sessionContext.getCallerPrincipal();
		return principal != null ? principal.getName() : null;
	}
	
	/**
	 * 
	 */
	public abstract void init();
	
	/**
	 * 
	 */
	public abstract void destroy();

}
