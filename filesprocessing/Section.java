package filesprocessing;

import filesprocessing.filters.AllFilter;
import filesprocessing.orders.AbsComparator;
import filesprocessing.orders.Order;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;


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
	private Comparator<File> order;

	/**
	 * Parse time warnings.
	 */
	private LinkedList<Warning> warnings;

	/**
	 * Generate a new Section object
	 * @param filter given filter.
	 * @param order given files order.
	 * @param warnings found warnings.
	 */
	Section(FileFilter filter, Comparator<File> order, LinkedList<Warning> warnings) {
		this.filter = filter;
		this.order = order;
		this.warnings = warnings;
	}

	/**
	 * Default constructor.
	 */
	Section() {
		this(new AllFilter(), new AbsComparator(), new LinkedList<>());
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
		Arrays.sort(files, order);
//		this.order.orderFiles(files);
		// return ordered and filtered files.
		return files;
	}

	/**
	 * @param files given files
	 * @return filtered files.
	 */
	private File[] applyFilter(File[] files) {
		// for each file, if filter accepts file add it to list.
		//TODO: maybe change to directory.listFiles(section.filter) ? better looking but worse performance.
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
	void setOrder(Comparator<File> order) {
		this.order = order;
	}
	LinkedList<Warning> getWarnings() {
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
