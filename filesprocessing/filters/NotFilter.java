package filesprocessing.filters;

import java.io.File;

public class NotFilter implements java.io.FileFilter {

    /**This is the filter we are doing the oposite of*/
    java.io.FileFilter filter;

    /**
     * The cinstructor
     * @param fileFilter a filter to do the oposite of
     */
    public NotFilter (java.io.FileFilter fileFilter){
        filter = fileFilter;
    }

    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        return !filter.accept(pathname);
    }
}
