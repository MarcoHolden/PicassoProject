package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.*;

/**
 * Random semantic analyzer class
 * @author Zachary Moore
 */
public class RandomAnalyzer implements SemanticAnalyzerInterface {

	/**
	 * @param tokens
	 * @return new Random()
	 */
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		
		tokens.pop(); // Need to remove the random token

		return new Random();
	}

}