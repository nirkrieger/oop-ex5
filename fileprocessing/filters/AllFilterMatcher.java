package fileprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Matcher;

public class AllFilterMatcher implements FilterMatcher {

    /**this is the matcher for this case*/
    Matcher allMatch;

    /**This represents the all filter name*/
    private static final String ALL = "all";

    /**This is the matcher for these files
     * @param input the filter name line
     * @return uf the filtter patterns matches
     */
    @Override
    public boolean matches(String input) {
        allMatch = AllFilter.allPattern.matcher(input);
        return allMatch.matches();
    }

    /**
     * creates a file filter
     * @return the new filter if it is valid
     * @throws MinBiggerThanMaxException
     */
    @Override
    public FileFilter getFilter(String input) throws FilterFactoryExceptions {
        FileFilter currentFilter;
        if (allMatch.group(1) != ALL)
            throw new FilterNameError();
        currentFilter = new AllFilter();
        if (allMatch.group(2) != null)
            return new NotFilter(currentFilter);
        return currentFilter;


    }
}
