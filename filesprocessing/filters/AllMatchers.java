package filesprocessing.filters;

import filesprocessing.BooleanFiltersMatcher;

public class AllMatchers {
    /**This an array with all the matchers*/
    public static FilterMatcher [] matcher = {new SizeFilterMatcher(), new BooleanFiltersMatcher(),
            new TitleFilterMatcher(), new AllFilterMatcher()};


}
