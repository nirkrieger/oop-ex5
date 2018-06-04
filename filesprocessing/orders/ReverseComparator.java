package filesprocessing.orders;

import java.io.File;
import java.util.Comparator;

/**
 * Decorator for Order implementing objects. First orders the file according to given order, and then
 * reverses the result.
 */
public class ReverseComparator implements Comparator<File> {

	private static final int REVERSE = -1;

	/**
	 * wrapped order.
	 */
	private Comparator<File> wrappedComparator;

	/**
	 * Default Constructor.
	 * @param wrappedComparator order object to wrap.
	 */
	public ReverseComparator(Comparator<File> wrappedComparator) {
		this.wrappedComparator = wrappedComparator;
	}

	private int reverse(int result) {
		return REVERSE * result;
	}

	@Override
	public int compare(File o1, File o2) {
		return reverse(wrappedComparator.compare(o1, o2));
	}

//	/**
//	 * First calls wrappedOrder.orderFiles, and later reverses the array.
//	 * @param files processed files.
//	 */
//	@Override
//	public void orderFiles(File[] files) {
//		wrappedOrder.orderFiles(files);
//		// Pretty stupid. cast to list, reverse with Collections and then cast back to array...
//		List<File> filesList = Arrays.asList(files);
//		Collections.reverse(filesList);
//		File[] reversed = new File[files.length];
//		files = filesList.toArray(reversed);
//	}
}
