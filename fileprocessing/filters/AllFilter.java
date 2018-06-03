package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * All Filter - returns true for every input.
 */
public class AllFilter implements FileFilter{

	/**This is the all filter pattern*/
	static Pattern allPattern = Pattern.compile("(\\w+)(#NOT)?");

	/**
	 * returns true for every input.
	 */
	public boolean accept(File pathname) {
		return true;
	}
}
