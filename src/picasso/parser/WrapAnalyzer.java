package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.Wrap;

/**
 * Handles parsing the wrap function
 * 
 * @author Barrett Bourgeois
 */

public class WrapAnalyzer extends UnaryFunctionAnalyzer {

		@Override
		public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
			tokens.pop();
			return new Wrap(SemanticAnalyzer.getInstance().generateExpressionTree(tokens));
		}
}		