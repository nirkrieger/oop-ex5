package filesprocessing;

/**
 * Missing Filter Exception
 */
class MissingFilterException extends BadFormatException{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents a bad format of a missing filter section.
	 */
	private static final String NO_FILTER = "FILTER sub-section is missing";

	/**
	 * Default constructor
	 */
	MissingFilterException() {
		super(NO_FILTER);
	}
}
