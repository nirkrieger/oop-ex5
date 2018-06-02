package fileprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseOrder implements Order {

	private Order wrappedOrder;
	public ReverseOrder(Order wrappedOrder) {
		this.wrappedOrder = wrappedOrder;
	}

	@Override
	public void orderFiles(File[] files) {
		wrappedOrder.orderFiles(files);
		List<File> filesList = Arrays.asList(files);
		Collections.reverse(filesList);
		File[] reversed = new File[files.length];
		files = filesList.toArray(reversed);
	}
}
