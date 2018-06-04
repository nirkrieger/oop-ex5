package filesprocessing.filters;

import java.io.File;

public class SmallerThanFilter implements SizeFilters {

    /**This is the minimum of the filter*/
    double max;

    /**This is the conversion to kb*/
    private static final int CONVERT = 1024;

    /**This is the constructor
     * @param curMinimum recieves the minimum factor that it filters by
     */
    SmallerThanFilter (double curMinimum){
        max = curMinimum/CONVERT;
    }

    /**This is the method that actually filters
     * @param pathname the files path
     * @return true if the file passes the path
     */
    @Override
    public boolean accept(File pathname) {
        return max < pathname.length();
    }
}
