package picasso.parser.language.expressions;

import java.util.Objects;

import picasso.parser.IdentifierAnalyzer;
import picasso.parser.language.ExpressionTreeNode;

/** 
 * Represents the assignment operator that takes as a parameter the given expression and a variable. 
 * 
 * @author Barrett Bourgeois
 */
public class Assignment extends BinaryOperators{
	
	private Variable variable; 
	
	/**
	 * Create an expression that takes as a parameter the given expression
	 * 
	 * @param variable, the inputed variable to the function
	 * @param Right Para, an expression tree of everything on the right of the equals sign 
	 */
	
	public Assignment(Variable variable, ExpressionTreeNode rightPara ) {
		super(variable, rightPara);
		this.variable = variable;
	}
	

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(variable);
	}


	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assignment other = (Assignment) obj;
		return Objects.equals(variable, other.variable);
	}


	/**
	 * Evaluates this expression at the given x,y point by evaluating the expression
	 * @return the color from evaluating the the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		String var = variable.getName();
		IdentifierAnalyzer.storeAssignmentResult(var, this.rightPara);
		return this.rightPara.evaluate(x, y);
	}
}
