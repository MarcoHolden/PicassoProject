package picasso.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.*;
import picasso.parser.tokens.functions.*;
import picasso.parser.tokens.operations.*;

import javax.swing.*;
import java.util.Random;

/**
 * Handle an word token 
 * 
 * @author Nick Lagges
 *
 */
public class WordAnalyzer implements SemanticAnalyzerInterface {

	static Map<String, ExpressionTreeNode> wordToExpression = new HashMap<String, ExpressionTreeNode>();
	
	final JFrame parent = new JFrame();
	static {
		// We always have x and y defined.
		wordToExpression.put("x", new X());
		wordToExpression.put("y", new Y());
	}
	private Stack<Token> wordTokens;
	
	/**
	 * MUST BE ONE CONTINUOUS WORD
	 * (no symbols, only letters (any case)
	 * creates an expression tree node from a word tokens.
	 * generates random expressions based on word length
	 * generates random color 
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		Token token = tokens.pop();
		WordToken t = (WordToken) token;
		String id = t.getName();
		ExpressionTreeNode mapped = wordToExpression.get(id);
		if (mapped != null) {
			return mapped;
		}
		//the different tokens and variables
		String[] vars = {"x", "y"};
		FunctionToken[] exprTokens = {new AbsToken(), new AtanToken(), new CeilToken(), 
									 new ClampToken(), new CosToken(), new FloorToken(), 
									 new SinToken(), new TanToken()};
		Random random = new Random();
		int i = 0;
		int randnum;
		if (mapped == null) {
			while (i < id.length()) {
				tokens.push(new IdentifierToken(vars[random.nextInt(2)]));
				if (i >0) {
					randnum = random.nextInt(exprTokens.length);
					tokens.push(new PlusToken());
					tokens.push(exprTokens[randnum]);
				}
				i++;
			}
			tokens.push(new ColorToken((random.nextInt(21)-10) / 10.0, (random.nextInt(21)-10) / 10.0, (random.nextInt(21)-10) / 10.0));
			tokens.push(new PlusToken());
			wordTokens = tokens;
			ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
			//if word length is only 1, it will return a cos expression every time
			Cos cosETN = new Cos(paramETN);
			return cosETN;
		}

		// TODO : What should we do if we don't recognize the identifier?
		// Is that an error? Or, could there a valid reason?
		return null;
	}
	
	public Stack<Token> getTokens() {
		return wordTokens;
	}
	
	public static void storeAssignmentResult(String variable, ExpressionTreeNode result) {
		wordToExpression.put(variable, result); 
	}

}
