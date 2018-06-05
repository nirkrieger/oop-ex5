package filesprocessing.filters;

import filesprocessing.filters.filters.BetweenFilter;
import filesprocessing.filters.filters.NotFilter;

import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetweenFilterMatcher implements FilterMatcher {
    /** represents the first parameter */
    private static final int FIRST = 1;
    /** represents the second parameter*/
    private static final int SECOND = 2;
    /**represents the Not parameter*/
    private static final int NOT = 3;

    /**This is the matcher data member*/
    private Matcher betweenMatcher;
    /**
     * Matcher's pattern.
     */
    private static final Pattern betweenPattern = Pattern.compile("between" + numberPattern + numberPattern
            + notPattern);

    /**This is the matcher for these files
     * @param input the filter name line
     * @return uf the filtter patterns matches
     */
    @Override
    public boolean matches(String input) {
        betweenMatcher = betweenPattern.matcher(input);
        return betweenMatcher.matches();
    }

    /**
     * creates a between filter
     * @return the new filter if it is valid
     * @throws MinBiggerThanMaxException min value is greater than max, throw exception.
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

