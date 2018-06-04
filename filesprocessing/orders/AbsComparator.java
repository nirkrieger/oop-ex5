package filesprocessing.orders;


import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Absolute path order representing class.
 */
public class AbsComparator implements Comparator<File> {
	/**
	 * Absolute path comparing function.
	 * @param o1 first file
	 * @param o2 second file
	 * @return 1 iff o1 name comes alphabetically before o2.
	 */
	@Override
	public int compare(File o1, File o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
