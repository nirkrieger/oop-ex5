package fileprocessing.filters;

import java.io.File;

public class HiddenFilter implements BooleanFilters {

    /**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.isHidden();
    }
    }
