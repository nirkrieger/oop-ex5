package filesprocessing.filters;

public class AllMatchers {
	/**
	 * This an array with all the matchers
	 */
	public static FilterMatcher[] matcher = {
			new AllFilterMatcher(),
			new SizeFilterMatcher(),
			new BetweenFilterMatcher(),
			new BooleanFiltersMatcher(),
			new TitleFilterMatcher(),
	};
}
