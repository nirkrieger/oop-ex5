package fileprocessing.orders;


import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Absolute path order representing class.
 */
public class AbsOrder implements Order{
	/**
	 * Absolute path comparator.
	 */
	static class AbsComparator implements Comparator<File>{
		/**
		 * compares files by absolute paths.
		 * @param o1 first file
		 * @param o2 second file
		 * @return 1, 0 or -1 according to compare result.
		 */
		@Override
		public int compare(File o1, File o2) {
			return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
		}
	}

	/**
	 * Orders files by absolute path.
	 * @param files processed files.
	 */
	@Override
	public void orderFiles(File[] files) {
		Arrays.sort(files, new AbsComparator());
	}
}
