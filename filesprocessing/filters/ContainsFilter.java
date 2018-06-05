package filesprocessing.filters;

import java.io.File;

public class ContainsFilter extends TitleFilter {

    /**
     * Constructor.
     * @param seq the sequence to check if contained.
     */
    public ContainsFilter(String seq) {
        super(seq);
    }

    /**Filters file if pathname is sequence is contained in pathname.
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        return(pathname.getName().contains(getSeq()));
    }
}
