package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.ParseException;
import picasso.parser.Tokenizer;
import picasso.parser.tokens.*;
import picasso.parser.tokens.chars.*;
import picasso.parser.tokens.functions.*;
import picasso.parser.tokens.operations.*;

/**
 * Tests that the tokenizer tokens as expected. 
 * @author Sara Sprenkle
 */
public class TokenizerTest {

	Tokenizer tokenizer;
	List<Token> tokens;

	@BeforeEach
	public void setUp() throws Exception {
		tokenizer = new Tokenizer();
	}
	
	/**
	 * Test that parsing an expression with a comment works
	 */
	@Test
	public void testTokenizeComment() {
		String expression = "x // this is a comment";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(1, tokens.size());
		
		expression = "// everything is a comment";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(0, tokens.size());
	}

	/**
	 * Test that parsing a constant works
	 */
	@Test
	public void testTokenizeConstant() {
		String expression = ".324";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(.324), tokens.get(0));

		expression = "-1";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(-1), tokens.get(0));

		// No problems here; problem will be in next step (Semantic Analysis)
		expression = "-1.2";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(-1.2), tokens.get(0));
	}

	@Test
	public void testTokenizeColor() {
		String expression = "[1, 1, 1]";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ColorToken(1, 1, 1), tokens.get(0));

		expression = "[-1, 0, .5]";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ColorToken(-1, 0, .5), tokens.get(0));
	}

	@Test
	public void testTokenizeInvalidColor() {
		String expression = "[1, 1.0001, 1]";

		assertThrows(ParseException.class, () -> {
			tokens = tokenizer.parseTokens(expression);
		});
	}

	@Test
	public void testTokenizeBasicFunctionExpression() {
		String expression = "floor(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new FloorToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}

	@Test
	public void testTokenizeCombinedFunctionExpression() {
		String expression = "sin(floor(x))";
		List<Token> tokens = tokenizer.parseTokens(expression);
		// TODO: Check the tokens...
		assertEquals(new SinToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new FloorToken(), tokens.get(2));
		assertEquals(new LeftParenToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
		assertEquals(new RightParenToken(), tokens.get(6));
		
		expression = "cos(abs(x))";
		tokens = tokenizer.parseTokens(expression);
		// TODO: Check the tokens...
		assertEquals(new CosToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new AbsToken(), tokens.get(2));
		assertEquals(new LeftParenToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
		assertEquals(new RightParenToken(), tokens.get(6));
		
		expression = "tan(atan(x))";
		tokens = tokenizer.parseTokens(expression);
		// TODO: Check the tokens...
		assertEquals(new TanToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new AtanToken(), tokens.get(2));
		assertEquals(new LeftParenToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
		assertEquals(new RightParenToken(), tokens.get(6));
	}
	
	@Test
	public void testTokenizeBasicArithmeticExpression() {
		String expression = "x+y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new PlusToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		expression = "y + x + y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("y"), tokens.get(0));
		assertEquals(new PlusToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new PlusToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		
		expression = "x*y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new TimesToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		expression = "y * x * y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("y"), tokens.get(0));
		assertEquals(new TimesToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new TimesToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		
		expression = "y / x / y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("y"), tokens.get(0));
		assertEquals(new DivideToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new DivideToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		
		expression = "y+(x+y)*x";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("y"), tokens.get(0));
		assertEquals(new PlusToken(), tokens.get(1));
		assertEquals(new LeftParenToken(), tokens.get(2));
		assertEquals(new IdentifierToken("x"), tokens.get(3));
		assertEquals(new PlusToken(), tokens.get(4));
		assertEquals(new IdentifierToken("y"), tokens.get(5));
		assertEquals(new RightParenToken(), tokens.get(6));
		assertEquals(new TimesToken(), tokens.get(7));
		assertEquals(new IdentifierToken("x"), tokens.get(8));
		
		expression = "x-y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new MinusToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		expression = "y - x - y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("y"), tokens.get(0));
		assertEquals(new MinusToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new MinusToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		
		expression = "x%y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new ModToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		expression = "y % x % y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("y"), tokens.get(0));
		assertEquals(new ModToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new ModToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		
		expression = "x^y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new ExponentiateToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		expression = "y ^ x ^ y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("y"), tokens.get(0));
		assertEquals(new ExponentiateToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new ExponentiateToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		
		expression = "y + random() + y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("y"), tokens.get(0));
		assertEquals(new PlusToken(), tokens.get(1));
		assertEquals(new RandomToken(), tokens.get(2));
		assertEquals(new LeftParenToken(), tokens.get(3));
		assertEquals(new RightParenToken(), tokens.get(4));
		assertEquals(new PlusToken(), tokens.get(5));
		assertEquals(new IdentifierToken("y"), tokens.get(6));
		
		expression = "y / x / random()";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("y"), tokens.get(0));
		assertEquals(new DivideToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new DivideToken(), tokens.get(3));
		assertEquals(new RandomToken(), tokens.get(4));
		assertEquals(new LeftParenToken(), tokens.get(5));
		assertEquals(new RightParenToken(), tokens.get(6));
	}
	
	@Test
	public void TestTokenizeAssignment() {
		String expression = "a = x+y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("a"), tokens.get(0));
		assertEquals(new AssignmentToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new PlusToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
	}

	public void testTokenizeImageWrapExpression() {
		
		String expression = "imageWrap(\"vortex.jpg\",x+x,y)";
		tokens = tokenizer.parseTokens(expression);
		
		assertEquals(new ImageWrapToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new ImageToken("vortex.jpg"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new PlusToken(), tokens.get(5));
		assertEquals(new IdentifierToken("x"), tokens.get(6));
		assertEquals(new CommaToken(), tokens.get(7));
		assertEquals(new IdentifierToken("y"), tokens.get(8));
		assertEquals(new RightParenToken(), tokens.get(9));
	}
	
	@Test
	public void testTokenizeImageExpression() {
		
		String expression = "image(\"vortex.jpg\")";
		tokens = tokenizer.parseTokens(expression);
		
		assertEquals(new IdentifierToken("image"), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new ImageToken("vortex.jpg"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test 
	public void testTokenizeNot() {
		String expression = "!x";
		tokens =tokenizer.parseTokens(expression);
		assertEquals(new NotToken(), tokens.get(0));
		assertEquals(new IdentifierToken("x"), tokens.get(1));
	}

	@Test
	public void testTokenizeExp() {
		String expression = "exp(x)";
		tokens =tokenizer.parseTokens(expression);
		assertEquals(new ExpToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeLog() {
		String expression = "log(x)";
		tokens =tokenizer.parseTokens(expression);
		assertEquals(new LogToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeWrap() {
		String expression = "wrap(x)";
		tokens =tokenizer.parseTokens(expression);
		assertEquals(new WrapToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizePerlinBW() {
		String expression = "PerlinBW(x, y)";
		tokens =tokenizer.parseTokens(expression);
		assertEquals(new PerlinBWToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(45));
	}
	
	@Test
	public void testTokenizePerlinColor() {
		String expression = "PerlinColor(x, y)";
		tokens =tokenizer.parseTokens(expression);
		assertEquals(new PerlinColorToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(45));
	}
	@Test
	public void testTokenizeRgbToYCrCb() {
		String expression = "RgbToYCrCb(x)";
		tokens =tokenizer.parseTokens(expression);
		assertEquals(new RgbToYCrCbToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(45));
	}
	
	@Test
	public void testTokenizerYCrCbToRGB() {
		String expression = "YCrCbToRGB(x)";
		tokens =tokenizer.parseTokens(expression);
		assertEquals(new YCrCbToRGBToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(45));
	}
	
}
