package filesprocessing.filters;

import java.io.FileFilter;


public class FiltersFactory {


    /**
     * This is the factory for generating the filters
     * @param input a string with the filters name and arguments
     * @return a correct filter according to the line
     * @throws FilterFactoryExceptions
     */
    public static FileFilter chooseFilter (String input) throws FilterFactoryExceptions {
     FileFilter filter = null;
     for (FilterMatcher f : AllMatchers.matcher) {
         if (f.matches(input)) {
             filter = f.getFilter(input);
             if (filter != null)
                return filter;
         }
     }
     if (filter == null) {
         throw new FilterFactoryExceptions();
     }
     return filter;
    }
}
