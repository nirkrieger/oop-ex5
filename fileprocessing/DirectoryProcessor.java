package fileprocessing;

import java.io.File;
import java.util.Arrays;

/**
 * Directory processor manages directory operations.
 * Including parsing command files, processing files and printing results to console.
 */
public class DirectoryProcessor {
	/**
	 * Sections to execute.
	 */
	private Section[] sections;

	public DirectoryProcessor(File commandsFile) {
		SectionFactory sectionFactory = new SectionFactory(commandsFile);
		sections = sectionFactory.parse();
	}

	/**
	 * Processes files from given source directory according to commands file.
	 * @param sourceDirectory given directory.
	 */
	public void process(File sourceDirectory) {
		if (!sourceDirectory.isDirectory()) {
			//TODO: throw a massive exception
		}
		File[] directoryContents = sourceDirectory.listFiles();
		File[] files = Arrays.stream(directoryContents).filter(file -> file.isFile()).toArray(File[]::new);

		for (Section section : sections) {
			section.execute(files.clone());
		}
	}

	public static void main(String[] args) {

	}

}
