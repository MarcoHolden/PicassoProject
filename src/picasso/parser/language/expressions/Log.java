package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * Represents the log function in the Picasso Langauge
 * 
 * @Author Barrett Bourgeois
 */

public class Log extends UnaryFunction {
	
	/** 
	 * Create a log expression
	 * 
	 * @param param the expression to log
	 */
	public Log(ExpressionTreeNode param) {
		super(param);
	}
	
	/**
	 * Evaluates this expression at the given x,y point by evaluating
	 * the log of the functions parameter. 
	 * 
	 * @return The RGB color from evaluating the expression
	 * @param x coordinate
	 * @param y coordinate
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		if (x == 0) {
			x = 0.000000001;
		}
		if (y == 0) {
			y = 0.000000001; 
		}
		
		RGBColor result = param.evaluate(x, y);
		
		double red = 0; 
		double green = 0;
		double blue = 0;
		
		if (result.getRed() != 0) {
			red = Math.log(Math.abs(result.getRed()));
		}
		if (result.getGreen() != 0) {
			green = Math.log(Math.abs(result.getGreen()));
		}
		if (result.getBlue() != 0) {
			blue = Math.log(Math.abs(result.getBlue()));
		}

		return new RGBColor(red, green, blue);
	}
}
