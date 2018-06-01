package fileprocessing.filters;

import java.io.File;

public class BetweenFilter implements java.io.FileFilter {

    /** The maximal threshold for a file*/
    double max;

    /** The minimal threshold for a file threshold*/
    double min;

    /**
     * This is the constructor
     * @param curMax max threshold
     * @param curMin minimum threshold
     */
    BetweenFilter(double curMax, double curMin){
        max = curMax;
        min = curMin;
    }

    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        double length = pathname.length();
        if ((length >= min )&& (length <= max))
            return true;
        return false;
    }
}
