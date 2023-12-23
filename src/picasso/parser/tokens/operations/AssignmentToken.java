package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;


/** 
 * 
 * Represents the equal sign token
 * 
 */
public class AssignmentToken extends CharToken implements OperationInterface {
	public AssignmentToken() {
		super(CharConstants.EQUAL);
	}

}
