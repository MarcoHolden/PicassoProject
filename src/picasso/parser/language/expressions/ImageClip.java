package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Image;

import java.awt.Color;

/**
 * Represents the imageClip function in the Picasso language.
 * 
 * @author Wonjun Jo
 * 
 */
public class ImageClip extends ExpressionTreeNode {
	
	// String filename;
	Image image;
	ExpressionTreeNode xExpr;
	ExpressionTreeNode yExpr;
	

	/**
	 * Create a imageClip expression that takes as a parameter the filename as well as the x and y expressions
	 * 
	 * @param xExpr x expression
	 * @param yExpr y expression
	 */
	public ImageClip(Image image, ExpressionTreeNode xExpr, ExpressionTreeNode yExpr) {
		this.image = image;
		this.xExpr = xExpr;
		this.yExpr = yExpr;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the imageClip of
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
		
		double xClip = clip(xEval);
		double yClip = clip(yEval);
		
		RGBColor origColor = image.evaluate(xClip, yClip); 
		
		return origColor;
	}
	
	/**
	 * Clip a double at either [-1, 1]
	 * This is the same functionality as clamp, but called it clip to maintain consistency with ImageClip
	 * In other words, discards the amount that val exceeds either boundary. 
	 * 
	 * @param val a double to be clipped
	 * @return a double that is clipped at either [-1, 1]
	 */
	private static double clip(double val) {
		double result = val;
		if (val > 1) {
			result = 1;
		} else if (val < -1) {
			result = -1;
		}
		return result;
	}
	
	
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof ImageClip)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		ImageClip imgClip = (ImageClip) o;

		// check if their parameters are equal
		if (this.image.equals(imgClip.image) && this.xExpr.equals(imgClip.xExpr) && this.yExpr.equals(imgClip.yExpr)) {
			return true;
		} else {
			return false;
		}
	}
	

}
