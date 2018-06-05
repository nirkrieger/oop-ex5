package filesprocessing.orders;

import java.io.File;
import java.util.Comparator;

/**
 * Decorator for Order implementing objects. First orders the file according to given order, and then
 * reverses the result.
 */
public class ReverseComparator implements Comparator<File> {

	/** represents the sign to reverse an order
	 */
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

}
