package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the divide function.
 * 
 * @author Zach Moore
 * 
 */
public class ExponentiateAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the exponentiate token

		ExpressionTreeNode rightETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode leftETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		return new Exponentiation(leftETN, rightETN);
	}
}