package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/** 
 * Represents the not function in picasso
 * 
 * @author Barrett Bourgeois
 */
public class Not extends UnaryFunction {

	/** 
	 * Create an exponent expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to exponent
	 */
	public Not(ExpressionTreeNode param) {
		super(param);
	}
	
	/**
	 * Evaluates this expression at the given x,y point by evaluating
	 * the not of the functions parameter. 
	 * 
	 * @return The RGB color from evaluating the expression
	 * @param x coordinate
	 * @param y coordinate
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		return new RGBColor(-result.getRed(), -result.getGreen(), -result.getBlue());
	}

}
