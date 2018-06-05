package filesprocessing.filters.filters;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * All Filter - returns true for every input.
 */
public class AllFilter implements FileFilter{
	/**
	 * returns true for every input.
	 */
	public boolean accept(File pathname) {
		return true;
	}
}
