package fileprocessing.filters;

import java.io.File;

public class WritableFilter implements java.io.FileFilter {

    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        if (pathname.canWrite())
            return true;
        return false;
    }


    }
