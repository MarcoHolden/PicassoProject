package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the negate token
 * 
 * @Author Barrett Bourgeois
 */
public class NotToken extends CharToken implements OperationInterface {
	public NotToken() {
		super(CharConstants.BANG);
	}

}
