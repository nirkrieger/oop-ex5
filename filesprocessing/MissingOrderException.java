package filesprocessing;

/**
 * Missing Filter Exception
 */
class MissingOrderException extends BadFormatException{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents a bad format of a missing filter section.
	 */
	private static final String NO_ORDER = "ORDER sub-section is missing";

	/**
	 * Default constructor
	 */
	MissingOrderException() {
		super(NO_ORDER);
	}
}
