package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the exponentiation operator in the Picasso language.
 * 
 * @author Zachary Moore
 * 
 */
public class Exponentiation extends ExpressionTreeNode {
	
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	/**
	 * Create an exponent expression that takes as a parameter the given expression
	 * 
	 * @param left the base
	 * @param right the exponent
	 */
	public Exponentiation(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the exponentiation of
	 * the operator's two arguments.
	 * 
	 * @return the color from evaluating the exponentiation of the two arguments
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result1 = left.evaluate(x, y);
		RGBColor result2 = right.evaluate(x, y);
		
		// Handles following problem cases
		// 0 ^ 0
		// 0 ^ -#
		// -# ^ (Pos.Fraction)
		
		// Result if 0 ^ 0 or 0 ^ -#
		double red = result1.getRed();
		double green = result1.getGreen();
		double blue = result1.getBlue();
		
		// Result if -# ^ (Pos.Fraction)
		if (red < 0 && red != -1 && 
				result2.getRed() > 0 && result2.getRed() != 1) {
			red = -1; // Pushes to black based on sample image in Picasso docs
		}
		
		// Result if no errors			
		else if (!(red == 0 && result2.getRed() <= 0)) {
			red = Math.pow(red, result2.getRed());
		}
		
		
		if (green < 0 && green != -1 && 
				result2.getGreen() > 0 && result2.getGreen() != 1) {
			green = -1;
		}
				
		else if (!(green == 0 && result2.getGreen() <= 0)) {
			green = Math.pow(green, result2.getGreen());
		}
		
		
		if (blue < 0 && blue != -1 && 
				result2.getBlue() > 0 && result2.getBlue() != 1) {
			blue = -1;
		}
				
		else if (!(blue == 0 && result2.getBlue() <= 0)) {
			blue = Math.pow(blue, result2.getBlue());
		}
				
				
		return new RGBColor(red, green, blue);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Exponentiation)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		Exponentiation exp = (Exponentiation) o;

		// check if their parameters are equal
		if (this.left.equals(exp.left) && this.right.equals(exp.right)) {
			return true;
		} else {
			return false;
		}
		
	}

}