package filesprocessing.orders;


import java.io.File;
import java.util.Arrays;

/**
 * Absolute path order representing class.
 */
public class AbsOrder implements Order{
	/**
	 * Absolute path comparing function..
	 */
	static int absCompare(File o1, File o2) {
		return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
	}

	/**
	 * Orders files by absolute path.
	 * @param files processed files.
	 */
	@Override
	public void orderFiles(File[] files) {
		Arrays.sort(files, (o1, o2) -> absCompare(o1, o2));
	}
}
