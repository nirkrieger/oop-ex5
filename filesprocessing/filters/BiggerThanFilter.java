package filesprocessing.filters;

import java.io.File;

public class BiggerThanFilter extends SizeFilter {

	/**
	 * This is the minimum threshold
	 */
	private double threshold;

	/**
	 * This is the constructor
	 *
	 * @param threshold the threshold
	 */
	BiggerThanFilter(double threshold) {
		this.threshold = threshold;
	}

	/**
	 * This does the actual filtering
	 *
	 * @param pathname the file we are checking
	 * @return true if it is bigger than threshold and false otherwise
	 */
	@Override
	public boolean accept(File pathname) {
		return toKb(pathname.length()) > threshold;
	}
}
