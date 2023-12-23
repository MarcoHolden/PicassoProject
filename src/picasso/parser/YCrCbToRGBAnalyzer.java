package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.YCrCbToRGB;

/** 
 * Handles parsing the rgbToYCrCb function
 * 
 * @Author Barrett Bourgeois
 */
public class YCrCbToRGBAnalyzer extends UnaryFunctionAnalyzer {
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		return new YCrCbToRGB(SemanticAnalyzer.getInstance().generateExpressionTree(tokens));
	}

}
