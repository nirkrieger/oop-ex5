package fileprocessing.filters;

import fileprocessing.BooleanFiltersMatcher;

import java.util.List;
import java.util.regex.Matcher;

public class AllMatchers {
    /**This an array with all the matchers*/
    public static FilterMatcher [] matcher = {new SizeFilterMatcher(), new BooleanFiltersMatcher(),
            new TitleFilterMatcher(), new AllFilterMatcher()};


}
