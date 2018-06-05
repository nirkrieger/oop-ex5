package filesprocessing.filters;

import filesprocessing.filters.filters.BiggerThanFilter;
import filesprocessing.filters.filters.NotFilter;
import filesprocessing.filters.filters.SmallerThanFilter;

import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks if a pattern matches a size filter and creates size filters according to the given parameters
 */
public class SizeFilterMatcher implements FilterMatcher {
	/**
	 * represents greater then filter
	 */
	private static final String GREATER_THAN = "greater_than";
	/**
	 * represents smaller Than filter
	 */
	private static final String SMALLER_THAN = "smaller_than";
	/**
	 * represents the name
	 */
	private static final int NAME = 1;
	/**
	 * represents the first parameter
	 */
	private static final int FIRST_PARAMETER = 2;
	/**
	 * represents the Not parameter
	 */
	private static final int NOT = 3;
	/**
	 * This is the filters pattern
	 */
	private static final Pattern sizePattern = Pattern.compile(namePattern + numberPattern + notPattern);

	/**
	 * This is the matcher data member
	 */
	private Matcher sizeMatch;

	/**
	 * This is the matcher for these files
	 *
	 * @param input the filter name line
	 * @return uf the filtter patterns matches
	 */
	@Override
	public boolean matches(String input) {
		sizeMatch = sizePattern.matcher(input);
		return sizeMatch.matches();
	}

	/**
	 * freates the right size filter
	 *
	 * @return the new filter if the name is valid
	 * @throws FilterNameError name error exception
	 */
	@Override
	public FileFilter getFilter(String input) throws FilterFactoryException {
		String name = sizeMatch.group(NAME);
		FileFilter currentFilter;
		try {
			double threshold = Double.parseDouble(sizeMatch.group(FIRST_PARAMETER));
			if (threshold < 0)
				throw new FilterArgumentsError();
			switch (name) {
				case GREATER_THAN: {
					currentFilter = new BiggerThanFilter(threshold);
					break;
				}
				case SMALLER_THAN: {
					currentFilter = new SmallerThanFilter(threshold);
					break;
				}
				default:
					throw new FilterNameError();
			}
			if (sizeMatch.group(NOT) != null)
				return new NotFilter(currentFilter);
			return currentFilter;
		} catch (NumberFormatException e) {
			throw new FilterArgumentsError();
		}
	}
}

