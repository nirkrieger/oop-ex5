package filesprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Matcher;

public class TitleFilterMatcher implements FilterMatcher {

    /** represents prefix filter*/
    private static final String PREFFIX = "preffix";
    /** represents suffix filter*/
    private static final String SUFFIX = "suffix";
    /** represents contains filter*/
    private static final String CONTAINS = "contains";
    /** represents the title filter*/
    private static  final String TITLE = "file";

    /**This is the matchers data member*/
    Matcher titleMatch;

    /**This is the matcher for these files
     * @param input the filter name line
     * @return uf the filtter patterns matches
     */
    @Override
    public boolean matches(String input) {
        titleMatch = TitleFilters.titlePattern.matcher(input);
        return titleMatch.matches();
    }

    /**
     * creates a filter according to the given name
     * @return  new filter if name is valid
     * @throws FilterNameError
     */
    @Override
    public FileFilter getFilter(String input) throws FilterFactoryExceptions {
        String name = titleMatch.group(1);
        String seq = titleMatch.group(2);
        FileFilter currentFilter;
            switch (name) {
                case PREFFIX: {
                    currentFilter = new PrefixFilter(seq);
                    break;
                }
                case SUFFIX: {
                    currentFilter = new SuffixFilter(seq);
                    break;
                }
                case CONTAINS: {
                    currentFilter = new ContainsFilter(seq);
                    break;
                }
                case TITLE:{
                    currentFilter = new TitleFilter(seq);
                }
                default:
                    throw new FilterNameError();
            }
            if (titleMatch.group(3) != null)
                return new NotFilter(currentFilter);
            return currentFilter;
        }
    }

