package fileprocessing;

import fileprocessing.filters.AllFilter;
import fileprocessing.orders.AbsOrder;
import fileprocessing.orders.Order;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.ArrayList;


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
	 * Parse time warnings.
	 */
	private ArrayList<Warning> warnings;

	/**
	 * Generate a new Section object
	 * @param filter given filter.
	 * @param order given files order.
	 * @param warnings found warnings.
	 */
	Section(FileFilter filter, Order order, ArrayList<Warning> warnings) {
		this.filter = filter;
		this.order = order;
		this.warnings = warnings;
	}

	/**
	 * Default constructor.
	 */
	Section() {
		this(new AllFilter(), new AbsOrder(), new ArrayList<>());
	}

	/**
	 * Executes the section's logic on given files. i.e, apply filter and order and return an array of File.
	 * @param files given files to process.
	 * @return filtered and ordered files.
	 */
	File[] execute(File[] files) {
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
	 * Sets section's filter.
	 * @param filter given filter.
	 */
	void setFilter(FileFilter filter) {
		this.filter = filter;
	}


	/**
	 * Sets section's order
	 * @param order given order
	 */
	void setOrder(Order order) {
		this.order = order;
	}
	ArrayList<Warning> getWarnings() {
		return warnings;
	}

	/**
	 * Adds a warning to the warning list.
	 * @param warning warning to add.
	 */
	void addWarning(Warning warning) {
		this.warnings.add(warning);
	}
}
