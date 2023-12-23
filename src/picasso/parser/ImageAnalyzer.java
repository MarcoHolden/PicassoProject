package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.ImageToken;

/**
 * Handles parsing the Image function.
 * 
 * @author Robert C. Duvall
 * @author Wonjun Jo
 * 
 */
public class ImageAnalyzer implements SemanticAnalyzerInterface {
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		ImageToken imageToken = (ImageToken) tokens.pop(); // Remove the Image token
		
		// now we need to return a Image expressionTreeNode with the filename as the argument
		String filename = imageToken.getImageName();
		return new Image(filename);
	}

}
