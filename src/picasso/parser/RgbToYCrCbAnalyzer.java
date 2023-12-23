package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.RgbToYCrCb;

/** 
 * Handles parsing the rgbToYCrCb function
 * 
 * @Author Barrett Bourgeois
 */
public class RgbToYCrCbAnalyzer extends UnaryFunctionAnalyzer {
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		return new RgbToYCrCb(SemanticAnalyzer.getInstance().generateExpressionTree(tokens));
	}

}
