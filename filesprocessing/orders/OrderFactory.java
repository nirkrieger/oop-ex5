package filesprocessing.orders;

import java.io.File;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderFactory {
	/**The char to split at*/
	private static final String POUND = "#";

	/**this means the filter doesn't contain REVERSE*/
	private static final int NO_REVERSE = 1;

	/** revrse ending */
	private static final String REVERSE = "#REVERSE";

	/**
	 * This is responsible for generating the right order
	 * @param orderLine	the string given
	 * @return the cirrect irder object
	 * @throws OrderFactoryException
	 */
	public static Comparator<File> chooseOrder(String orderLine) throws OrderFactoryException {
		Pattern pattern = Pattern.compile("([a-z])(#Reverse)?");
		Matcher name = pattern.matcher(orderLine);
//		String orderName = "";
//		if (name.find())
//			orderName = name.group(1);

		String [] orderName = orderLine.split(POUND);
		Comparator<File> currentComparator = null;
		switch(orderName[0]){//checks the name of the filter
			case "abs": {
				currentComparator = new AbsComparator();
				break;
			}
			case "size": {
				currentComparator = new SizeComparator();
				break;
			}
			case "type": {
				currentComparator = new TypeComparator();
				break;
			}
			default: {// if it doesn't match any name so it is invalid
				throw new OrderNameException();
			}
		}
		if (orderName.length > NO_REVERSE){//check for a reverse statment and reverse if needed
			if (orderLine.endsWith(REVERSE))
			currentComparator = new ReverseComparator(currentComparator);
			else
				throw new OrderNameException();
		}
		return currentComparator;
	}
}
