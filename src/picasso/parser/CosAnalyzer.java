package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.*;

/**
 * Cos semantic analyzer class
 * @author Nick Lagges
 */
public class CosAnalyzer extends UnaryFunctionAnalyzer {

	/**
	 * @param tokens
	 * @return cosETN
	 * @see picasso.parser.UnaryFunctionAnalyzer#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		// TODO Auto-generated method stub
		tokens.pop(); // Need to remove the floor token
		// the parameter is the next token(s) on the stack.
		// But, it needs to be processed
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		Cos cosETN = new Cos(paramETN);
		return cosETN;
	}

}
