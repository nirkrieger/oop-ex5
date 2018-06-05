package filesprocessing.filters;

import filesprocessing.filters.filters.ExecutableFilter;
import filesprocessing.filters.filters.HiddenFilter;
import filesprocessing.filters.filters.NotFilter;
import filesprocessing.filters.filters.WritableFilter;

import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanFiltersMatcher implements FilterMatcher {

	/**
	 * represents writable filter
	 */
	private static final String WRITABLE = "writable";
	/**
	 * represents executable filter
	 */
	private static final String EXECUTABLE = "executable";
	/**
	 * represents hidden filter
	 */
	private static final String HIDDEN = "hidden";
	/**
	 * The yes factor
	 */
	private static final String YES = "YES";
	/**
	 * The No factor
	 */
	private static final String NO = "NO";
	/**
	 * represents the name
	 */
	private static final int NAME = 1;
	/**
	 * represents the first parameter
	 */
	private static final int BOOLEAN_VALUE = 2;
	/**
	 * represents the Not paraneter
	 */
	private static final int NOT = 3;

	/**This is the pattern for these filters*/
	private static Pattern boolPattern = Pattern.compile("([\\w./\\-]+)#(YES|NO)(#NOT)?");

	private Matcher boolMatch;

	@Override
	public boolean matches(String input) {
		boolMatch = boolPattern.matcher(input);
		return boolMatch.matches();
	}

	/**
	 * creates a Yes/No filter
	 *
	 * @return the new filter if the name is valid
	 * @throws FilterNameError no such filter found.
	 */
	@Override
	public FileFilter getFilter(String input) throws FilterFactoryException {
		String name = boolMatch.group(NAME);
		String booleanString = boolMatch.group(BOOLEAN_VALUE);
		boolean value;
		FileFilter currentFilter;
		// parse YES/NO string.
		switch (booleanString) {
			case YES: {
				value = true;
				break;
			}
			case NO: {
				value = false;
				break;
			}
			default:
				throw new FilterArgumentsError();
		}
		switch (name) {
			case WRITABLE: {
				currentFilter = new WritableFilter(value);
				break;
			}
			case EXECUTABLE: {
				currentFilter = new ExecutableFilter(value);
				break;
			}
			case HIDDEN: {
				currentFilter = new HiddenFilter(value);
				break;
			}
			default:
				throw new FilterNameError();
		}
		if (boolMatch.group(NOT) != null)
			return new NotFilter(currentFilter);
		return currentFilter;
	}
}
