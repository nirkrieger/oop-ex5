package filesprocessing.filters;

import java.io.File;

public class PrefixFilter implements TitleFilters {

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
       return pathname.getName().startsWith(seq);
    }
}
