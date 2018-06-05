package filesprocessing;

import filesprocessing.filters.*;

import java.io.FileFilter;
import java.util.regex.Matcher;

public class BooleanFiltersMatcher implements FilterMatcher{

    /** represents writable filter*/
    private static final String WRITABLE = "writable";
    /** represents executable filter*/
    private static final String EXECUTABLE ="executable";
    /** represents hidden filter*/
    private static final String HIDDEN = "hidden";
    /**The yes factor*/
    private static final String YES = "YES";
    /**The No factor*/
    private static final String NO = "NO";

    Matcher boolMatch;

    @Override
    public boolean matches(String input) {
        boolMatch = BooleanFilters.boolPattern.matcher(input);
        return boolMatch.matches();
    }

    /**
     * creates a Yes/No filter
     * @return  the new filter if the name is valid
     * @throws FilterNameError
     */
    @Override
    public FileFilter getFilter(String input) throws FilterFactoryException {
       String name = boolMatch.group(1);
       FileFilter currentFilter;
       if ((boolMatch.group(2)!= YES)&&(boolMatch.group(2)!= YES))
           throw new FilterArgumentsError();
       switch (name) {
                case WRITABLE : {
                    currentFilter = new WritableFilter();
                    break;
                }
                case EXECUTABLE: {
                    currentFilter = new ExecutableFilter();
                    break;
                }
                case HIDDEN: {
                    currentFilter = new HiddenFilter();
                    break;
                }
                default:
                    throw new FilterNameError();
            }
            if (boolMatch.group(3) != null)
                return new NotFilter(currentFilter);
            return currentFilter;
    }
}
