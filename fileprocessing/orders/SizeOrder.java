package fileprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Size order class.
 */
public class SizeOrder implements Order {
	/**
	 * Compares files by size in bytes.
	 * @param o1 first file.
	 * @param o2 second file.
	 * @return 1 iff o1.size > o2.size, if equal returns Abscompare of o1 and o2.
	 */
	private int sizeCompare(File o1, File o2) {
		int res = Long.compare(o1.length(), o2.length());
		if (res == 0) {
			// if equal.
			return AbsOrder.absCompare(o1, o2);
		}
		return res;
	}

	/**
	 * Orders files using sizeCompare.
	 * @param files processed files.
	 */
	@Override
	public void orderFiles(File[] files) {
		Arrays.sort(files, (o1, o2) -> sizeCompare(o1, o2));
	}
}
