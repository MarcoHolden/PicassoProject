package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Image;

import java.awt.Color;

/**
 * Represents the imageWrap function in the Picasso language.
 * 
 * @author Wonjun Jo
 * 
 */
public class ImageWrap extends ExpressionTreeNode {
	
	// String filename;
	Image image;
	ExpressionTreeNode xExpr;
	ExpressionTreeNode yExpr;
	

	/**
	 * Create a imageWrap expression that takes as a parameter the filename as well as the x and y expressions
	 * 
	 * @param xExpr x expression
	 * @param yExpr y expression
	 */
	public ImageWrap(Image image, ExpressionTreeNode xExpr, ExpressionTreeNode yExpr) {
		this.image = image;
		this.xExpr = xExpr;
		this.yExpr = yExpr;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the addition of
	 * the operator's two arguments.
	 * 
	 * @return the color from evaluating the addition of the two arguments
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		RGBColor result1 = xExpr.evaluate(x, y);
		RGBColor result2 = yExpr.evaluate(x, y);
		
		double xEval = (result1.getRed() + result1.getGreen() + result1.getBlue())/3;
		double yEval = (result2.getRed() + result2.getGreen() + result2.getBlue())/3;
		
		double xWrap = wrapAround(xEval);
		double yWrap = wrapAround(yEval);
		
		RGBColor origColor = image.evaluate(xWrap, yWrap); 
		
		return origColor;
	}
	
	/**
	 * Wraps a double around [-1, 1]
	 * In other words, the amount that exceeds boundaries is added again 
	 * to the other side of the boundary until amount does not exceed boundary 
	 * 
	 * @param val a double to be wrapped around
	 * @return a double that is wrapped around [-1, 1]
	 */
	private static double wrapAround(double val) {
		double result = val;
		if (val > 1) {
			result = -1 + (val - 1)%2;
		} else if (val < -1) {
			result = 1 - (-1 - val)%2;
		}
		return result;
	}
	
	
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof ImageWrap)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		ImageWrap imgWrap = (ImageWrap) o;

		// check if their parameters are equal
		if (this.image.equals(imgWrap.image) && this.xExpr.equals(imgWrap.xExpr) && this.yExpr.equals(imgWrap.yExpr)) {
			return true;
		} else {
			return false;
		}
	}
	

}
