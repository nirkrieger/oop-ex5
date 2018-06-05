package filesprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Pattern;

public abstract class TitleFilter implements FileFilter{

    /**This is the filters pattern*/
    static Pattern titlePattern = Pattern.compile("([\\w\\./\\-]+)#([\\w\\./\\- ]+)(#NOT)?");

	/** This is the sequence we are checking for*/
	private String seq;
	/**
	 * Constructor.
	 * @param seq sequence to filter by.
	 */
	public TitleFilter(String seq) {
		this.seq = seq;
	}

	/**
	 * @return filter's sequence.
	 */
	public String getSeq() {
		return seq;
	}
}
