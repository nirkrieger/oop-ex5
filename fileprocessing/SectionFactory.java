package fileprocessing;

import fileprocessing.filters.FiltersFactory;
import fileprocessing.orders.Order;
import fileprocessing.orders.OrderFactory;

import java.io.*;
import java.util.ArrayList;

class SectionFactory {
	private static final String FILTER = "FILTER";
	private static final String ORDER = "ORDER";

	/**
	 * commands file.
	 */
	private File commandsFile;

	private FiltersFactory filtersFactory = new FiltersFactory();
	private OrderFactory orderFactory = new OrderFactory();

	/**
	 * Section factory constructor.
	 * @param commandsFile commands file to parse.
	 */
	SectionFactory(File commandsFile) throws IOException{
		// TODO: What to do with exceptions?!
		if (commandsFile == null || !commandsFile.isDirectory()) {
			throw new IOException();
		}
		this.commandsFile = commandsFile;
	}

	/**
	 * @return parses a commands file and returns an array of sections.
	 */
	Section[] parse() {
		ArrayList<Section> parsedSections = null;
		// try to read commands file.
		try (BufferedReader br = new BufferedReader(new FileReader(commandsFile))) {
			parsedSections = new ArrayList<>();
			// initialize variables for parsing commands file.
			String filterArgument = new String(), orderArgument = new String();
			boolean firstLine = true;
			boolean beforeFilter = true;
			String line;
			int lineNum = 1;
			// iterate:
			while ((line = br.readLine()) != null) {
				if ((line.equals(FILTER) && beforeFilter)) {
					// filter found in the right position.
					beforeFilter = false;
					if (firstLine) {
						// if firstLine, ignore.
						firstLine = false;
					} else {
	//					FileFilter filter = filtersFactory.chooseFilter(filterArgument);
						Order order = orderFactory.chooseOrder(orderArgument);
						FileFilter filter = null;
						parsedSections.add(new Section(filter, order));
						filterArgument = new String();
						orderArgument = new String();
					}
				}else if (line.equals(ORDER) && !beforeFilter) {
					// ORDER found in the right position, set beforeFilter to true.
					beforeFilter = true;
				} else {
					if (beforeFilter && orderArgument.equals(new String())) {
						// this is order argument, make
						orderArgument = line;
					} else if (!beforeFilter && filterArgument.equals(new String())) {
						filterArgument = line;
					} else {
						//TODO: VERY BAD!!!!
					}
				}
				lineNum++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (Section[]) parsedSections.toArray();
	}
}
