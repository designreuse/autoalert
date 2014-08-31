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

/**
 * 
 */
package info.geekinaction.autoalert.model.incident; 

import info.geekinaction.autoalert.common.util.JAXBUtil;
import info.geekinaction.autoalert.common.util.LogUtil;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.Tablespace;

import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Contains callback method executed by the JPA framework
 * in order to calculate various fields of the current Incident object. 
 * 
 * @author lcsontos
 *
 */
public class AutoAlertIncidentListener {
	
	private static final Logger logger = Logger.getLogger(AutoAlertIncidentListener.class);
	private static String MESSAGE = "Method {0} called on instance {1}.";
	
	private static JAXBContext jaxbContext;
	
	/**
	 * Default constructor
	 */
	public AutoAlertIncidentListener() {
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(AutoAlertIncident.class);
			} catch (JAXBException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * After loading an instance from the database unmarshal the details of
	 * this incident from XML format.
	 *  
	 * @param autoAlertIncident Current incident instance.
	 */
	public void postLoad(AutoAlertIncident autoAlertIncident) {
		try {
			LogUtil.log(logger, Level.DEBUG, MESSAGE, null, buildMessage("postLoad", autoAlertIncident));
			if (autoAlertIncident.details != null) {
				AutoAlertIncident _incident = JAXBUtil.fromXML(jaxbContext, autoAlertIncident.details);
				autoAlertIncident.tablespaces = _incident.tablespaces;
				autoAlertIncident.datafiles = _incident.datafiles;
				autoAlertIncident.cpuUsage = _incident.cpuUsage;
				autoAlertIncident.ioUsage = _incident.ioUsage;
			} else {
				logger.warn("Value of column DETAILS was NULL.");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Before persisting this instance calculate its checksum.
     *
	 * @see AutoAlertIncidentListener#createCheckSum(AutoAlertIncident)
	 * @param autoAlertIncident Current incident instance.
	 */
	public void prePersist(AutoAlertIncident autoAlertIncident) {
		try {
			LogUtil.log(logger, Level.DEBUG, MESSAGE, null, buildMessage("postLoad", autoAlertIncident));
			createCheckSum(autoAlertIncident);
			createDetails(autoAlertIncident);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Before updating this instance calculate its checksum.
     *
	 * @see AutoAlertIncidentListener#createCheckSum(AutoAlertIncident)
	 * @param autoAlertIncident Current incident instance.
	 */
	public void preUpdate(AutoAlertIncident autoAlertIncident) {
		try {
			LogUtil.log(logger, Level.DEBUG, MESSAGE, null, buildMessage("preUpdate", autoAlertIncident));
			createCheckSum(autoAlertIncident);
			createDetails(autoAlertIncident);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Create as hashcode-like checksum out of the details
	 * of this incident. This checksum is supposed to be
	 * unique within a specified time frame, thus two
	 * detected indicents with the details will not be
	 * reported twice.
	 * 
	 * @param autoAlertIncident Current incident instance.
	 * @return Checksum.
	 */
	public int createCheckSum(AutoAlertIncident autoAlertIncident) {
		int checkSum = autoAlertIncident.checkSum;
		
		// Calculate only if we do not have a checksum yet.
		if (checkSum == 0) {
			
			checkSum = 17; // Starter prime value.
			
			// Take into account all those tablespaces which violated storage requirements.
			for (Iterator<Tablespace> iterator = autoAlertIncident.tablespaces.iterator(); iterator.hasNext();) {
				Tablespace obj = iterator.next();
				checkSum = calcCheckSum(checkSum, obj);
			}

			// Take into account all those data files which violated storage requirements.
			for (Iterator<Datafile> iterator = autoAlertIncident.datafiles.iterator(); iterator.hasNext();) {
				Datafile obj = iterator.next();
				checkSum = calcCheckSum(checkSum, obj);
			}
			
			// Take into account CPU or I/O usage violations if any.
			checkSum = calcCheckSum(checkSum, autoAlertIncident.cpuUsage);
			checkSum = calcCheckSum(checkSum, autoAlertIncident.ioUsage);
			autoAlertIncident.checkSum = checkSum;
		}
		return checkSum;
	}
	
	/**
	 * Marshal details of this incident to XML by using  JAXB.
	 * 
	 * @param autoAlertIncident Current incident instance.
	 * @throws JAXBException On error.
	 */
	private void createDetails(AutoAlertIncident autoAlertIncident) throws JAXBException {
		if (autoAlertIncident.details == null) {
			autoAlertIncident.details = JAXBUtil.toXML(jaxbContext, autoAlertIncident);
		}
	}
	
	/**
	 * Combine the current checksum with the hashCode() of the given object.
	 * @param checkSum Current checksum value.
	 * @param obj Arbitrary object in checksum calculation.
	 * @return Checksum value (untouched if obj == null).
	 */
	private int calcCheckSum(int checkSum, Object obj) {
		if (obj == null) {
			return checkSum;
		}
		return 31 * checkSum + obj.hashCode();
	}
	
	private Object[] buildMessage(String methodName, AutoAlertIncident obj) {
		Object[] retval = new Object[] { methodName, obj};
		return retval;
	}

}