package fileprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Matcher;

public class SizeFilterMatcher implements FilterMatcher {
    /** represents greater then filter*/
    private static final String GREATER_THAN = "greater_Than";
    /** represents smaller Than filter*/
    private static final String SMALLER_THAN = "smaller_Than";

    /**This is the matcher data member*/
    Matcher sizeMatch ;

    /**This is the matcher for these files
     * @param input the filter name line
     * @return uf the filtter patterns matches
     */
    @Override
    public boolean matches(String input) {
        sizeMatch = SizeFilters.sizePattern.matcher(input);
        return sizeMatch.matches();
    }

    /**
     * freates the right size filter
     * @return  the new filter if the name is valid
     * @throws FilterNameError
     */
    @Override
    public FileFilter getFilter(String input) throws FilterFactoryExceptions {
            String name = sizeMatch.group(1);
            FileFilter currentFilter;
            try {
                double threshold = Double.parseDouble(sizeMatch.group(2));
                if (threshold < 0)
                    throw new FilterArgumentsError();
                switch (name) {
                    case GREATER_THAN: {
                        currentFilter = new BiggerThanFilter(threshold);
                        break;
                    }
                    case SMALLER_THAN: {
                        currentFilter = new SmallerThanFilter(threshold);
                        break;
                    }
                    default:
                        throw new FilterNameError();
                }
                if (sizeMatch.group(3) != null)
                    return new NotFilter(currentFilter);
                return currentFilter;
            }
            catch(NumberFormatException e){
                throw new FilterArgumentsError();
            }
        }
    }

