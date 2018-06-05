package filesprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Matcher;

public class BetweenFilterMatcher implements FilterMatcher {
    /** represents the first parameter */
    private static final int FIRST = 1;
    /** represents the second parameter*/
    private static final int SECOND = 2;
    /**represents the Not paraneter*/
    private static final int NOT = 3;

    /**This is the matcher data member*/
    private Matcher betweenMatcher;

    /**This is the matcher for these files
     * @param input the filter name line
     * @return uf the filtter patterns matches
     */
    @Override
    public boolean matches(String input) {
        betweenMatcher = BetweenFilter.betweenPattern.matcher(input);
        return betweenMatcher.matches();
    }

    /**
     * creates a between filter
     * @return the new filter if it is valid
     * @throws MinBiggerThanMaxException
     */
    @Override
    public FileFilter getFilter(String input) throws FilterFactoryException {
        double minimum = Double.parseDouble(betweenMatcher.group(FIRST));
        double max = Double.parseDouble(betweenMatcher.group(SECOND));
        if(minimum >= max)
            throw new MinBiggerThanMaxException();
        FileFilter currentFilter = new BetweenFilter(max, minimum);
        if (betweenMatcher.group(NOT) != null) {
            return new NotFilter(currentFilter);
        }
        return currentFilter;

        }
    }

