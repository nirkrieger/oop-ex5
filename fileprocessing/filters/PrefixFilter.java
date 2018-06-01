package fileprocessing.filters;

import java.io.File;

public class PrefixFilter implements java.io.FileFilter {

    /** This is the sequence we are chaecking*/
    String seq;

    /**
     * This is the constroctur
     * @param prefix the prefox thos filter checks
     */
    PrefixFilter (String prefix){
        seq = prefix;
    }


    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
       if(pathname.getName().startsWith(seq))
           return true;
       return false;
    }
}
