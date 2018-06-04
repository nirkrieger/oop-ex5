package filesprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Size order class.
 */
public class SizeComparator implements Comparator<File> {
		/**
	 * Compares files using by size.
	 * @param o1 first file
	 * @param o2 seconds file
	 * @return 1 iff o1.size > o2.size, if equal returns AbsComparator.compare of o1 and o2.
	 */
	@Override
	public int compare(File o1, File o2) {
		int result = Long.compare(o1.length(), o2.length());
		if (result == 0) {
			// if equal.
			return new AbsComparator().compare(o1, o2);
		}
		return result;
	}

}
