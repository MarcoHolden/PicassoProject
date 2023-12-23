package picasso.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.*;

import picasso.model.Pixmap;
import picasso.util.ThreadedCommand;
import picasso.view.commands.*;

import picasso.parser.*;

/**
 * Main container for the Picasso application
 *
 * @author Robert Duvall (rcd@cs.duke.edu)
 * @author Zachary Moore
 * 
 */
@SuppressWarnings("serial")
public class Frame extends JFrame implements KeyListener {

	// Resizable array for storing entered expressions for later reference
	private static ArrayList<String> expressions = new ArrayList<String>();
	private static int index = 0;

	private static JTextField expressionTextField;
  private Evaluator evaluator = new Evaluator();

	public Frame(Dimension size) {

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create GUI components
		Canvas canvas = new Canvas(this);
		canvas.setSize(size);

		// add commands to test here

		ButtonPanel commands = new ButtonPanel(canvas);
		expressionTextField = new JTextField(40);
		expressionTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        
        Evaluator evaluator = new Evaluator();
				try {
					evaluator.execute(canvas.getPixmap());
					canvas.refresh();
				}
				catch (ParseException | IllegalArgumentException ex) {
					ErrorHandling error = new ErrorHandling(ex.getMessage());
					error.showError();
					canvas.refresh();
				}
        
        evaluator.execute(canvas.getPixmap());
				canvas.refresh();
        
				String text = getExpressionText();

				if (expressions.size() == 0) {
					expressions.add(text);
					index = 0;	
				}
				
				else if (!(expressions.get(expressions.size() - 1).equals(text))) { // Does not add to history if previous entry
					expressions.add(text);
					index++;
				}

			}
		});

		expressionTextField.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					
					if (!(getExpressionText().equals(expressions.get(index)))) {
						setExpressionText(expressions.get(index));
					}
					
					else if (expressions.size() != 0 && index > 0) {
						index--;
						setExpressionText(expressions.get(index)); // Update text bar with expression from history
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					if (expressions.size() != 0 && index < expressions.size() - 1) {
						index++;
						setExpressionText(expressions.get(index)); // Update text bar with expression from history
					}
				}
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}
		});

		commands.add("Open", new Reader());
		commands.add("Evaluate", new ThreadedCommand<Pixmap>(canvas, new Evaluator()));
		commands.add("Animate", new ThreadedCommand<Pixmap>(canvas, new Animator(new Evaluator())));
		commands.add("Save", new Writer());
		commands.add(expressionTextField);

		// add our container to Frame and show it
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(commands, BorderLayout.NORTH);
		pack();
	}

	public static String getExpressionText() {
		return expressionTextField.getText();
	}

	public static void setExpressionText(String s) {
		expressionTextField.setText(s);
	}

	/**
	 * Detects when a key has been pressed
	 * 
	 * @param e key event
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
	}

	/**
	 * Ignore typed keys
	 * 
	 * @param e key event
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Ignore released keys
	 * 
	 * @param e key event
	 */
	public void keyReleased(KeyEvent e) {
	}
}
