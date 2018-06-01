package fileprocessing;

import fileprocessing.filters.FiltersFactory;
import fileprocessing.orders.Order;
import fileprocessing.orders.OrderFactory;

import java.io.*;
import java.util.ArrayList;

/**
 * Section parsing factor.
 * TODO: convert to static class?
 */
class SectionFactory {
	private static final String FILTER = "FILTER";
	private static final String ORDER = "ORDER";

	/**
	 * commands file.
	 */
	private File commandsFile;

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
	 * creates a Section from given arguments
	 * @param filterArgument argument to be passed to filter factory.
	 * @param orderArgument argument to be passed to filter factory.
	 * @return Section object.
	 */
	private Section createSection(String filterArgument, String orderArgument) {
		FileFilter filter = FiltersFactory.chooseFilter(filterArgument);
		Order order = OrderFactory.chooseOrder(orderArgument);
		return new Section(filter, order);
	}

	/**
	 * @return parses a commands file and returns an array of sections.
	 */
	ParseResult parse() {
		ArrayList<Section> parsedSections = null;
		ArrayList<SectionParsingException> exceptions = null;
		String line;
		int lineNum = 1;
		String filterArgument = "", orderArgument = "";
		boolean firstLine = true, beforeFilter = true;
		// try to read commands file.
		try (BufferedReader br = new BufferedReader(new FileReader(commandsFile))) {
			parsedSections = new ArrayList<>();
			// iterate:
			while ((line = br.readLine()) != null) {
				if ((line.equals(FILTER) && beforeFilter)) {
					// filter found in the right position.
					beforeFilter = false;
					if (firstLine) {
						// ignore first line.
						firstLine = false;
					} else {
						parsedSections.add(createSection(filterArgument, orderArgument));
						filterArgument = "";
						orderArgument = "";
					}
				}else if (line.equals(ORDER) && !beforeFilter) {
					// ORDER found in the right position, set beforeFilter to true.
					beforeFilter = true;
				} else {
					if (beforeFilter && orderArgument.isEmpty()) {
						// this is order argument, make
						orderArgument = line;
					} else if (!beforeFilter && filterArgument.isEmpty()) {
						filterArgument = line;
					} else {
						//TODO: VERY BAD!!!!
					}
				}
				lineNum++;
			}
			if (!beforeFilter) {
				// File ended without ORDER clause!
				//TODO: handle exception!
			} else {
				// handle last section.
				parsedSections.add(createSection(filterArgument, orderArgument));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Section[] sections = (Section[]) parsedSections.toArray();
		return new ParseResult(sections, (SectionParsingException[]) exceptions.toArray());
	}
}
