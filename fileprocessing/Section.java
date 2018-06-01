package fileprocessing;

import fileprocessing.filters.AllFilter;
import fileprocessing.orders.AbsOrder;
import fileprocessing.orders.Order;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Represents a commands file section.
 */
class Section {
	// data members
	/**
	 * Section files filter
	 */
	private FileFilter filter;
	/**
	 * Section order
	 */
	private Order order;

	/**
	 * Constructor, generates a new section object.
	 *
	 * @param filter filter to apply on files.
	 * @param order  how to order files.
	 */
	Section(FileFilter filter, Order order) {
		this.filter = filter;
		this.order = order;
	}

	/**
	 * Default constructor.
	 */
	Section() {
		this(new AllFilter(), new AbsOrder());
	}

	/**
	 * Executes the section's logic on given files. i.e, apply filter and order and return an array of File.
	 * @param files given files to process.
	 * @return filtered and ordered files.
	 */
	File[] execute(File[] files) {
		// TODO: decide what is the correct flow, maybe can be merged into one statement.
		// TODO: apply exceptions.
		// filter files
		files = applyFilter(files);
		// order files
		this.order.orderFiles(files);

		// return ordered and filtered files.
		return files;
	}

	/**
	 * Apply filter on processed files.
	 */
	File[] applyFilter(File[] files) {
		// for each file, if filter accepts file add it to list.
		return Arrays.stream(files).filter(file -> filter.accept(file)).toArray(File[]::new);
	}

	/**
	 * @return gets section's filter.
	 */
	FileFilter getFilter() {
		return filter;
	}

	/**
	 * Sets section's filter.
	 *
	 * @param filter given filter.
	 */
	void setFilter(FileFilter filter) {
		this.filter = filter;
	}

	/**
	 * @return Returns section's order
	 */
	Order getOrder() {
		return order;
	}

	/**
	 * Sets section's order
	 *
	 * @param order given order
	 */
	void setOrder(Order order) {
		this.order = order;
	}
}
