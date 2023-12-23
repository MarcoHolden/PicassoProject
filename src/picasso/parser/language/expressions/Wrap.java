package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the wrap function in the Picasso language. 
 * 
 * @Author Barrett Bourgeois
 */
public class Wrap extends UnaryFunction {

	/**
	 * Create a wrap expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to wrap
	 */
	public Wrap(ExpressionTreeNode param) {
		super(param); 
	}
	
	/**
	 * Evaluates the expression at the given x,y point by evaluating the wrap fo the 
	 * functions parameter.
	 * 
	 * @return the color from evaluating the wrap of the expressions parameter. 
	 */
	@Override
	public RGBColor evaluate(double x, double y) { 
		RGBColor result = param.evaluate(x, y);
		double red = wrap(result.getRed());
		double green = wrap(result.getGreen());
		double blue = wrap(result.getBlue());
		return new RGBColor(red, green, blue);
	}
	
	public static double wrap(double num) { 
		num += 3;
		num %= 2;
		num -= 1;
		return num; 
	}
	

}
