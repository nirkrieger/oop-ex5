package filesprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Matcher;

public class BetweenFilterMatcher implements FilterMatcher {
    /** represents between filter*/
    private static final String BETWEEN = "between";
    /** represents the name */
    private static final int NAME = 1;
    /** represents the first parameter*/
    private static final int FIRST_PARAMETER = 2;
    /**represents the Not paraneter*/
    private static final int NOT = 3;

    /**This is the matcher data member*/
    Matcher betweenMatcher;

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
        double minimum = Double.parseDouble(betweenMatcher.group(2));
        double max = Double.parseDouble(betweenMatcher.group(2));
            if(minimum >= max)
                throw new MinBiggerThanMaxException();
            return new BetweenFilter(max, minimum);

        }
    }

