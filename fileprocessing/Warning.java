package fileprocessing;

/**
 * Repersents type I errors.
 */
public class Warning {
	/**
	 * Warning message format.
	 */
	private static final String WARNING_FORMAT = "Warning in line %d";
	/**
	 * line number related to the warning.
	 */
	private int lineNum;

	/**
	 * Constructor
	 * @param lineNum line number related to the warning.
	 */
	public Warning(int lineNum) {
		this.lineNum = lineNum;
	}

	/**
	 * @return returns a formatted string of the warning.
	 */
	@Override
	public String toString() {
		return String.format(WARNING_FORMAT, lineNum);
	}

	/**
	 * @return Returns the hashcode of the lineNumber. used for hashing warnings.
	 */
	@Override
	public int hashCode() {
		return Integer.hashCode(lineNum);
	}

	/**
	 * @param o other object
	 * @return true iff o's hashcode equals to this.hashcode
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Warning) {
			return lineNum == ((Warning) o).lineNum;
		}
		return false;
	}
}
