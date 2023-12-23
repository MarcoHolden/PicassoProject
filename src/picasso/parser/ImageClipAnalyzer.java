package picasso.parser;

import java.io.IOException;
import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageClip;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the ImageWrap function.
 * 
 * @author Robert C. Duvall
 * @author Wonjun Jo
 * 
 */
public class ImageClipAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the ImageClip token
		// the parameters are the next tokens on the stack.
		// But, they need to be processed
		
		ExpressionTreeNode yETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode xETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		
		Image imageETN = (Image) SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		if (!(imageETN instanceof Image)) {
			throw new ParseException("Argument is not an Image ETN");
		}
		
		return new ImageClip(imageETN, xETN, yETN);
	}

}
