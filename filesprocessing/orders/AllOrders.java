package filesprocessing.orders;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This class acts as a configuration file holding all possible Comparators.
 */
public class AllOrders {
	/**
	 * A hashmap of all possible comparators, a new comparator type can be easily added.
	 */
	static final HashMap<String, Comparator<File>> ordersMap = new HashMap<String, Comparator<File>>() {{
			put("abs", new AbsComparator());
			put("size", new SizeComparator());
			put("type", new TypeComparator());
		}};
}
