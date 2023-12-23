package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Tan evaluator class
 * @author Nick Lagges
 */
public class Tan extends UnaryFunction {

	/**
	 * @param param
	 */
	public Tan(ExpressionTreeNode param) {
		super(param);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 * @return RGBColor
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		//changes x and y input into radians for cos evaluation
		RGBColor result = param.evaluate(x, y);
		double red = Math.tan(result.getRed());
		double green = Math.tan(result.getGreen());
		double blue = Math.tan(result.getBlue());

		return new RGBColor(red, green, blue);
	}

}
