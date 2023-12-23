package picasso.parser;

import java.util.Stack;
import picasso.parser.tokens.Token;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Exp;

/** 
 * Handles parsing for the exponent function
 * 
 * @author Barrett Bourgeois
 */
public class ExpAnalyzer extends UnaryFunctionAnalyzer {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		Exp expETN = new Exp(paramETN);
		return expETN; 
	}

}
