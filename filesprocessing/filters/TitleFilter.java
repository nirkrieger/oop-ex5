package filesprocessing.filters;

import java.io.File;

public class TitleFilter implements TitleFilters {

    /**This is the name we want to compare to*/
    String name;


    /** This is the constructor
     * @param curName the current files name
     */
    TitleFilter(String curName){
        name = curName;
    }

    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        return (pathname.getName() == name);
    }
}
