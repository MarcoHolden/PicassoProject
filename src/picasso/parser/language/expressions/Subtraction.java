package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the minus operator in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Zachary Moore
 * 
 */
public class Subtraction extends ExpressionTreeNode {
	
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	/**
	 * Create a minus expression that takes as a parameter the given expression
	 * 
	 * @param left the first operand
	 * @param right the second operand to subtract from the first
	 */
	public Subtraction(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the subtraction of
	 * the operator's two arguments.
	 * 
	 * @return the color from evaluating the subtraction of the two arguments
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result1 = left.evaluate(x, y);
		RGBColor result2 = right.evaluate(x, y);
		
		double red = result1.getRed() - result2.getRed();
		double green = result1.getGreen() - result2.getGreen();
		double blue = result1.getBlue() - result2.getBlue();

		return new RGBColor(red, green, blue);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Subtraction)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		Subtraction ad = (Subtraction) o;

		// check if their parameters are equal
		if (this.left.equals(ad.left) && this.right.equals(ad.right)) {
			return true;
		} else {
			return false;
		}
		
		//return true;
	}
	

}