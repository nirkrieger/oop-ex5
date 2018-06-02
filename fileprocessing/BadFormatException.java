package fileprocessing;

/**
 * Represents an exception thrown while parsing a command file with bad format.
 */
public class BadFormatException extends Exception {
	/**
	 * Constructor
	 * @param message relevant message.
	 */
	public BadFormatException(String message) {
		super(message);
	}
}
