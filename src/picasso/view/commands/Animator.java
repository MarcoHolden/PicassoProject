package picasso.view.commands;

import javax.swing.JComponent;

import java.util.Random;

import picasso.model.Pixmap;
import picasso.parser.language.expressions.RGBColor;
import picasso.util.Command;


/**
 * Animates an expression
 * 
 * @author Wonjun Jo
 */
public class Animator implements Command<Pixmap> {
	private static final int DELAY = 400; // in milliseconds

	private boolean isDone;
	private Evaluator evaluator;
	private String add;
	private String step;// = "+[-0.05,0.05,0.025]";
	private Random rand = new Random();
	private int increment = 60; // number of times we animate the frame, also used to divide the RGB amount change per frame
	
	/**
	 * Create a command that runs the given command and updates the given view
	 * over time.
	 */
	public Animator(Evaluator evaluator) {
		this.isDone = true;
		this.evaluator = evaluator;
	}

	/**
	 * Run the command on the target or cancel it if is already running.
	 */
	public void execute(Pixmap target) {
		evaluator.execute(target);
		try {
			Thread.sleep(500);
		} catch (InterruptedException E) {
			E.printStackTrace();
		}
		add = "";
		step = randColorString(increment);
		for (int i = 0; i < increment; i++) {
			if (i%10 == 1 ) {
				// halfway through the animation, change the gradient
				step = randColorString(increment);
			}
			evaluator.execute(target, add);
			add = add + step;
			try {
				Thread.sleep(100);
			} catch (InterruptedException E) {
				E.printStackTrace();
			}
		}
		System.out.println("Done animating");
	}
	
	/**
	 * Generates a string representation of a randomly generated color
	 * 
	 * @return result a random string representation of color to be added with each animation frame
	 */
	private String randColorString(int increment) {
		
		StringBuilder result = new StringBuilder();
		
		int rSign = randomSign();
		int gSign = randomSign();
		int bSign = randomSign();
		
		double randR = rSign*(Math.round(rand.nextDouble()*100.0)/100.0)/(increment/5);
        double randG = gSign*(Math.round(rand.nextDouble()*100.0)/100.0)/(increment/5);
        double randB = bSign*(Math.round(rand.nextDouble()*100.0)/100.0)/(increment/5);
        
        result.append("+[");
        result.append(randR);
        result.append(",");
        result.append(randG);
        result.append(",");
        result.append(randB);
        result.append("]");
        
		return result.toString();
		
	}
	
	/**
	 * Method either returns 1 or -1
	 * 
	 * This method is used to determine if we add step or subtract step for each animation frame
	 */
	private int randomSign() {
		int randInt = rand.nextInt(2);
		if (randInt == 0) {
			return -1;
		}
		return randInt;
		
	}
	
}
