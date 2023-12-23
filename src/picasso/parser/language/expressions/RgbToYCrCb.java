package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * Represents the rgbToYCrCb function in the picasso language
 * 
 * @Author Barrett Bourgeois
 */
public class RgbToYCrCb extends UnaryFunction {

	/**
	 * Create an rgbTOYCrCb expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to rgbToYCrCb
	 */
	public RgbToYCrCb(ExpressionTreeNode param) {
		super(param);
	}
	
	/** 
	 * Evaluates the expression at the given x,y point by evaluating the 
	 * rgbToCrCb of the functions parameter
	 * 
	 * @return RGB color of rgbToYCrCb
	 */
	@Override
	public RGBColor evaluate (double x, double y) {
		
		RGBColor result = param.evaluate(x,  y); 
		double red = result.getRed() * 0.2989 + result.getGreen() * 0.5866 + result.getBlue() * 0.1145; 
		double green = result.getRed() * -0.1687 + result.getGreen() * -0.3312 + result.getBlue() * 0.5;
		double blue = result.getRed() * 0.5 + result.getGreen() * -0.4183 + result.getBlue() * -0.0816;
		return new RGBColor(red, green, blue); 
	}

}
