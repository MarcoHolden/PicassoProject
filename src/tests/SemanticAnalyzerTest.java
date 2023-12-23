/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.SemanticAnalyzer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.*;
import picasso.parser.tokens.functions.*;
import picasso.parser.tokens.operations.*;

/**
 * Test the parsing from the Stack (not as easy as using a String as input, but
 * helps to isolate where the problem is)
 * 
 * @author Sara Sprenkle
 *
 */
class SemanticAnalyzerTest {

	private SemanticAnalyzer semAnalyzer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		semAnalyzer = SemanticAnalyzer.getInstance();
	}
	
	@Test
	void testParseAddition() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PlusToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Addition(new X(), new Y()), actual);
	}
	
	@Test
	void testParseMultiplication() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new TimesToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Multiplication(new X(), new Y()), actual);
	}
	
	@Test
	void testParseDivision() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new DivideToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Division(new X(), new Y()), actual);
	}
	
	@Test
	void testParseCos() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new CosToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Cos(new X()), actual);
	}
	
	@Test
	void testParseSine() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new SinToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Sine(new X()), actual);
	}
	
	@Test
	void testParseTan() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new TanToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Tan(new X()), actual);
	}
	
	@Test
	void testParseAtan() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new AtanToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Atan(new X()), actual);
	}
	
	@Test
	void testParseClamp() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new ClampToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Clamp(new X()), actual);
	}
	
	@Test
	void testParseCeil() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new CeilToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Ceil(new X()), actual);
	}
	
	@Test
	void testParseFloor() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new FloorToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Floor(new X()), actual);
	}
	
	@Test
	void testParseAbs() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new AbsToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Abs(new X()), actual);
	}
	
	@Test
	void testParseAssignment() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("a"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new AssignmentToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new Assignment(new Variable("a"), new X()), actual);
	}
	
	void testParseAssignmentBinop() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("a"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PlusToken());
		tokens.push(new AssignmentToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new Assignment(new Variable("a"), new Addition(new X(), new Y())), actual);
	}
	
	@Test
	void testParseImageWrap() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new ImageToken("vortex.jpg"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new PlusToken());
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ImageWrapToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new ImageWrap(new Image("vortex.jpg"), new Addition(new X(), new X()), new Y()), actual);
	}
	
	@Test
	void testParseImageClip() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new ImageToken("vortex.jpg"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new PlusToken());
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ImageClipToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new ImageClip(new Image("vortex.jpg"), new Addition(new X(), new X()), new Y()), actual);
	}
	
	@Test
	void testParseImage() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new ImageToken("vortex.jpg"));

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Image("vortex.jpg"), actual);

	}
	
	
	@Test
	void testParseSubtraction() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new MinusToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Subtraction(new X(), new Y()), actual);
	}
	
	@Test
	void testParseModulo() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ModToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Modulo(new X(), new Y()), actual);
	}
	
	
	@Test
	void testParseExponentiate() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ExponentiateToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Exponentiation(new X(), new Y()), actual);
	}
  
  @Test
  void testParseNot() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new NotToken());
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new Not(new X()), actual);
	}
	
	@Test
	void testParseExp() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new ExpToken());
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new Exp(new X()), actual);
	}
	
	@Test
	void testParseLog() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new LogToken());
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new Log(new X()), actual);
	}
	
	@Test
	void testParseWrap() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new WrapToken());
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new Wrap(new X()), actual);
	}
	
	@Test
	void testParsePerlinBW() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PerlinBWToken());
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new PerlinBW(new X(), new Y()),  actual);
	}
	
	@Test
	void testParsePerlinColor() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PerlinColorToken());
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new PerlinColor(new X(), new Y()),  actual);
	}
	
	@Test
	void testParseRgbToYCrCb() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new RgbToYCrCbToken());
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new RgbToYCrCb(new X()),  actual);
	}
	@Test
	void testParseYCrCbToRGB() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new YCrCbToRGBToken());
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		assertEquals(new YCrCbToRGB(new X()),  actual);
	}

}
