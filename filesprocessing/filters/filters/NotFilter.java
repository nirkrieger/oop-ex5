package filesprocessing.filters.filters;

import java.io.File;
import java.io.FileFilter;


public class NotFilter implements FileFilter {

    /**This is the filter we are doing the oposite of*/
    FileFilter filter;

    /**
     * The constructor
     * @param fileFilter a filter to do the oposite of
     */
    public NotFilter (FileFilter fileFilter){
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
