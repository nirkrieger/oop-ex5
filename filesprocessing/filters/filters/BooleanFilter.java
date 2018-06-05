package filesprocessing.filters.filters;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

abstract class BooleanFilter implements FileFilter{

    private boolean flag;

	/**
	 * Constructor
	 * @param flag indicates if has to return true or false.
	 */
	public BooleanFilter(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @param v1 first boolean
	 * @param v2 second boolean
	 * @return returns true iff v1 && v2 are both true or false.
	 */
	static boolean logicalNotXor(boolean v1, boolean v2) {
		return !(v1 ^ v2);
	}

	/**
	 *
	 * @param pathname
	 * @return
	 */
	@Override
	public boolean accept(File pathname) {
		return flag;
	}
}
