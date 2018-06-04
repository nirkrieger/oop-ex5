package filesprocessing;

/**
 * Represents an exception thrown while parsing a command file with bad format.
 */
public class BadFormatException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 * @param message relevant message.
	 */
	public BadFormatException(String message) {
		super(message);
	}
}
