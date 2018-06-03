package fileprocessing.orders;

import java.io.File;
import java.util.Arrays;

/**
 * Order by type.
 */
public class TypeOrder implements Order {
	/**
	 * Period delimiter.
	 */
	private static final String DELIM = ".";
	/**
	 * Minimum size for a split filename with extension
	 */
	private static final int MIN_INDEX = 0;

	/**
	 * @param file given file
	 * @return returns file's extension. if none is present returns an empty string.
	 */
	private String getExtension(File file) {
		String fileExt = "";
		int lastPeriodIndex = file.getName().lastIndexOf(DELIM);
		if (lastPeriodIndex > MIN_INDEX) {
			// if file name starts with a period, get a substring from the second position.
			fileExt = file.getName().substring(lastPeriodIndex + 1);
		}
		return fileExt;
	}

	/**
	 * compares two files by type.
	 * @param o1 first file.
	 * @param o2 second file.
	 * @return 1 iff o1.size > o2.size, if equal returns Abscompare of o1 and o2.
	 */
	private int compareType(File o1, File o2) {
		int res = getExtension(o1).compareTo(getExtension(o2));
		if (res == 0) {
			return AbsOrder.absCompare(o1, o2);
		}
		return res;

	}
	/**
	 * Orders the given files by the implementing strategy.
	 *
	 * @param files processed files.
	 */
	@Override
	public void orderFiles(File[] files) {
		Arrays.sort(files, (o1, o2) -> compareType(o1, o2));
	}
}
