package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Subtraction;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the minus or "subtraction function".
 * 
 * @author Robert C. Duvall
 * @author Zachary Moore
 * 
 */
public class MinusAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the minus token

		// The first token pop should be the parameter on the right when expression was in infix notation
		ExpressionTreeNode rightETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode leftETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Subtraction(leftETN, rightETN);
	}

}
