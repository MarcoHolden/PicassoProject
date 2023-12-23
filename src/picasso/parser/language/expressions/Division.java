package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the divide operator in the Picasso language.
 * 
 * @author Nick Lagges
 * 
 */
public class Division extends ExpressionTreeNode {
	
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	/**
	 * Create a divide expression
	 * 
	 * @param left the numerator
	 * @param right the denominator
	 */
	public Division(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the division of
	 * the operator's two arguments.
	 * 
	 * @return the color from evaluating the division of the two arguments
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result1 = left.evaluate(x, y);
		RGBColor result2 = right.evaluate(x, y);
		double red;
		double green;
		double blue;
		if (result2.getRed() == 0) {
			red = 0;
		}
		else {
			red = result1.getRed() / result2.getRed();
		}
		if (result2.getGreen() == 0) {
			green = 0;
		}
		else {
			green = result1.getGreen() / result2.getGreen();
		}
		if (result2.getBlue() == 0) {
			blue = 0;
		}
		else {
			blue = result1.getBlue() / result2.getBlue();
		}
		return new RGBColor(red, green, blue);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Division)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		Division div = (Division) o;

		// check if their parameters are equal
		if (this.left.equals(div.left) && this.right.equals(div.right)) {
			return true;
		} else {
			return false;
		}
		
	}

}
