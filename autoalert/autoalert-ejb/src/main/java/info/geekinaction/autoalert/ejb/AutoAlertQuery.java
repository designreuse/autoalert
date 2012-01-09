/**
 * 
 */
package info.geekinaction.autoalert.ejb;

/**
 * @author lcsontos
 *
 */
public enum AutoAlertQuery {

	FIND_PARAMETERS ("findParameters"),
	FIND_DATABASE ("findDatabase"),
	FIND_INSTANCE_CPU_USAGE ("findInstanceCpuUsage"),
	FIND_INSTANCE_IO_USAGE ("findInstanceIoUsage"),
	FIND_TABLESPACES ("findTablespaces"),
	FIND_DATAFILES ("findDatafiles"),
	FIND_SESSION ("findSession"),
	FIND_SESSION_CPU_USAGE ("findSessionCpuUsage"),
	FIND_SESSION_IO_USAGE ("findSessionIoUsage"),
	COUNT_INCIDENTS ("countIncidents");
	
	private String queryName;
	
	/**
	 * 
	 * @param queryName
	 */
	private AutoAlertQuery(String queryName) {
		this.queryName = queryName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getQueryName() {
		return queryName;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return queryName;
	}
}
