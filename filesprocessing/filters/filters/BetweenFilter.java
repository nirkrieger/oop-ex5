package filesprocessing.filters.filters;

import filesprocessing.filters.filters.SizeFilter;

import java.io.File;

public class BetweenFilter extends SizeFilter {
	/**
	 * The maximal threshold for a file
	 */
	double maxThreshold;

	/**
	 * The minimal threshold for a file threshold
	 */
	double minThreshold;

	/**
	 * This is the constructor
	 *
	 * @param curMax max threshold
	 * @param curMin minimum threshold
	 */
	public BetweenFilter(double curMax, double curMin) {
		maxThreshold = curMax;
		minThreshold = curMin;
	}

	/**
	 * This doeas the actual filtering
	 *
	 * @param pathname the file
	 * @return true if the file passes the filter, false otherwise
	 */
	@Override
	public boolean accept(File pathname) {
		double length = toKb(pathname.length());
		return (length >= minThreshold) && (length <= maxThreshold);
	}
}
