package fileprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class SizeOrder implements Order {
	@Override
	public void orderFiles(File[] files) {
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				int res = Long.compare(o1.length(), o2.length());
				if (res == 0) {
					return new AbsOrder.AbsComparator().compare(o1, o2);
				}
				return res;
			}
		});
	}
}
