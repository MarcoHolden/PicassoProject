package picasso.parser;

import java.util.Stack;
import picasso.parser.tokens.Token;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Log;

/** 
 * Handles parsing for the log function
 * 
 * @author Barrett Bourgeois
 */
public class LogAnalyzer extends UnaryFunctionAnalyzer {
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		Log logETN = new Log(paramETN);
		return logETN; 
	}

}
