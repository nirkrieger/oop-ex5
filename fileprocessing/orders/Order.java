package fileprocessing.orders;

import java.io.File;

public interface Order {
	/**
	 * Orders the given files by the implementing strategy.
	 * @param files processed files.
	 */
	void orderFiles(File[] files);

}
