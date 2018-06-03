package fileprocessing.orders;

import java.io.File;

public class OrderTester {

	static void printFileArray(File[] files) {
		for (File file : files) {
			System.out.println(String.format("%s\t%d",file.getAbsolutePath(),file.length()));
		}
	}
	public static void main(String[] args) {
		File folder = new File("./test");
		File[] content = folder.listFiles();
		System.out.println("Before ");
		printFileArray(content);
//		Order order = new ReverseOrder(new TypeOrder());
		Order order = new TypeOrder();
		order.orderFiles(content);
		System.out.println("After " );
		printFileArray(content);

	}
}
