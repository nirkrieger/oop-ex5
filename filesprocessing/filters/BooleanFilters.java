package filesprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Pattern;

public interface BooleanFilters extends FileFilter{

    /**This is the pattern for these filters*/
    Pattern boolPattern = Pattern.compile("([\\w\\./\\-]+)#(Yes|No)(#NOT)?");
}
