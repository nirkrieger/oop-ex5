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
	private Warning[] exceptions;

	/**
	 * Constructor
	 * @param sections sections
	 * @param exceptions exceptions
	 */
	ParseResult(Section[] sections, Warning[] exceptions) {
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
	Warning[] getExceptions() {
		return exceptions;
	}
}
