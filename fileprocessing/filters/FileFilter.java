package fileprocessing.filters;

import java.io.File;

public class FileFilter implements java.io.FileFilter {

    /**This is the name we want to compare to*/
    String name;

    /** This is the constructor
     * @param curName the current files name
     */
    FileFilter (String curName){
        name = curName;
    }

    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        if (pathname.getName() == name)
            return true;
        return false;
    }
}
