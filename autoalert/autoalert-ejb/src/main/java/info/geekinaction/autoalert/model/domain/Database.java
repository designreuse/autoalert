package info.geekinaction.autoalert.model.domain;

import java.util.Date;

/**
 * 
 * @author lcsontos
 * 
 */
public class Database extends AbstractDomainObject<Long> {

	private static final long serialVersionUID = 1L;

	private String name;
	private String dbUniqueName;
	private Date created;
	private Date resetlogsTime;
	private String logMode;
	private String controlfileType;
	private Date controlfileCreated;
	private String openMode;
	private String databaseRole;
	private String platformName;
	private Long currentScn;
	private Boolean flashbackOn;
	
	private String hostName;
	private String version;
	private String banner;
	private Date startupTime;
	private Boolean loginsAllowed;
	private Boolean shutdownPending;	

	/**
	 * 
	 */
	public Database() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public Long getDbid() {
		return getKey();
	}

	/**
	 * 
	 * @param dbid
	 */
	public void setDbid(Long dbid) {
		setKey(dbid);
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getDbUniqueName() {
		return this.dbUniqueName;
	}

	/**
	 * 
	 * @param dbUniqueName
	 */
	public void setDbUniqueName(String dbUniqueName) {
		this.dbUniqueName = dbUniqueName;
	}

	/**
	 * 
	 * @return
	 */
	public Date getCreated() {
		return this.created;
	}

	/**
	 * 
	 * @param created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * 
	 * @return
	 */
	public Date getResetlogsTime() {
		return this.resetlogsTime;
	}

	/**
	 * 
	 * @param resetlogsTime
	 */
	public void setResetlogsTime(Date resetlogsTime) {
		this.resetlogsTime = resetlogsTime;
	}

	/**
	 * 
	 * @return
	 */
	public String getLogMode() {
		return this.logMode;
	}

	/**
	 * 
	 * @param logMode
	 */
	public void setLogMode(String logMode) {
		this.logMode = logMode;
	}

	/**
	 * 
	 * @return
	 */
	public String getControlfileType() {
		return this.controlfileType;
	}

	/**
	 * 
	 * @param controlfileType
	 */
	public void setControlfileType(String controlfileType) {
		this.controlfileType = controlfileType;
	}

	/**
	 * 
	 * @return
	 */
	public Date getControlfileCreated() {
		return this.controlfileCreated;
	}

	/**
	 * 
	 * @param controlfileCreated
	 */
	public void setControlfileCreated(Date controlfileCreated) {
		this.controlfileCreated = controlfileCreated;
	}

	/**
	 * 
	 * @return
	 */
	public String getOpenMode() {
		return this.openMode;
	}

	/**
	 * ,
	 * 
	 * @param openMode
	 */
	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	/**
	 * 
	 * @return
	 */
	public String getDatabaseRole() {
		return this.databaseRole;
	}

	/**
	 * 
	 * @param databaseRole
	 */
	public void setDatabaseRole(String databaseRole) {
		this.databaseRole = databaseRole;
	}

	/**
	 * 
	 * @return
	 */
	public String getPlatformName() {
		return this.platformName;
	}

	/**
	 * 
	 * @param platformName
	 */
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	/**
	 * 
	 * @return
	 */
	public Long getCurrentScn() {
		return this.currentScn;
	}

	/**
	 * 
	 * @param currentScn
	 */
	public void setCurrentScn(Long currentScn) {
		this.currentScn = currentScn;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getFlashbackOn() {
		return this.flashbackOn;
	}

	/**
	 * 
	 * @param flashbackOn
	 */
	public void setFlashbackOn(Boolean flashbackOn) {
		this.flashbackOn = flashbackOn;
	}

	public Date getStartupTime() {
		return startupTime;
	}

	public void setStartupTime(Date startupTime) {
		this.startupTime = startupTime;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Boolean getLoginsAllowed() {
		return loginsAllowed;
	}

	public void setLoginsAllowed(Boolean loginsAllowed) {
		this.loginsAllowed = loginsAllowed;
	}

	public Boolean getShutdownPending() {
		return shutdownPending;
	}

	public void setShutdownPending(Boolean shutdownPending) {
		this.shutdownPending = shutdownPending;
	}
	
}
