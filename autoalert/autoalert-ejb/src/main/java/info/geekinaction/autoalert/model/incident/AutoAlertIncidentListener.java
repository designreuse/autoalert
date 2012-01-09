/**
 * 
 */
package info.geekinaction.autoalert.model.incident; 

import info.geekinaction.autoalert.common.util.JAXBUtil;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.Tablespace;

import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

public class AutoAlertIncidentListener {
	
	private static final Logger logger = Logger.getLogger(AutoAlertIncidentListener.class);

	private static JAXBContext jaxbContext;
	
	public AutoAlertIncidentListener() {
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(AutoAlertIncident.class);
			} catch (JAXBException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void postLoad(AutoAlertIncident autoAlertIncident) {
		try {
			logger.debug(buildMessage("portLoad", autoAlertIncident));
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

	public void prePersist(AutoAlertIncident autoAlertIncident) {
		try {
			logger.debug(buildMessage("prePersist", autoAlertIncident));
			createCheckSum(autoAlertIncident);
			createDetails(autoAlertIncident);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void preUpdate(AutoAlertIncident autoAlertIncident) {
		try {
			logger.debug(buildMessage("preUpdate", autoAlertIncident));
			createCheckSum(autoAlertIncident);
			createDetails(autoAlertIncident);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public int createCheckSum(AutoAlertIncident autoAlertIncident) {
		int checkSum = autoAlertIncident.checkSum;
		if (checkSum == 0) {
			
			checkSum = 17;
			for (Iterator<Tablespace> iterator = autoAlertIncident.tablespaces.iterator(); iterator.hasNext();) {
				Tablespace obj = iterator.next();
				checkSum = calcCheckSum(checkSum, obj);
			}

			for (Iterator<Datafile> iterator = autoAlertIncident.datafiles.iterator(); iterator.hasNext();) {
				Datafile obj = iterator.next();
				checkSum = calcCheckSum(checkSum, obj);
			}
			
			checkSum = calcCheckSum(checkSum, autoAlertIncident.cpuUsage);
			checkSum = calcCheckSum(checkSum, autoAlertIncident.ioUsage);
			autoAlertIncident.checkSum = checkSum;
		}
		return checkSum;
	}
	
	private void createDetails(AutoAlertIncident autoAlertIncident) throws JAXBException {
		if (autoAlertIncident.details == null) {
			autoAlertIncident.details = JAXBUtil.toXML(jaxbContext, autoAlertIncident);
		}
	}
	
	private int calcCheckSum(int checkSum, Object obj) {
		if (obj == null) {
			return checkSum;
		}
		return 31 * checkSum + obj.hashCode();
	}
	
	private String buildMessage(String methodName, AutoAlertIncident obj) {
		StringBuilder sb = new StringBuilder();
		sb.append(methodName); 
		sb.append(" called on ");
		sb.append(obj);
		sb.append('.');
		return sb.toString();
	}

}