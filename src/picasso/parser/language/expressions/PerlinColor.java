package picasso.parser.language.expressions;

import picasso.model.ImprovedNoise;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the perlinColor function in the Picasso language.
 * 
 * @Author Barrett Bourgeois
 */ 
public class PerlinColor extends BinaryOperators {
	/**
	 * Creates a Perlin Color expression that takes right and left as parameters
	 * 
	 * @param left node
	 * @param right node
	 */
	public PerlinColor(ExpressionTreeNode leftPara, ExpressionTreeNode rightPara) {
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
		double red = ImprovedNoise.noise(left.getRed() + 0.3, right.getRed() + 0.3, 0);
		double green = ImprovedNoise.noise(left.getGreen() - 0.8 , right.getGreen() - 0.8, 0);
		double blue = ImprovedNoise.noise(left.getBlue() + 0.1, right.getBlue() + 0.1, 0);
		return new RGBColor(red, green, blue);
		
	}
}
