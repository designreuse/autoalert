package info.geekinaction.autoalert.model.domain;

/**
 * Holds additional fields which are specific for data files.
 * 
 * @author lcsontos
 *
 */
public class Datafile extends AbstractStorage {

	private static final long serialVersionUID = 1L;

	private String autoext;
	private Float incrByMb;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return getKey();
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		setKey(fileName);
	}

	/**
	 * @return the autoext
	 */
	public String getAutoext() {
		return autoext;
	}

	/**
	 * @param autoext
	 *            the autoext to set
	 */
	public void setAutoext(String autoext) {
		this.autoext = autoext;
	}

	/**
	 * @return the incrByMb
	 */
	public Float getIncrByMb() {
		return incrByMb;
	}

	/**
	 * @param incrByMb
	 *            the incrByMb to set
	 */
	public void setIncrByMb(Float incrByMb) {
		this.incrByMb = incrByMb;
	}

}
