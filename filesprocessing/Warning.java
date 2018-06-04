package filesprocessing;

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
}
