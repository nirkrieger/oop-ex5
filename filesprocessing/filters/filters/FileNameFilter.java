package filesprocessing.filters.filters;

import java.io.File;

/**
 * Represents a filter, filtering by filename.
 */
public class FileNameFilter extends TitleFilter {

	/**
	 * Constructor.
	 * @param seq sequence to filter by.
	 */
	public FileNameFilter(String seq) {
		super(seq);
	}

	/**
	 * Filters file by known name.
	 * @param pathname the file
	 * @return true if the file passes the filter, false otherwise
	 */
	@Override
	public boolean accept(File pathname) {
		return pathname.getName().equals(getSeq());
	}
}
