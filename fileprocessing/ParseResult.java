package fileprocessing;

/**
 * Represents a parsing result of {@link SectionFactory}
 */
class ParseResult {
	/**
	 * result sections
	 */
	private Section[] sections;
	/**
	 * result exceptions
	 */
	private SectionParsingException[] exceptions;

	/**
	 * Constructor
	 * @param sections sections
	 * @param exceptions exceptions
	 */
	ParseResult(Section[] sections, SectionParsingException[] exceptions) {
		this.sections = sections;
		this.exceptions = exceptions;
	}

	/**
	 * @return sections
	 */
	Section[] getSections() {
		return sections;
	}

	/**
	 * @return exceptions
	 */
	SectionParsingException[] getExceptions() {
		return exceptions;
	}
}
