package filesprocessing.filters.filters;

import java.io.FileFilter;
import java.util.regex.Pattern;

abstract class SizeFilter implements FileFilter {
	/**
	 * Kilobyte size.
	 */
	private static final double KB = 1024;

	/**
	 * @param size given size
	 * @return converts size to kb
	 */
	double toKb(double size) {
		return size / KB;
	}

}
