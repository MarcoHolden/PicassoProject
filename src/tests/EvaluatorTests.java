/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.model.ImprovedNoise;
import picasso.parser.WordAnalyzer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;

/**
 * Tests of the evaluation of expression trees
 * 
 * @author Sara Sprenkle
 * 
 */
public class EvaluatorTests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testConstantEvaluation() {
		ExpressionTreeNode e = new RGBColor(1, -1, 1);
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(1, -1, 1), e.evaluate(i, i));
		}
	}

	@Test
	public void testXEvaluation() {
		X x = new X();
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), x.evaluate(i, i));
		}
	}

	@Test
	public void testFloorEvaluation() {
		Floor myTree = new Floor(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double floorOfTestVal = Math.floor(testVal);
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}

	@Test
	public void testCeilvaluation() {
		Ceil myTree = new Ceil(new X());
   
    // some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-.7, -1));
    
		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}
		
		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double ceilOfTestVal = Math.ceil(testVal);
			assertEquals(new RGBColor(ceilOfTestVal, ceilOfTestVal, ceilOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(ceilOfTestVal, ceilOfTestVal, ceilOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}

  @Test
	public void testCosEvaluation() {
		Cos myTree = new Cos(new X());
		
		//Some basic tests
		RGBColor black = new RGBColor(-1, -1, -1);
		RGBColor gray = new RGBColor(0, 0, 0);
		RGBColor white = new RGBColor(1, 1, 1);
		
		RGBColor[] colors = {black, gray, white};
		
		RGBColor cosBlack = myTree.evaluate(Math.PI, Math.PI);
		RGBColor cosGray = myTree.evaluate(Math.PI/2, Math.PI/2);
		RGBColor cosWhite = myTree.evaluate(0, 0);
		
		RGBColor[] cosCols = {cosBlack, cosGray, cosWhite};
		
		for (int i = 0; i < 3; i++) {
			assertEquals(colors[i].getRed(), cosCols[i].getRed(), 0.0001);
			assertEquals(colors[i].getGreen(), cosCols[i].getGreen(), 0.0001);
			assertEquals(colors[i].getBlue(), cosCols[i].getBlue(), 0.0001);
		}
  }
    
  @Test
	public void testClampEvaluation() {
		Clamp myTree = new Clamp(new X());
		// some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1.4, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-1.999, -1));
		assertEquals(new RGBColor(-0.7, -0.7, -0.7), myTree.evaluate(-.7, -1));
		assertEquals(new RGBColor(0.7, 0.7, 0.7), myTree.evaluate(.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -1.7, -1, -.00001, 0, .000001, 1, 1.5 };

		for (double testVal : tests) {
			double clampOfTestVal = testVal;
			if (clampOfTestVal > 1) {
				clampOfTestVal = 1;
			} else if (clampOfTestVal < -1) {
				clampOfTestVal = -1;
			}
			assertEquals(new RGBColor(clampOfTestVal, clampOfTestVal, clampOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(clampOfTestVal, clampOfTestVal, clampOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
	
	@Test
	public void testPlusEvaluation() {
		Addition myTree = new Addition(new X(), new Y());

		// some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, 0));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(-0.7, -0.7, -0.7), myTree.evaluate(-0.6, -0.1));
		assertEquals(new RGBColor(0.7, 0.7, 0.7), myTree.evaluate(0.6, 0.1));
		assertEquals(new RGBColor(-0.5, -0.5, -0.5), myTree.evaluate(-0.6, 0.1));
		assertEquals(new RGBColor(0.5, 0.5, 0.5), myTree.evaluate(0.6, -0.1));
		
		// test the ints; 
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(2*i, 2*i, 2*i), myTree.evaluate(i, i));

		}

		double[] tests = { -1.7, -1, -.00001, 0, .000001, 1, 1.5 };
		
		for (double testVal : tests) {
			double plusOfTestVal = testVal;
			
			assertEquals(new RGBColor(plusOfTestVal, plusOfTestVal, plusOfTestVal), myTree.evaluate(testVal, 0));
			assertEquals(new RGBColor(2*plusOfTestVal, 2*plusOfTestVal, 2*plusOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
		
	}
  
	@Test
	public void testAbsEvaluation() {
		Abs myTree = new Abs(new X());

		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0.5)); 
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -0.2));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(-1, 0.3));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, -0.7));

		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.abs(i), Math.abs(i), Math.abs(i)), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(Math.abs(i), Math.abs(i), Math.abs(i)), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double absOfTestVal = Math.abs(testVal);
			assertEquals(new RGBColor(absOfTestVal, absOfTestVal, absOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(absOfTestVal, absOfTestVal, absOfTestVal), myTree.evaluate(testVal, testVal));
		}
	}

  
  @Test
	public void testSineEvaluation() {
	  Sine myTree = new Sine(new X());
		
		//Some basic tests
		RGBColor black = new RGBColor(-1, -1, -1);
		RGBColor gray = new RGBColor(0, 0, 0);
		RGBColor white = new RGBColor(1, 1, 1);
		
		RGBColor[] colors = {black, gray, white};
		
		RGBColor sinBlack = myTree.evaluate(Math.PI/-2, Math.PI/-2);
		RGBColor sinGray = myTree.evaluate(0, 0);
		RGBColor sinWhite = myTree.evaluate(Math.PI/2, Math.PI/2);
		
		RGBColor[] sinCols = {sinBlack, sinGray, sinWhite};
		
		for (int i = 0; i < 3; i++) {
			assertEquals(colors[i].getRed(), sinCols[i].getRed(), 0.0001);
			assertEquals(colors[i].getGreen(), sinCols[i].getGreen(), 0.0001);
			assertEquals(colors[i].getBlue(), sinCols[i].getBlue(), 0.0001);
		}
	}
  
  	@Test
	public void testTanEvaluation() {
	  Tan myTree = new Tan(new X());
		
		//Some basic tests
		RGBColor black = new RGBColor(-1, -1, -1);
		RGBColor gray = new RGBColor(0, 0, 0);
		RGBColor white = new RGBColor(1, 1, 1);
		
		RGBColor[] colors = {black, gray, white};
		
		RGBColor sinBlack = myTree.evaluate(3*Math.PI/4, 3*Math.PI/4);
		RGBColor sinGray = myTree.evaluate(0, 0);
		RGBColor sinWhite = myTree.evaluate(Math.PI/4, Math.PI/4);
		
		RGBColor[] sinCols = {sinBlack, sinGray, sinWhite};
		
		for (int i = 0; i < 3; i++) {
			assertEquals(colors[i].getRed(), sinCols[i].getRed(), 0.0001);
			assertEquals(colors[i].getGreen(), sinCols[i].getGreen(), 0.0001);
			assertEquals(colors[i].getBlue(), sinCols[i].getBlue(), 0.0001);
		}
	}
  
  	@Test
	public void testAtanEvaluation() {
	  Atan myTree = new Atan(new X());
		
		//Some basic tests
		RGBColor black = new RGBColor(-1, -1, -1);
		RGBColor gray = new RGBColor(0, 0, 0);
		RGBColor white = new RGBColor(1, 1, 1);
		
		RGBColor[] colors = {black, gray, white};
		
		RGBColor sinBlack = myTree.evaluate(-1.5574, -1.5574);
		RGBColor sinGray = myTree.evaluate(0, 0);
		RGBColor sinWhite = myTree.evaluate(1.5574, 1.5574);
		
		RGBColor[] sinCols = {sinBlack, sinGray, sinWhite};
		
		for (int i = 0; i < 3; i++) {
			assertEquals(colors[i].getRed(), sinCols[i].getRed(), 0.0001);
			assertEquals(colors[i].getGreen(), sinCols[i].getGreen(), 0.0001);
			assertEquals(colors[i].getBlue(), sinCols[i].getBlue(), 0.0001);
		}
	}
  
  	@Test
	public void testTimesEvaluation() {
		Multiplication myTree = new Multiplication(new X(), new Y());

		// some straightforward tests
		assertEquals(new RGBColor(0.05, 0.05, 0.05), myTree.evaluate(-0.5, -0.1));
		assertEquals(new RGBColor(0.81, 0.81, 0.81), myTree.evaluate(0.9, 0.9));
		assertEquals(new RGBColor(-0.18, -0.18, -0.18), myTree.evaluate(-0.6, 0.3));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0.3));
		
		// test the ints; 
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, 1));

	}
  
  @Test
  public void testDivideEvaluation() {
	  Division myTree = new Division(new X(), new Y());
	  
	  // some straightforward tests
	  assertEquals(new RGBColor(0.05, 0.05, 0.05), myTree.evaluate(0.5, 10));
	  assertEquals(new RGBColor(0.9, 0.9, 0.9), myTree.evaluate(0.81, 0.9));
	  assertEquals(new RGBColor(-0.6, -0.6, -0.6), myTree.evaluate(-0.18, 0.3));
	  assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0.9, 0));
	  
	  //tests the ints
	  assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, 0));
	  assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, 0));
	  assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
	  assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(1, -1));
	  assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-1, 1));
	  assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, 1));
  }

  @Test
	public void testAssignmentEvaluation() {
		Addition add = new Addition(new X(), new Y());
		Multiplication times =  new Multiplication(new X(), new Y());
		Floor floor = new Floor(new X());
		Ceil ceil = new Ceil(new X());
		Cos cos = new Cos(new X());
		Sine sin = new Sine(new X());
		Clamp clamp = new Clamp(new X());
		Abs abs = new Abs(new X());
		
		Assignment addTree = new Assignment(new Variable ("a"), add);
		Assignment timesTree = new Assignment(new Variable ("b"), times);
		Assignment floorTree = new Assignment(new Variable ("c"), floor);
		Assignment ceilTree = new Assignment(new Variable ("d"), ceil);
		Assignment cosTree = new Assignment(new Variable ("e"), cos);
		Assignment sinTree = new Assignment(new Variable ("f"), sin);
		Assignment clampTree = new Assignment(new Variable ("g"), clamp);
		Assignment absTree = new Assignment(new Variable ("h"), abs);
		
		assertEquals(add.evaluate(1, 0), addTree.evaluate(1, 0));
		assertEquals(times.evaluate(1, 0.5), timesTree.evaluate(1, 0.5));
		assertEquals(floor.evaluate(.4, 0.4), floorTree.evaluate(.4, 0.4));
		assertEquals(ceil.evaluate(0.5, 0.5), ceilTree.evaluate(0.5, 0.5));
		assertEquals(cos.evaluate(1, 0.5), cosTree.evaluate(1, 0.5));
		assertEquals(sin.evaluate(.4, -1), sinTree.evaluate(.4, -1));
		assertEquals(clamp.evaluate(0.5, 0.5), clampTree.evaluate(0.5, 0.5));
		assertEquals(abs.evaluate(-1, -1), absTree.evaluate(-1, -1));
		
		
  }
  
  	@Test
	public void testImageWrapEvaluation() {
  		ImageWrap myTree = new ImageWrap(new Image("vortex.jpg"), new Addition(new X(), new X()), new Y());
  		Image testImage = new Image("vortex.jpg");
  		
  		RGBColor testColor = new RGBColor(testImage.getColor(0, 0)); 
  		assertEquals(testColor, myTree.evaluate(-1,-1));
  		
  		testColor = new RGBColor(testImage.getColor(testImage.getSize().width/2, 0));
  		assertEquals(testColor, myTree.evaluate(1,-1));
  		
  		testColor = new RGBColor(testImage.getColor(testImage.getSize().width/2, testImage.getSize().height-1));
  		assertEquals(testColor, myTree.evaluate(-1,1));
  		
  		testColor = new RGBColor(testImage.getColor(testImage.getSize().width/2, testImage.getSize().height-1));
  		assertEquals(testColor, myTree.evaluate(1,1));
		
	}
  	
  	@Test
	public void testImageClipEvaluation() {
  		ImageClip myTree = new ImageClip(new Image("vortex.jpg"), new Addition(new X(), new X()), new Y());
  		Image testImage = new Image("vortex.jpg");
  		
		RGBColor testColor = new RGBColor(testImage.getColor(0, 0)); 
  		assertEquals(testColor, myTree.evaluate(-1,-1));
  		
  		testColor = new RGBColor(testImage.getColor(testImage.getSize().width-1, 0));
  		assertEquals(testColor, myTree.evaluate(1,-1));
  		
  		testColor = new RGBColor(testImage.getColor(0, testImage.getSize().height-1));
  		assertEquals(testColor, myTree.evaluate(-1,1));
  		
  		testColor = new RGBColor(testImage.getColor(testImage.getSize().width-1, testImage.getSize().height-1));
  		assertEquals(testColor, myTree.evaluate(1,1));
		
	}
  
  @Test
  public void testMinusEvaluation() {
		Subtraction myTree = new Subtraction(new X(), new Y());

		// some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, 0));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(-0.5, -0.5, -0.5), myTree.evaluate(-0.6, -0.1));
		assertEquals(new RGBColor(0.5, 0.5, 0.5), myTree.evaluate(0.6, 0.1));
		assertEquals(new RGBColor(-0.7, -0.7, -0.7), myTree.evaluate(-0.6, 0.1));
		assertEquals(new RGBColor(0.7, 0.7, 0.7), myTree.evaluate(0.6, -0.1));
		
		// test the ints; 
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(i, i));
			assertEquals(new RGBColor(2*i, 2*i, 2*i), myTree.evaluate(i, -i));

		}

		double[] tests = { -1.7, -1, -.00001, 0, .000001, 1, 1.5 };
		
		for (double testVal : tests) {
			double minusOfTestVal = testVal;
			
			assertEquals(new RGBColor(minusOfTestVal, minusOfTestVal, minusOfTestVal), myTree.evaluate(testVal, 0));
			assertEquals(new RGBColor(2*minusOfTestVal, 2*minusOfTestVal, 2*minusOfTestVal),myTree.evaluate(testVal, -testVal));
		}
  	
  	}
  	
  	@Test
	public void testModEvaluation() {
		Modulo myTree = new Modulo(new X(), new Y());

		double[] tests = { -1.7, -1, -.00001, -0.5, 0, .000001, 0.5, 1, 1.5 };
		
		for (double testVal : tests) {
			double modOfTestVal1 = testVal % 0.1;
			double modOfTestVal2 = testVal % -0.8;
			
			assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(testVal, testVal));
			assertEquals(new RGBColor(modOfTestVal1, modOfTestVal1, modOfTestVal1),myTree.evaluate(testVal, 0.1));
			assertEquals(new RGBColor(modOfTestVal2, modOfTestVal2, modOfTestVal2),myTree.evaluate(testVal, -0.8));
			assertEquals(new RGBColor(testVal, testVal, testVal),myTree.evaluate(testVal, 0));
		
			
		}
		
  	}
  	
  	@Test
	public void testExponentiateEvaluation() {
		Exponentiation myTree = new Exponentiation(new X(), new Y());
		
		double[] tests = {-1, -0.5, -.27, .31, 0.5, 1};

		for (double testVal : tests) {
			double expOfTestVal1 = Math.pow(testVal, -1);
			double expOfTestVal2 = Math.pow(testVal, -0.5);
			double expOfTestVal3 = Math.pow(testVal, 0);
			double expOfTestVal4 = Math.pow(testVal, 1);
			
			assertEquals(new RGBColor(expOfTestVal1, expOfTestVal1, expOfTestVal1), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(expOfTestVal2, expOfTestVal2, expOfTestVal2).getRed(), myTree.evaluate(testVal, -0.5).getRed(), 0.01);
			assertEquals(new RGBColor(expOfTestVal3, expOfTestVal3, expOfTestVal3), myTree.evaluate(testVal, 0));
			assertEquals(new RGBColor(expOfTestVal4, expOfTestVal4, expOfTestVal4), myTree.evaluate(testVal, 1));
		
			
			
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -0.4));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0.5));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 1));
		}
  	

	}
  	
  	@Test
  	public void testNotEvaluation() {
  		Not myTree = new Not(new X());
  		
  		double[] tests = { -1.7, -1, -.00001, -0.5, 0, .000001, 0.5, 1, 1.5 };
		
		for (double testVal : tests) {
			double notOfTestVal1 = -testVal;
			assertEquals(new RGBColor(notOfTestVal1, notOfTestVal1, notOfTestVal1),myTree.evaluate(testVal, 0));
		}
			
  	}
  	
  	@Test
  	public void testExpEvaluation() {
  		Exp myTree = new Exp(new X());
  		
  		double[] tests = { -1.7, -1, -.00001, -0.5, 0, .000001, 0.5, 1, 1.5 };
		
		for (double testVal : tests) {
			double expOfTestVal1 = Math.exp(testVal);
			assertEquals(new RGBColor(expOfTestVal1, expOfTestVal1, expOfTestVal1),myTree.evaluate(testVal, 0));
		}
  	
  	}
  	
  	@Test
  	public void testLogEvaluation() {
  		Log myTree = new Log(new X());
  		
  		double[] tests = { -1.7, -1, -.00001, -0.5, .000001, 0.5, 1, 1.5 };
		
		for (double testVal : tests) {
			double LogOfTestVal = Math.log(Math.abs(testVal));
			assertEquals(new RGBColor(LogOfTestVal, LogOfTestVal, LogOfTestVal),myTree.evaluate(testVal, 0));
		}
  	
  	}
  	
  	@Test
  	public void testWrapEvaluation() {
  		Wrap myTree = new Wrap(new X());
  		
  		double[] tests = { -1.7, -1, -.00001, -0.5, .000001, 0.5, 1, 1.5 };
		
		for (double testVal : tests) {
			double WrapOfTestVal = (((testVal+3) % 2) - 1);
			assertEquals(new RGBColor(WrapOfTestVal, WrapOfTestVal, WrapOfTestVal),myTree.evaluate(testVal, 0));
		}
  	
  	}
  	@Test
  	public void testPerlinBWEvaluation() {
  		PerlinBW myTree = new PerlinBW(new X(), new Y());
  		
  		double[] tests = { -1.7, -1, -.00001, -0.5, .000001, 0.5, 1, 1.5 };
		
		for (double testVal : tests) {
			double PBWOfTestVal1 = testVal;
			double PBWofTestVal2 = testVal;
			double grey = ImprovedNoise.noise(PBWOfTestVal1 + PBWofTestVal2, 
					PBWOfTestVal1 + PBWofTestVal2, PBWOfTestVal1 + PBWofTestVal2);
			assertEquals(new RGBColor(grey, grey, grey),
					myTree.evaluate(testVal, testVal));
		}
  	
  	}
  	@Test
  	public void testPerlinColorEvaluation() {
  		PerlinColor myTree = new PerlinColor(new X(), new Y());
  		
  		double[] tests = { -1.7, -1, -.00001, -0.5, .000001, 0.5, 1, 1.5 };
		
		for (double testVal : tests) {
			double PCOfTestVal1 = testVal;
			double PCofTestVal2 = testVal;
			double red = ImprovedNoise.noise(PCOfTestVal1 + 0.3, PCofTestVal2 + 0.3, 0);
			double green = ImprovedNoise.noise(PCOfTestVal1 - 0.8 , PCofTestVal2 - 0.8, 0);
			double blue = ImprovedNoise.noise(PCOfTestVal1 + 0.1, PCofTestVal2 + 0.1, 0);
			assertEquals(new RGBColor(red, green, blue), myTree.evaluate(testVal, testVal));
		}
  	
  	}
  	@Test
  	public void testRgbToYCrCbEvaluation() {
  		RgbToYCrCb myTree = new RgbToYCrCb(new X());
  		
  		double[] tests = { -1.7, -1, -.00001, -0.5, .000001, 0.5, 1, 1.5 };
		
		for (double testVal : tests) {
			double red = testVal * 0.2989 + testVal * 0.5866 + testVal * 0.114;
			double green = testVal * -0.1687 + testVal * -0.3312 + testVal * 0.5;
			double blue = testVal * 0.5 + testVal * -0.4183 + testVal * -0.0816;
			assertEquals(new RGBColor(red, green, blue), myTree.evaluate(testVal, testVal));
		}	
  	}
  	@Test
  	public void testYCrCbToRGBEvaluation() {
  		YCrCbToRGB myTree = new YCrCbToRGB(new X());
  		
  		double[] tests = { -1.7, -1, -.00001, -0.5, .000001, 0.5, 1, 1.5 };
		
		for (double testVal : tests) {
			double red = testVal + testVal * 1.4022;
			double green = testVal + testVal * -0.3456 + testVal * -0.7145;
			double blue = testVal + testVal * 1.7710;
			assertEquals(new RGBColor(red, green, blue), myTree.evaluate(testVal, testVal));
		}	
  	}
}


		
	
  	
  	
  	
  	
  
  
  

