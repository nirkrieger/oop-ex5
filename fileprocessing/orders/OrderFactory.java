package fileprocessing.orders;

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
	public static Order chooseOrder(String orderLine) throws OrderFactoryException {
		Pattern pattern = Pattern.compile("([a-z])(#Reverse)?");
		Matcher name = pattern.matcher(orderLine);
//		String orderName = "";
//		if (name.find())
//			orderName = name.group(1);

		String [] orderName = orderLine.split(POUND);
		Order currentOrder = null;
		switch(orderName[0]){//checks the name of the filter
			case "abs": {
				currentOrder = new AbsOrder();
				break;
			}
			case "size": {
				currentOrder = new SizeOrder();
				break;
			}
			case "type": {
				currentOrder = new TypeOrder();
				break;
			}
			default: {// if it doesn't match any name so it is invalid
				throw new OrderNameException();
			}
		}
		if (orderName.length > NO_REVERSE){//check for a reverse statment and reverse if needed
			if (orderLine.endsWith(REVERSE))
			currentOrder = new ReverseOrder(currentOrder);
			else
				throw new OrderNameException();
		}
		return currentOrder;
	}
}
