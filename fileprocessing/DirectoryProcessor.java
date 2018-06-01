package fileprocessing;

import java.io.File;
import java.io.IOException;
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
	 * Sections to execute.
	 */
	private SectionParsingException[] exceptions;

	/**
	 * Generates a new directory processor with the given commandsFile.
	 * @param commandsFile given commands file.
	 */
	public DirectoryProcessor(File commandsFile) {
		try {
			SectionFactory sectionFactory = new SectionFactory(commandsFile);
			ParseResult result = sectionFactory.parse();
			sections = result.getSections();
			exceptions = result.getExceptions();
		}
		catch (IOException e) {

		}
	}

	/**
	 * Processes files from given source directory according to commands file.
	 * @param sourceDirectory given directory.
	 */
	public void process(File sourceDirectory) {
		if (!sourceDirectory.isDirectory()) {
			//TODO: throw a massive exception
		}
		// retrieve sourcedir directory contents, keep only files.
		File[] directoryContents = sourceDirectory.listFiles();
		File[] files = Arrays.stream(directoryContents).filter(file -> file.isFile()).toArray(File[]::new);
		for (Section section : sections) {
			section.execute(files.clone());
		}
		//TODO: Get results from executing the section and store them.
	}

	/**
	 * prints error messages.
	 * @param msg error message.
	 */
	private static void printError(String msg) {
		System.err.println(String.format(ERROR_FORMAT, msg));
	}

	public static void main(String[] args) {
		// validate given arguments.
		if (args.length != NUM_OF_ARGS) {
			printError(INVALID_NUM_ARGS);
			return;
		}
		File sourceDir = new File(args[SOURCEDIR_IDX]);
		if (!sourceDir.isDirectory()) {
			printError(INVALID_SOURCEDIR);
			return;
		}
		File commandsFile = new File(args[COMMANDSFILE_IDX]);
		if (!commandsFile.isFile()) {
			printError(INVALID_COMMANDS_PATH);
			return;
		} else if (!commandsFile.canRead()) {
			printError(NO_READ_PERMISSION);
			return;
		}
		// initialize processor and process sourceDir.
		DirectoryProcessor processor = new DirectoryProcessor(commandsFile);
		processor.process(sourceDir);
	}

}
