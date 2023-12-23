package picasso.parser;


import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Not;
import picasso.parser.tokens.Token;

/** 
 * Handles parsing for the exponent function
 * 
 * @author Barrett Bourgeois
 */
public class NotAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		Not notETN = new Not(paramETN);
		return notETN; 
	}

}
