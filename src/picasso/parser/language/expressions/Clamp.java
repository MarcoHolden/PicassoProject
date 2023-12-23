package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the clamp function in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Wonjun Jo
 * 
 */
public class Clamp extends UnaryFunction {

	/**
	 * Create a clamp expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to floor
	 */
	public Clamp(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the clamp of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the clamp of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = result.getRed();
		double green = result.getGreen();
		double blue = result.getBlue();
		if (red > 1) {
			red = 1;
		} else if (red < -1) {
			red = -1;
		}
		if (green > 1) {
			green = 1;
		} else if (green < -1) {
			green = -1;
		}
		if (blue > 1) {
			blue = 1;
		} else if (blue < -1) {
			blue = -1;
		}

		return new RGBColor(red, green, blue);
	}

}
