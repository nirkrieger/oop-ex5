package fileprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Decorator for Order implementing objects. First orders the file according to given order, and then
 * reverses the result.
 */
public class ReverseOrder implements Order {
	/**
	 * wrapped order.
	 */
	private Order wrappedOrder;

	/**
	 * Default Constructor.
	 * @param wrappedOrder order object to wrap.
	 */
	public ReverseOrder(Order wrappedOrder) {
		this.wrappedOrder = wrappedOrder;
	}

	/**
	 * First calls wrappedOrder.orderFiles, and later reverses the array.
	 * @param files processed files.
	 */
	@Override
	public void orderFiles(File[] files) {
		wrappedOrder.orderFiles(files);
		// Pretty stupid. cast to list, reverse with Collections and then cast back to array...
		List<File> filesList = Arrays.asList(files);
		Collections.reverse(filesList);
		File[] reversed = new File[files.length];
		files = filesList.toArray(reversed);
	}
}
