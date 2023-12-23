package picasso.parser.tokens;

/**
 * Represents an word expression
 * 
 * @author Owen Astrachan
 * @author Nick Lagges
 * 
 */
public class WordToken extends Token {

	private final String myName;

	public WordToken(String value) {
		super("Word Token");
		myName = value;
	}

	public boolean equals(Object o) {
		if( o == this ) {
			return true;
		}
		if (!(o instanceof WordToken)) {
			return false;
		}
		WordToken rhs = (WordToken) o;
		return myName.equals(rhs.myName);
	}

	/**
	 * Returns the identifier's name
	 * @return the identifier's name
	 */
	public String getName() {
		return myName;
	}

	public String toString() {
		return super.toString() + ": " + myName;
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return true;
	}

}
