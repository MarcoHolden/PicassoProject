package picasso.parser.language.expressions;

import picasso.model.ImprovedNoise;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the perlinBW function in the Picasso language.
 * 
 * @Author Barrett Bourgeois
 */ 
public class PerlinBW extends BinaryOperators {
	/**
	 * Creates a Perlin Color expression that takes right and left as parameters
	 * 
	 * @param left node
	 * @param right node
	 */
	public PerlinBW(ExpressionTreeNode leftPara, ExpressionTreeNode rightPara) {
		super(leftPara, rightPara);
	}
	
	/**
	 * This function uses improvedNoise to create the noisy distributions
	 * 
	 *  @return the color from PerlinColor
	 */
	@Override
	public RGBColor evaluate(double x, double y) { 
		RGBColor right = rightPara.evaluate(x, y);
		RGBColor left = leftPara.evaluate(x, y);
		double grey = ImprovedNoise.noise(left.getRed() + right.getRed(), 
				left.getGreen() + right.getGreen(), left.getBlue() + right.getBlue());
		return new RGBColor(grey, grey, grey);
	}
}
