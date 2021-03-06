package filesprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NotDirectoryException;
import java.util.Arrays;

/**
 * Directory processor manages directory operations.
 * Including parsing command files, processing files and printing results to console.
 */
public class DirectoryProcessor {
	/**
	 * num of main args
	 */
	private static final int NUM_OF_ARGS = 2;
	/**
	 * Source directory index in args
	 */
	private static final int SOURCEDIR_IDX = 0;
	/**
	 * Source directory index in args
	 */
	private static final int COMMANDSFILE_IDX = 1;
	/**
	 * Invalid num of args message.
	 */
	private static final String INVALID_USAGE = "Incorrect usage";
	/**
	 * Invalid sourcedir.
	 */
	private static final String INVALID_SOURCEDIR = "Invalid sourcedir";
	/**
	 * Invalid path to commands file.
	 */
	private static final String INVALID_COMMANDS_PATH = "Invalid path to commands file.";
	/**
	 * error format
	 */
	private static final String ERROR_FORMAT = "ERROR: %s\n";

	/**
	 * Sections to execute.
	 */
	private Section[] sections;


	/**
	 * Generates a new directory processor with the given commandsFile.
	 * @param commandsFile given commands file.
	 */
	public DirectoryProcessor(File commandsFile) throws IOException, BadFormatException {
		SectionFactory sectionFactory = new SectionFactory();
		sections = sectionFactory.parse(commandsFile);
	}

	/**
	 * Prints warnings caused by parsing the given section.
	 * @param section given section.
	 */
	private void printWarnings(Section section) {
		for (Warning warning : section.getWarnings()) {
			System.err.println(warning);
		}
	}

	/**
	 * Processes files from given source directory according to commands file.
	 * @param sourceDirectory given directory.
	 */
	public void process(File sourceDirectory){
		for (Section section : sections) {
			printWarnings(section);
			// execute the section on a clone of the listed files.
			File[] processedFiles = section.execute(sourceDirectory);
			for (File file : processedFiles) {
				System.out.println(file.getName());
			}
		}
	}

	/**
	 * prints error messages.
	 * @param msg error message.
	 */
	private static void printError(String msg) {
		System.err.println(String.format(ERROR_FORMAT, msg));
	}

	/**
	 * This is the mane class that checks if the input is valid, then parses the comands file
	 * and filters the files of the source directory.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length != NUM_OF_ARGS) {
				throw new IllegalArgumentException(INVALID_USAGE);
			}
			File sourceDir = new File(args[SOURCEDIR_IDX]);
			File commandsFile = new File(args[COMMANDSFILE_IDX]);
			// initialize processor and process sourceDir.
			DirectoryProcessor processor = new DirectoryProcessor(commandsFile);
			processor.process(sourceDir);
		}
		catch (BadFormatException e) {
			printError(e.toString());
		}
		catch (IllegalArgumentException|FileNotFoundException e) {
			printError(INVALID_USAGE);
		}
		catch (AccessDeniedException e) {
			printError(INVALID_SOURCEDIR);
		}
		catch (IOException e) {
			printError(e.toString());
		}
	}
}
