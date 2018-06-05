package filesprocessing.filters;

import filesprocessing.filters.filters.AllFilter;
import filesprocessing.filters.filters.NotFilter;

import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Implements an all filter*
 */
public class AllFilterMatcher implements FilterMatcher {

	/**
	 * This is the all filter pattern
	 */
	private Pattern allPattern = Pattern.compile("all" + notPattern);

	/**
	 * Not group.
	 */
	private static final int NOT = 1;
	/**
	 * this is the matcher for this case
	 */
	private Matcher allMatch;


	/**
	 * This is the matcher for these files
	 *
	 * @param input the filter name line
	 * @return uf the filtter patterns matches
	 */
	@Override
	public boolean matches(String input) {
		allMatch = allPattern.matcher(input);
		return allMatch.matches();
	}

	/**
	 * creates a file filter
	 *
	 * @return the new filter if it is valid
	 */
	@Override
	public FileFilter getFilter(String input) {
		FileFilter currentFilter = new AllFilter();
		if (allMatch.group(NOT) != null)
			return new NotFilter(currentFilter);
		return currentFilter;
	}
}
