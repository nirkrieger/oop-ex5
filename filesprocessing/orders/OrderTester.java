package filesprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class OrderTester {

	static void printFileArray(File[] files) {
		for (File file : files) {
			System.out.println(String.format("%s\t%d",file.getAbsolutePath(),file.length()));
		}
	}
	public static void main(String[] args) {
		Comparator<File> comparator = OrderFactory.getDefaultComparator();
		try {
			comparator = OrderFactory.chooseOrder("abs");
			comparator = OrderFactory.chooseOrder("type");
			comparator = OrderFactory.chooseOrder("size#REVERSE");
			comparator = OrderFactory.chooseOrder("absasdasdas");
		}catch (OrderFactoryException e) {
			e.printStackTrace();
		}
//		File folder = new File("./test");
//		File[] content = folder.listFiles();
//		System.out.println("Before ");
//		printFileArray(content);
//		Comparator<File> order = new ReverseComparator(new TypeComparator());
////		Comparator<File> order = new TypeComparator();
//		Arrays.sort(content, order);
//		System.out.println("After " );
//		printFileArray(content);

	}
}
