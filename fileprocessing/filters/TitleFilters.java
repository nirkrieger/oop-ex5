package fileprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Pattern;

public interface TitleFilters extends FileFilter{

    /**This is the filters pattern*/
    Pattern titlePattern = Pattern.compile("(\\w+)#(\\w+)(#NOT)?");

}
