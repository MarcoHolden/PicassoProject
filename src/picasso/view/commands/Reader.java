package picasso.view.commands;

import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import picasso.model.Pixmap;
import picasso.util.FileCommand;
import picasso.view.Frame;
import picasso.view.commands.Evaluator;

/**
 * Open the chosen image file and display in the Pixmap target.
 * 
 * @author Robert C Duvall
 */
public class Reader extends FileCommand<Pixmap> {

	/**
	 * Creates a Reader object, which prompts users for image files to open
	 */
	public Reader() {
		super(JFileChooser.OPEN_DIALOG);
	}

	/**
	 * @param dialogType
	 */

	/**
	 * Displays the image or expression file on the given target. 
	 */
	public void execute(Pixmap target) {
		String fileName = getFileName();
		if (fileName != null) {
			String currLine;
			int i = fileName.length();
			if (fileName.subSequence(i-3, i).equals("exp")) {
				try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
					String expression;
					while ((currLine = br.readLine()) != null) {
						expression = currLine;
						Frame.setExpressionText(expression);
						new Evaluator().execute(target);
					}
				} catch (IOException e) {
					System.out.println(e);
					//Come back to this later
				}
			} else {
				target.read(fileName);
			}
			
		}
	}
}
