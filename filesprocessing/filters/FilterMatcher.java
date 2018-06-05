package filesprocessing.filters;

import java.io.FileFilter;

/**
 * An interface for all the matchers
 */
public interface FilterMatcher {
	/**
	 * standard name pattern
	 */
    String namePattern = "([\\w./\\-]+)";
	/**
	 * standard not pattern.
	 */
	String notPattern = "(#NOT)?";
	/**
	 * standard number pattern.
	 */
    String numberPattern = "#([\\d. ]+)";

    /**This is the matcher for these files
     * @param input the filter name line
     * @return uf the filtter patterns matches
     */
    boolean matches(String input);

    /**
     * This is a method that returns the right filter
     * @param input the string with the filters name and arguments
     * @return the new filter if valid
     * @throws FilterFactoryException
     */
    FileFilter getFilter (String input) throws FilterFactoryException;
}
