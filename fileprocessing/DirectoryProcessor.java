package fileprocessing;

import java.io.File;
import java.io.IOException;
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
	private static final String INVALID_NUM_ARGS = "Incorrect usage";
	/**
	 * Invalid sourcedir.
	 */
	private static final String INVALID_SOURCEDIR = "Invalid sourcedir";
	/**
	 * Invalid path to commands file.
	 */
	private static final String INVALID_COMMANDS_PATH = "Invalid path to commands file.";
	/**
	 * No reading permission
	 */
	private static final String NO_READ_PERMISSION = "Commands file has no reading permission.";
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
	 * @throws NotDirectoryException in case of bad sourceDir name, not a directory or any other IO exception.
	 */
	public void process(File sourceDirectory) throws NotDirectoryException{
		// list sourcedir directory contents, keep only files.
		File[] directoryContents = sourceDirectory.listFiles();
		if (directoryContents == null) {
			throw new NotDirectoryException(sourceDirectory.getAbsolutePath());
		}
		// filter contents and keep only files.
		File[] files = Arrays.stream(directoryContents).filter(file -> file.isFile()).toArray(File[]::new);
		for (Section section : sections) {
			printWarnings(section);
			// execute the section on a clone of the listed files.
			File[] processedFiles = section.execute(files.clone());
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
	 * Validates given arguments.
	 * @param args commandline arguments.
	 * @return true iff arguments are valid.
	 */
	private static boolean validateInput(String[] args) {
		// validate number of arguments
		if (args.length != NUM_OF_ARGS) {
			printError(INVALID_NUM_ARGS);
			return false;
		}
		File sourceDir = new File(args[SOURCEDIR_IDX]);
		// validate sourcedir
		if (!sourceDir.isDirectory()) {
			printError(INVALID_SOURCEDIR);
			return false;
		}
		// validate commands file.
		File commandsFile = new File(args[COMMANDSFILE_IDX]);
		if (!commandsFile.isFile()) {
			printError(INVALID_COMMANDS_PATH);
			return false;
		} else if (!commandsFile.canRead()) {
			printError(NO_READ_PERMISSION);
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		if (!validateInput(args)) {
			// bad input, terminate program.
			return;
		}
		File sourceDir = new File(args[SOURCEDIR_IDX]);
		File commandsFile = new File(args[COMMANDSFILE_IDX]);
		try {
			// initialize processor and process sourceDir.
			DirectoryProcessor processor = new DirectoryProcessor(commandsFile);
			processor.process(sourceDir);
		} catch (BadFormatException e) {
			printError(e.getMessage());
		} catch (IOException e) {
			printError(e.getMessage());
		}
	}
}
