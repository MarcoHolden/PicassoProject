package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import java.lang.Math;


/** 
 * Represents the exponent function in picasso
 * 
 * @author Barrett Bourgeois
 */
public class Exp extends UnaryFunction {

	/** 
	 * Create an exponent expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to expponent
	 */
	public Exp(ExpressionTreeNode param) {
		super(param);
	}
	
	/**
	 * Evaluates this expression at the given x,y point by evaluating
	 * the exponent of the functions parameter. 
	 * 
	 * @return The RGB color from evaluating the expression
	 * @param x coordinate
	 * @param y coordinate
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.exp(result.getRed());
		double green = Math.exp(result.getGreen());
		double blue = Math.exp(result.getBlue());

		return new RGBColor(red, green, blue);
	}

}
