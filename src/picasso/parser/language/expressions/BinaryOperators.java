package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode ; 

/**
 * 
 * Represents a binary operation to be extended by the assignment class
 * @Author Barrett Bourgeois
 * 
 */

public abstract class BinaryOperators extends ExpressionTreeNode{
	
	ExpressionTreeNode leftPara;
	ExpressionTreeNode rightPara;
	

	/** 
	 * 
	 * @param leftPara
	 * @param rightPara
	 */
	public BinaryOperators(ExpressionTreeNode leftPara, ExpressionTreeNode rightPara) {
		this.leftPara = leftPara;
		this.rightPara = rightPara;
		
	}
	
	/**
	 * Returns the string representation of the function in the format "ClassName:
	 * Left Parameter, Right Parameter"
	 */
	@Override
	public String toString() {
		return this.getClass() + ": " + leftPara + ", " + rightPara;
		
	}
	
	/*
	 * An equivalence structure that returns true if the binary expressions are the same
	 *
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof BinaryOperators)) {
			return false;
		}
		BinaryOperators a = (BinaryOperators) o;
		return rightPara.equals(a.rightPara) && leftPara.equals(a.leftPara) && this.getClass().equals(a.getClass());
	}

}
