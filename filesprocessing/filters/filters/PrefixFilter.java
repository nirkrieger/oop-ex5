package filesprocessing.filters.filters;

import filesprocessing.filters.filters.TitleFilter;

import java.io.File;

/**
 * Represents a prefix filter.
 */
public class PrefixFilter extends TitleFilter {
    /**
     * Constructor.
     * @param seq sequence to filter by.
     */
    public PrefixFilter(String seq) {
        super(seq);
    }

    /** filters the given file.
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
       return pathname.getName().startsWith(getSeq());
    }
}
