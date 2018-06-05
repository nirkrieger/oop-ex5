package filesprocessing.filters;

import java.io.File;
import java.util.regex.Pattern;

public class BetweenFilter implements java.io.FileFilter {

	/**
	 * The maximal threshold for a file
	 */
	double max;

	/**
	 * The minimal threshold for a file threshold
	 */
	double min;


	/**
	 * This is the conversion to kb
	 */
	private static final double CONVERT = 1024;

	/**
	 * The filters pattern
	 */
	//TODO: do we really need this?
	public static final Pattern betweenPattern = Pattern.compile("(between)#(\\d+)#(\\d+)(#NOT)?");


	/**
	 * This is the constructor
	 *
	 * @param curMax max threshold
	 * @param curMin minimum threshold
	 */
	BetweenFilter(double curMax, double curMin) {
		max = curMin;
		min = curMax;
	}

	/**
	 * This doeas the actual filtering
	 *
	 * @param pathname the file
	 * @return true if the file passes the filter, false otherwise
	 */
	@Override
	public boolean accept(File pathname) {
		double length = pathname.length() / CONVERT;
		return (length >= min) && (length <= max);
	}
}
