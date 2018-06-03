package fileprocessing.filters;

import java.io.File;

public class BiggerThanFilter implements SizeFilters {

    /** This is the minimum threshold*/
    double minimum;

    /**This is the constructor
     * @param curMax the threshold
     */
    BiggerThanFilter (double curMax){
        minimum = curMax;
    }

    /**Tis does the actual filtering
      * @param pathname the file we are checking
     * @return true if it is bigger than threshold and false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        return (pathname.length() > minimum);
    }
}
