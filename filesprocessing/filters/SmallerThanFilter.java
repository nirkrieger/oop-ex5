package filesprocessing.filters;

import java.io.File;

public class SmallerThanFilter extends SizeFilter {

	/**
	 * This is the minimum of the filter
	 */
	private double threshold;

	/**
	 * This is the constructor
	 *
	 * @param threshold recieves the minimum factor that it filters by
	 */
	SmallerThanFilter(double threshold) {
		this.threshold = threshold;
	}

	/**
	 * This is the method that actually filters
	 *
	 * @param pathname the files path
	 * @return true if the file passes the path
	 */
	@Override
	public boolean accept(File pathname) {
		return toKb(pathname.length()) < threshold;
	}
}
