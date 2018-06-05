package filesprocessing.filters;

import java.io.File;

public class BiggerThanFilter implements SizeFilters {

	/**
	 * This is the minimum threshold
	 */
	double minimum;

	/**
	 * This is the conversion to kb
	 */
	private static final double CONVERT = 1024;

	/**
	 * This is the constructor
	 *
	 * @param curMax the threshold
	 */
	BiggerThanFilter(double curMax) {
		(minimum) = curMax / CONVERT;
	}

	/**
	 * This does the actual filtering
	 *
	 * @param pathname the file we are checking
	 * @return true if it is bigger than threshold and false otherwise
	 */
	@Override
	public boolean accept(File pathname) {
		return (pathname.length() > minimum);
	}
}
