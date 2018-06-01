package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

/**
 * All Filter - returns true for every input.
 */
public class AllFilter implements FileFilter{
	@Override
	/**
	 * returns true for every input.
	 */
	public boolean accept(File pathname) {
		return true;
	}
}
