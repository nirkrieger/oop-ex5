package fileprocessing.filters;

import java.io.File;

public class ContainsFilter implements java.io.FileFilter {

    /**the sequence that should be contained*/
    String seq;

    /**
     * This is the constructor
     * @param curSeq the sequence we currentky want to check
     */
    ContainsFilter( String curSeq){
        seq = curSeq;
    }/*TODO problem if char?*/

    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        if (pathname.getName().contains(seq))
            return true;
        return false;
    }
}
