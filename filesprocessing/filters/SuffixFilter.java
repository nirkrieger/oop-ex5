package filesprocessing.filters;

import java.io.File;

public class SuffixFilter implements TitleFilters  {

    /** This is the sequence we are checking for*/
    String seq;

    /**
     * This is the constructor
     * @param suffix the suffix we are filtering
     */
    SuffixFilter (String suffix){
        seq = suffix;
    }

    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.getName().endsWith(seq);
    }

    }
