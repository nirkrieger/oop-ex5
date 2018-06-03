package fileprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class FiltersFactory {

    /**This are all the possible matchers*/
    AllMatchers matchers = new AllMatchers();

    /**
     * This is the factory for generating the filters
     * @param input a string with the filters name and arguments
     * @return a correct filter according to the line
     * @throws FilterFactoryExceptions
     */
    public static FileFilter chooseFilter2 (String input) throws FilterFactoryExceptions {
     FileFilter filter = null;
     for (FilterMatcher f : AllMatchers.matcher) {
         if (f.matches(input)) {
             filter = f.getFilter(input);
         }
     }
     if (filter == null) {
         throw new FilterFactoryExceptions();
     }
     return filter;
    }
}
