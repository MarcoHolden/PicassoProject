package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.operations.PlusToken;
import picasso.parser.tokens.operations.TimesToken;

/**
 * Tests of creating an expression tree from a string expression. Will have
 * compiler errors until some code is created.
 * 
 * @author Sara Sprenkle
 * 
 */
public class ExpressionTreeGeneratorTests {

	private ExpressionTreeGenerator parser;

	@BeforeEach
	public void setUp() throws Exception {
		parser = new ExpressionTreeGenerator();
	}

	@Test
	public void constantExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("[1,-1, 1]");
		assertEquals(new RGBColor(1, -1, 1), e);
	}

	@Test
	public void variableExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x");
		assertEquals(new X(), e);
	}

	@Test
	public void additionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x + y");
		assertEquals(new Addition(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x+y");
		assertEquals(new Addition(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] + y");
		assertEquals(new Addition(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x + y + [ -.51, 0, 1]");
		assertEquals(new Addition(new Addition(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void multiplicationExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x * y");
		assertEquals(new Multiplication(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x*y");
		assertEquals(new Multiplication(new X(), new Y()), e);

		e = parser.makeExpression("[0.99,-0.5,-1] * y");
		assertEquals(new Multiplication(new RGBColor(0.99, -0.5, -1), new Y()), e);

		e = parser.makeExpression("x * y * [ -.41, 0, 1]");
		assertEquals(new Multiplication(new Multiplication(new X(), new Y()), new RGBColor(-.41, 0, 1)), e);
	}
	
	@Test
	public void divisionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x / y");
		assertEquals(new Division(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x/y");
		assertEquals(new Division(new X(), new Y()), e);

		e = parser.makeExpression("[0.99,-0.5,-1] / y");
		assertEquals(new Division(new RGBColor(0.99, -0.5, -1), new Y()), e);

		e = parser.makeExpression("x / y / [ -.41, 0, 1]");
		assertEquals(new Division(new Division(new X(), new Y()), new RGBColor(-.41, 0, 1)), e);
	}	

	@Test
	public void parenthesesExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("( x + y )");
		assertEquals(new Addition(new X(), new Y()), e);

		e = parser.makeExpression("( x + (y + [ 1, 1, 1] ) )");
		assertEquals(new Addition(new X(), new Addition(new Y(), new RGBColor(1, 1, 1))), e);
	}

	@Test
	public void arithmeticStackTests() {
		Stack<Token> stack = parser.infixToPostfix("x + y * x");

		Stack<Token> expected = new Stack<>();
		expected.push(new IdentifierToken("x"));
		expected.push(new IdentifierToken("y"));
		expected.push(new IdentifierToken("x"));
		expected.push(new TimesToken());
		expected.push(new PlusToken());

		assertEquals(expected, stack);
	}
	
	// have everything for unary
	@Test
	public void floorFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("floor( x )");
		assertEquals(new Floor(new X()), e);

		e = parser.makeExpression("floor( x + y )");
		assertEquals(new Floor(new Addition(new X(), new Y())), e);
	}

	@Test
	public void cosFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("cos( x )");
		assertEquals(new Cos(new X()), e);
		
		e = parser.makeExpression("cos( x + y )");
		assertEquals(new Cos(new Addition(new X(), new Y())), e); 
	}
	
	@Test
	public void sineFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("sin( x )");
		assertEquals(new Sine(new X()), e);
		
		e = parser.makeExpression("sin( x + y )");
		assertEquals(new Sine(new Addition(new X(), new Y())), e); 
	}
	
	@Test
	public void tanFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("tan( x )");
		assertEquals(new Tan(new X()), e);
		
		e = parser.makeExpression("tan( x + y )");
		assertEquals(new Tan(new Addition(new X(), new Y())), e); 
	}
	
	@Test
	public void atanFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("atan( x )");
		assertEquals(new Atan(new X()), e);
		
		e = parser.makeExpression("atan( x + y )");
		assertEquals(new Atan(new Addition(new X(), new Y())), e); 
	}
	
	@Test
	public void clampFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("clamp( x )");
		assertEquals(new Clamp(new X()), e);
		
		e = parser.makeExpression("clamp( x + y )");
		assertEquals(new Clamp(new Addition(new X(), new Y())), e); 
	}
	
	@Test
	public void ceilFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("ceil( x )");
		assertEquals(new Ceil(new X()), e);
		
		e = parser.makeExpression("ceil( x + y )");
		assertEquals(new Ceil(new Addition(new X(), new Y())), e);
	}
	
	@Test
	public void absFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("abs( x )");
		assertEquals(new Abs(new X()), e);
		
		e = parser.makeExpression("abs( x + y )");
		assertEquals(new Abs(new Addition(new X(), new Y())), e);
	}
	
	@Test
	public void assignmentExpressionTests() {
		ExpressionTreeNode actual = parser.makeExpression("a = x");
		assertEquals(new Assignment(new Variable("a"), new X()), actual);
		
		ExpressionTreeNode e = parser.makeExpression("a = x + y");
		assertEquals(new Assignment(new Variable("a"), new Addition(new X(), new Y())), e);
	}
	
	@Test
	public void imageWrapFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("imageWrap(\"vortex.jpg\",x,y)");
		assertEquals(new ImageWrap(new Image("vortex.jpg"), new X(), new Y()), e);
		
		e = parser.makeExpression("imageWrap(\"vortex.jpg\",x+x,y+y)");
		assertEquals(new ImageWrap(new Image("vortex.jpg"), new Addition(new X(), new X()), new Addition(new Y(), new Y())), e);
	}
	
	@Test
	public void imageClipFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("imageClip(\"vortex.jpg\",x,y)");
		assertEquals(new ImageClip(new Image("vortex.jpg"), new X(), new Y()), e);
		
		e = parser.makeExpression("imageClip(\"vortex.jpg\",x+x,y+y)");
		assertEquals(new ImageClip(new Image("vortex.jpg"), new Addition(new X(), new X()), new Addition(new Y(), new Y())), e);
	}
	
	@Test
	public void imageFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("\"vortex.jpg\"");
		assertEquals(new Image("vortex.jpg"), e);

	}
	
	@Test
	public void subtractionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x - y");
		assertEquals(new Subtraction(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x-y");
		assertEquals(new Subtraction(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] - y");
		assertEquals(new Subtraction(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x - y - [ -.51, 0, 1]");
		assertEquals(new Subtraction(new Subtraction(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void modExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x % y");
		assertEquals(new Modulo(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x%y");
		assertEquals(new Modulo(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] % y");
		assertEquals(new Modulo(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x % y % [ -.51, 0, 1]");
		assertEquals(new Modulo(new Modulo(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void exponentiateExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x ^ y");
		assertEquals(new Exponentiation(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x^y");
		assertEquals(new Exponentiation(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] ^ y");
		assertEquals(new Exponentiation(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x ^ y ^ [ -.51, 0, 1]");
		assertEquals(new Exponentiation(new Exponentiation(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
  
	@Test
	public void notExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("!x");
		assertEquals(new Not(new X()), e);
	}
	
	@Test
	public void ExpExpressionTests() { 
		ExpressionTreeNode e = parser.makeExpression("exp(x)");
		assertEquals(new Exp(new X()), e);
	}
	
	@Test
	public void LogExpressionTests() { 
		ExpressionTreeNode e = parser.makeExpression("log(x)");
		assertEquals(new Log(new X()), e);
	}
	
	@Test
	public void WrapExpressionTests() { 
		ExpressionTreeNode e = parser.makeExpression("wrap(x)");
		assertEquals(new Wrap(new X()), e);
	}
	
	public void PerlinBWExpressionTest() { 
		ExpressionTreeNode e = parser.makeExpression("PerlinBW(x, y)");
		assertEquals(new PerlinBW(new X(), new Y()), e);
	}
	
	public void PerlinColorExpressionTest() { 
		ExpressionTreeNode e = parser.makeExpression("PerlinColor(x, y)");
		assertEquals(new PerlinColor(new X(), new Y()), e);
	}
	
	@Test 
	public void rgbToYCrCbExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("rgbToYCrCb(x)");
		assertEquals(new RgbToYCrCb(new X()), e);
	}
	
	@Test 
	public void YCrCbToRGBExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("yCrCbToRGB(x)");
		assertEquals(new YCrCbToRGB(new X()), e);
	}
}
