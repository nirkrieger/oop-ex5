package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

interface SizeFilters extends FileFilter {

    /**This is the filters pattern*/
    Pattern sizePattern = Pattern.compile("(\\w)+#(\\d)+(#NOT)?");

}
