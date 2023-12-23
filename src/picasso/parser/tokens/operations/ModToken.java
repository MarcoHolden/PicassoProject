package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the modulo sign token
 * 
 */
public class ModToken extends CharToken implements OperationInterface {
	public ModToken() {
		super(CharConstants.MOD);
	}
}