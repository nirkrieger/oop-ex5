package filesprocessing.filters;

import java.io.FileFilter;

public interface FilterMatcher {

    /**This is the matcher for these files
     * @param input the filter name line
     * @return uf the filtter patterns matches
     */
    boolean matches(String input);

    /**
     * This is a method that returns the right filter
     * @param input the string with the filters name and arguments
     * @return the new filter if valid
     * @throws FilterFactoryExceptions
     */
    FileFilter getFilter (String input) throws FilterFactoryExceptions ;
}
