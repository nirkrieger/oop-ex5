package filesprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Matcher;

public class BtweenFilterMatcher implements FilterMatcher {
    /** represents between filter*/
    private static final String BETWEEN = "between";

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
    public FileFilter getFilter(String input) throws FilterFactoryExceptions {
        double minimum = Double.parseDouble(betweenMatcher.group(2));
        double max = Double.parseDouble(betweenMatcher.group(2));
            if(minimum >= max)
                throw new MinBiggerThanMaxException();
            return new BetweenFilter(max, minimum);

        }
    }

