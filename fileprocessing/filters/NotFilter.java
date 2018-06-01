package fileprocessing.filters;

import java.io.File;

public class NotFilter implements java.io.FileFilter {

    /**This is the filter we are doing the oposite of*/
    FileFilter filter;

    NotFilter (FileFilter fileFilter){
        filter = fileFilter;
    }

    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        if(!filter.accept(pathname))
            return true;
        return false;
    }

    }
