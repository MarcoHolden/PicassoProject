package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * Random evaluator class
 * @author Zachary Moore
 */
public class Random extends ExpressionTreeNode {

	public Random() {
	}

	/**
	 * @param x
	 * @param y
	 * @return randomly generated color
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		double red = 2 * Math.random() - 1;
		double green = 2 * Math.random() - 1;
		double blue = 2 * Math.random() - 1;
		
		return new RGBColor(red,green,blue);

	}

}