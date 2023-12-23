package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Assignment;
import picasso.parser.tokens.IdentifierToken;
//import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.Variable; 

/**
 * Handles parsing the assignment operation.
 * 
 * @author Barrett Bourgeois
 * 
 */
public class AssignmentAnalyzer extends UnaryFunctionAnalyzer {
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the assignment token
		// the parameter is the next token(s) on the stack.
		// But, it needs to be processed
		ExpressionTreeNode rightPara = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		if (tokens.peek() instanceof IdentifierToken) { 
			IdentifierToken token =  (IdentifierToken) tokens.pop(); 
			Variable variable = new Variable(token.getName());
			return new Assignment(variable, rightPara);
		}
		else {
			return null;
		}
		
	}

}
