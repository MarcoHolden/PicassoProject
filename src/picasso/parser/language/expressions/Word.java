package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a word expression in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Nick Lagges
 * 
 */
public class Word extends ExpressionTreeNode {
	
	ExpressionTreeNode word;

	/**
	 * Create a word expression that takes as a parameter the given expression
	 * 
	 * @param left the first one to add
	 * @param right the second one to add
	 */
	public Word(ExpressionTreeNode word) {
		this.word = word;
	}

	/**
	 * Evaluates this expression at the given x,y
	 * 
	 * @return the color from evaluating the addition of the two arguments
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = word.evaluate(x, y);		
		double red = result.getRed();
		double green = result.getGreen();
		double blue = result.getBlue();

		return new RGBColor(red, green, blue);
	}
	
	public ExpressionTreeNode getWord() {
		return this.word;
	}	

}
