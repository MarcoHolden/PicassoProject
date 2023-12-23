package picasso.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import picasso.model.Pixmap;
import picasso.view.commands.Evaluator;

/**
 * The canvas on which to present the image.
 * 
 * @author Robert Duvall (rcd@cs.duke.edu)
 *
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel {

	/** keep track of the frame that contains this container */
	private JFrame myContainer;
	static JFrame coordFrame;
	static JLabel coordLabel;
	static JLabel coordLabel2;

	/** the pixel map of the displayed image */
	private Pixmap myPixmap;

	/**
	 * 
	 * @param container
	 */
	public Canvas(JFrame container) {
		this(container, null);
	}

	/**
	 * 
	 * @param container
	 * @param pixName
	 */
	public Canvas(JFrame container, String pixName) {
		setBorder(BorderFactory.createLoweredBevelBorder());
		myContainer = container;
		myPixmap = new Pixmap(pixName);
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				myPixmap.setSize(getSize());
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (coordFrame != null) {
					coordFrame.dispose();
				}
				int x_coordinate = e.getX();
				int y_coordinate = e.getY();
				double Y_CoordScale = imageToDomainScale(y_coordinate, myPixmap.getSize().height);
				double X_CoordScale = imageToDomainScale(x_coordinate, myPixmap.getSize().width);
				
				Color color = myPixmap.getColor(x_coordinate,  y_coordinate);
				showCoordinates(X_CoordScale, Y_CoordScale, color);
				}
		});
		refresh();
	}

	public Pixmap getPixmap() {
		return myPixmap;
	}

	public void refresh() {
		if (!myPixmap.getSize().equals(getSize())) {
			setSize(myPixmap.getSize());
			myContainer.setTitle(myPixmap.getName() + " Invincibles");
			myContainer.pack();
		}
		repaint();
	}

	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		myPixmap.paint(pen);
	}

	public void setSize(Dimension size) {
		setPreferredSize(size);
		setMinimumSize(size);
		super.setSize(size);
	}
	
	protected double imageToDomainScale(int value, int bounds) {
		double range = Evaluator.DOMAIN_MAX - Evaluator.DOMAIN_MIN;
		return ((double) value / bounds) * range + Evaluator.DOMAIN_MIN;
	}
	
	public void showCoordinates(double x_coor, double y_coor, Color color) {
		coordFrame = new JFrame("Coordinates");
		coordLabel = new JLabel();
		coordLabel2 = new JLabel();
		coordLabel.setHorizontalAlignment(JLabel.CENTER);
		coordLabel.setVerticalAlignment(JLabel.TOP);
		coordLabel2.setHorizontalAlignment(JLabel.CENTER);
		coordLabel2.setVerticalAlignment(JLabel.BOTTOM);
		coordLabel.setSize(500, 75);
		coordLabel2.setSize(500, 75);
		
		String x_coordStr = String.format("%.3f", x_coor);
		String y_coordStr = String.format("%.3f", y_coor);
		
		coordLabel.setText("Coordinate (X,Y): (" + x_coordStr + ", " + y_coordStr + ")");
		//Do math
		double red = (float) (color.getRed()*2)/255;
		String redStr = String.format("%.3f", red-1);
		
		double green = (float) (color.getGreen()*2)/255;
		String greenStr = String.format("%.3f", green-1);
		
		double blue = (float) (color.getBlue()*2)/255;
		String blueStr = String.format("%.3f", blue-1);
		
		coordLabel2.setText("The color is (r,g,b): (" + redStr + ", " + greenStr + ", " + blueStr + ")");
		coordFrame.add(coordLabel);
		coordFrame.add(coordLabel2);
		coordFrame.setSize(500, 150);  
		coordFrame.setLayout(null);  
		coordFrame.setVisible(true);
	}
}