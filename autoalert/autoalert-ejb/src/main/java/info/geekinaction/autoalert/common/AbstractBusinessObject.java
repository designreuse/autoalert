/*
 * Copyright (C) 2010 - present, Laszlo Csontos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
	 * Set the Session context for this EJB mainly through dependency injection.
	 * 
	 * @param sessionContext Session context object.
	 */
	protected abstract void setSessionContext(SessionContext sessionContext);

	/**
	 * Returns the current user.
	 * 
	 * @return User name.
	 */
	public final String getCurrentUser() {
		Principal principal = sessionContext.getCallerPrincipal();
		return principal != null ? principal.getName() : null;
	}
	
	/**
	 * Performs the initialization the current EJB instance.
	 */
	public abstract void init();
	
	/**
	 * Destroys the current EJB instance.
	 */
	public abstract void destroy();

}
