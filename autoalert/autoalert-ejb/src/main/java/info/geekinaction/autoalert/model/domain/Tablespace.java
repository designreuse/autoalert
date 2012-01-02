package info.geekinaction.autoalert.model.domain;

/**
 * 
 * @author lcsontos
 * @since 1.0
 *
 */
public class Tablespace extends AbstractStorage {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	@Override
	public String getTablespaceName() {
		return getKey();
	}
	
	/**
	 * 
	 */
	@Override
	public void setTablespaceName(String tablespaceName) {
		setKey(tablespaceName);
	}
	
}
