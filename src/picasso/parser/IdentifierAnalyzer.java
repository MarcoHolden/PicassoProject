package picasso.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.X;
import picasso.parser.language.expressions.Y;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.view.ErrorHandling;

import javax.swing.*;

/**
 * Handle an identifier token 
 * 
 * @author Sara Sprenkle
 *
 */
public class IdentifierAnalyzer implements SemanticAnalyzerInterface {

	static Map<String, ExpressionTreeNode> idToExpression = new HashMap<String, ExpressionTreeNode>();
	
	final JFrame parent = new JFrame();
	static {
		// We always have x and y defined.
		idToExpression.put("x", new X());
		idToExpression.put("y", new Y());
	}
	
	/**
	 * creates an expression tree node from a stack of tokens. Will prompt the user
	 * to enter a different input if there are any parsing errors
	 * 		will throw a ParseException if unrecognized identifier analyzer
	 * 
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		Token token = tokens.pop();
		IdentifierToken t = (IdentifierToken) token;
		String id = t.getName();
		//System.out.println(id);
		ExpressionTreeNode mapped = idToExpression.get(id);
		
		if (mapped != null) {
			return mapped;
		}
		//System.out.println("test");
		if (mapped == null) {
			throw new ParseException("Unrecognized Identifier Variable: " + id);
		}

		// TODO : What should we do if we don't recognize the identifier?
		// Is that an error? Or, could there a valid reason?
		return null;
	}
	
	public static void storeAssignmentResult(String variable, ExpressionTreeNode result) {
		idToExpression.put(variable, result); 
	}

}
