package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the plus operator in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Wonjun Jo
 * 
 */
public class Addition extends ExpressionTreeNode {
	
	
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	/**
	 * Create a plus expression that takes as a parameter the given expression
	 * 
	 * @param left the first one to add
	 * @param right the second one to add
	 */
	public Addition(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the addition of
	 * the operator's two arguments.
	 * 
	 * @return the color from evaluating the addition of the two arguments
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result1 = left.evaluate(x, y);
		RGBColor result2 = right.evaluate(x, y);
		
		double red = result1.getRed() + result2.getRed();
		double green = result1.getGreen() + result2.getGreen();
		double blue = result1.getBlue() + result2.getBlue();

		return new RGBColor(red, green, blue);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Addition)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		Addition ad = (Addition) o;

		// check if their parameters are equal
		if (this.left.equals(ad.left) && this.right.equals(ad.right)) {
			return true;
		} else {
			return false;
		}
		
		//return true;
	}
	
	@Override
	public String toString() {
		return "Addition [left=" + left + ", right=" + right + "]";
	}

	

}
