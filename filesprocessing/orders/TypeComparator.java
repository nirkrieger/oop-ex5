package filesprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Order by type.
 */
public class TypeComparator implements Comparator<File> {
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
	@Override
	public int compare(File o1, File o2) {
		int res = getExtension(o1).compareTo(getExtension(o2));
		if (res == 0) {
			return new AbsComparator().compare(o1, o2);
		}
		return res;
	}
}
