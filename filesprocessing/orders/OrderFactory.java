package filesprocessing.orders;

import java.io.File;
import java.util.Comparator;

/**
 * This class serves as an OrderFactory, choosing 
 */
public class OrderFactory {
	/**The char to split at*/
	private static final String POUND = "#";

	/**this means the filter doesn't contain REVERSE*/
	private static final int NO_REVERSE = 1;

	/** revrse ending */
	private static final String REVERSE = "#REVERSE";

	/**
	 * @return returns the default comparator (ABS).
	 */
	public static Comparator<File> getDefaultComparator() {
		return new AbsComparator();
	}
	/**
	 * This is responsible for generating the right order
	 * @param orderLine	the string given
	 * @return the cirrect irder object
	 * @throws OrderFactoryException
	 */
	public static Comparator<File> chooseOrder(String orderLine) throws OrderFactoryException {
		if (orderLine.isEmpty()) {
			// in case string is emtpy.
			throw new OrderNameException();
		}
		String[] orderName = orderLine.split(POUND);
		// get comparator from ordersMap
		Comparator<File> currentComparator = AllOrders.ordersMap.get(orderName[0]);
		if (currentComparator == null) {
			throw new OrderNameException();
		}
		if (orderName.length > NO_REVERSE){ //check for a reverse statement and reverse if needed
			if (orderLine.endsWith(REVERSE)) {
				currentComparator = new ReverseComparator(currentComparator);
			} else {
				throw new OrderNameException();
			}
		}
		return currentComparator;
	}
}
