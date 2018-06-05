package filesprocessing;

import filesprocessing.filters.FilterFactoryException;
import filesprocessing.filters.FiltersFactory;
import filesprocessing.orders.OrderFactory;
import filesprocessing.orders.OrderFactoryException;

import java.io.*;
import java.util.LinkedList;

/**
 * Section parsing factory.
 */
class SectionFactory {
	/**
	 * FILTER subsection prefix
	 */
	private static final String FILTER = "FILTER";
	/**
	 * ORDER subsection prefix
	 */
	private static final String ORDER = "ORDER";
	/**
	 * This flag indicates whether this is the first line in the file.
	 */
	private boolean firstLine;
	/**
	 * This flag is true iff this is a filter subsection, else it is an order subsection.
	 */
	private boolean filterSubSection;
	/**
	 * This flag is true iff FILTER keyword was found in FILTER subsection.
	 */
	private boolean filterFound;
	/**
	 * This flag is true iff ORDER keyword was found in ORDER subsection.
	 */
	private boolean orderFound;
	/**
	 * Holds the currently parsed section.
	 */
	private Section currentSection;
	/**
	 * list of sections.
	 */
	private LinkedList<Section> sections;

	/**
	 * Default constructor.
	 */
	SectionFactory() {
		reset();
	}

	/**
	 * Resets object's data members. In case when multiple parse calls are made one after another, we have
	 * to ensure data members are re-initialized.
	 */
	private void reset() {
		firstLine = true;
		filterSubSection = true;
		filterFound = false;
		orderFound = false;
		currentSection = new Section();
		sections = new LinkedList<>();
	}

	/**
	 * Handles lines within ORDER subsections. If lines equals to ORDER, it marks orderFound as true,
	 * if ORDER was not found within this ORDER subsection throw an exception and otherwise call factory
	 * for an order object.
	 * @param line line in commands file.
	 * @param lineNum line number.
	 * @throws BadFormatException
	 */
	private void parseOrderLine(String line, int lineNum) throws BadFormatException {

		// handle order sub section:
		if (line.equals(ORDER) && !orderFound) {
			orderFound = true;
		} else if (!orderFound) {
			// this is order subsection but order is not found, throw exception.
			throw new MissingOrderException();
		} else {
			orderFound = false;
			filterSubSection = true;
			if (line.equals(FILTER)) {
				// FILTER could be found right after ORDER, if so call parseFilterLine with this line.
				parseFilterLine(line, lineNum);
			} else {
				try {
					currentSection.setOrder(OrderFactory.chooseOrder(line));
				}
				catch (OrderFactoryException e) {
					currentSection.addWarning(new Warning(lineNum));
				}
			}
		}
	}

	/**
	 * Handles lines within FILTER subsections. If lines equals to FILTER, it marks filterFound as true,
	 * if filter was not found within this FILTER subsection throw an exception and otherwise call factory
	 * for a filter object.
	 * @param line line in commands file.
	 * @param lineNum line number.
	 * @throws BadFormatException
	 */
	private void parseFilterLine(String line, int lineNum) throws MissingFilterException {
		// handle filter subsection
		if (line.equals(FILTER) && !filterFound) {
			filterFound = true;
			if (firstLine) {
				firstLine = false;
			} else {
				sections.add(currentSection);
				currentSection = new Section();
			}
		}
		else if (!filterFound) {
			throw new MissingFilterException();
		} else {
			filterSubSection = false;
			filterFound = false;
			try {
				currentSection.setFilter(FiltersFactory.chooseFilter(line));
			}
			catch (FilterFactoryException e) {
				currentSection.addWarning(new Warning(lineNum));
			}
		}
	}

	/**
	 * Parses a commands file and returns an array of sections.
	 * @param commandsFile given commands file.
	 * @return an array of Sections.
	 * @throws IOException thrown when
	 * @throws BadFormatException thrown when given a command file with a bad format.
	 */
	Section[] parse(File commandsFile) throws IOException, BadFormatException{
		// reset object variables.
		reset();
		try (LineNumberReader reader = new LineNumberReader(new FileReader(commandsFile))) {
			String line;
//			int lineNums = countLines(commandsFile);
			// read lines from commands file
			while ((line = reader.readLine()) != null) {
				if (filterSubSection) {
					parseFilterLine(line, reader.getLineNumber());
				} else {
					parseOrderLine(line, reader.getLineNumber());
				}
			}
			// do file closures - if ended with bad format or just need to add last section.
			if (filterSubSection && filterFound) {
				// in case file ended without supplied filter or order subsection.
				throw new MissingFilterException();
			} else if (!filterSubSection && !orderFound) {
				// in case file ended without order subsection.
				throw new MissingOrderException();
			} else if (!firstLine){
				sections.add(currentSection);
			}
		}
		Section[] sectionsArray = new Section[sections.size()];
		return sections.toArray(sectionsArray);
	}

	private int countLines (File commandFile ) throws IOException {
		FileReader input = new FileReader(commandFile);
		LineNumberReader counter = new LineNumberReader(input);
		while (counter.skip(Long.MAX_VALUE)>0){}
		return counter.getLineNumber();
	}
}
