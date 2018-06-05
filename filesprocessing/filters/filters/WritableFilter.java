package filesprocessing.filters.filters;

import filesprocessing.filters.filters.BooleanFilter;

import java.io.File;

public class WritableFilter extends BooleanFilter {
	/**
	 * represents if true or false.
	 */
	public WritableFilter(boolean flag) {
		super(flag);
	}

	/**This doeas the actual filtering
     * @param pathname the file
     * @return true if the file passes the filter, false otherwise
     */
    @Override
    public boolean accept(File pathname) {
        return logicalNotXor(super.accept(pathname),pathname.canWrite());
    }
}
