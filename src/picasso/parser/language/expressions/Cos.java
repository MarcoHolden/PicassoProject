package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Cos evaluator class
 * @author Nick Lagges
 */
public class Cos extends UnaryFunction {

	/**
	 * @param param
	 */
	public Cos(ExpressionTreeNode param) {
		super(param);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 * @return cosColor
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		//changes x and y input into radians for cos evaluation
		RGBColor result = param.evaluate(x, y);
		double red = Math.cos(result.getRed());
		double green = Math.cos(result.getGreen());
		double blue = Math.cos(result.getBlue());

		return new RGBColor(red, green, blue);
	}

}
