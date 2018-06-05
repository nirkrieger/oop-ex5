package filesprocessing.filters;

import java.io.File;

/**
 * Represents a filter which filters by suffix.
 */
public class SuffixFilter extends TitleFilter {
	/**
	 * Constructor.
	 * @param suffix the suffix we are filtering
	 */
	SuffixFilter(String suffix) {
		super(suffix);
	}

	/**
	 * filters the file by suffix.
	 * @param pathname the file
	 * @return true if the file passes the filter, false otherwise
	 */
	@Override
	public boolean accept(File pathname) {
		return pathname.getName().endsWith(getSeq());
	}

}
