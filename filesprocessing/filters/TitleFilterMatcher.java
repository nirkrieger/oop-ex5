package filesprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Matcher;

public class TitleFilterMatcher implements FilterMatcher {

    /** represents prefix filter*/
    private static final String PREFIX = "prefix";
    /** represents suffix filter*/
    private static final String SUFFIX = "suffix";
    /** represents contains filter*/
    private static final String CONTAINS = "contains";
    /** represents the title filter*/
    private static  final String TITLE = "file";
    /** represents the name */
    private static final int NAME = 1;
    /** represents the first parameter*/
    private static final int FIRST_PARAMETER = 2;
    /**represents the Not paraneter*/
    private static final int NOT = 3;

    /**This is the matchers data member*/
    Matcher titleMatch;

    /**This is the matcher for these files
     * @param input the filter name line
     * @return uf the filtter patterns matches
     */
    @Override
    public boolean matches(String input) {
        titleMatch = TitleFilter.titlePattern.matcher(input);
        return titleMatch.matches();
    }

    /**
     * creates a filter according to the given name
     * @return  new filter if name is valid
     * @throws FilterNameError
     */
    //TODO: Document this.
    @Override
    public FileFilter getFilter(String input) throws FilterFactoryException {
        String name = titleMatch.group(NAME);
        String seq = titleMatch.group(FIRST_PARAMETER);
        FileFilter currentFilter;
            switch (name) {
                case PREFIX: {
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
                    currentFilter = new FileNameFilter(seq);
                    break;
                }
                default:
                    throw new FilterNameError();
            }
            if (titleMatch.group(NOT) != null)
                return new NotFilter(currentFilter);
            return currentFilter;
        }
    }

