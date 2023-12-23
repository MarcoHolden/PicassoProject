package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the multiplication operator in the Picasso language.
 * 
 * @author Zachary Moore
 * 
 */
public class Multiplication extends ExpressionTreeNode {
	
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	/**
	 * Create a plus expression that takes as a parameter the given expression
	 * 
	 * @param left the first one to multiply
	 * @param right the second one to multiply
	 */
	public Multiplication(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the multiplication of
	 * the operator's two arguments.
	 * 
	 * @return the color from evaluating the multiplication of the two arguments
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result1 = left.evaluate(x, y);
		RGBColor result2 = right.evaluate(x, y);
		
		double red = result1.getRed() * result2.getRed();
		double green = result1.getGreen() * result2.getGreen();
		double blue = result1.getBlue() * result2.getBlue();

		return new RGBColor(red, green, blue);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Multiplication)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		Multiplication mult = (Multiplication) o;

		// check if their parameters are equal
		if (this.left.equals(mult.left) && this.right.equals(mult.right)) {
			return true;
		} else {
			return false;
		}
		
	}

}
