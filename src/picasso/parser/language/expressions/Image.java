package picasso.parser.language.expressions;

import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import javax.imageio.ImageIO;

import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;
import picasso.model.Pixmap;


/**
 * Represents the Image object in the Picasso language.
 * 
 * @author Wonjun Jo
 * 
 */
public class Image extends ExpressionTreeNode {
	
	// In a sense, Image is a type of Pixmap
	String filename;
	BufferedImage myImage;
	Dimension mySize;
	File directory;
	
	/**
	 * Create a image ETN that takes as a parameter the filename
	 * 
	 * @param filename the name of the file
	 */
	public Image(String filename) {
		this.filename = filename;
		this.directory = new File("images/"); 
		// One of the method overloads for File is taking a parent file object as arg1 and String of child file as arg2
		try {
			this.myImage = ImageIO.read(new File(directory, filename));
			this.mySize = new Dimension(myImage.getWidth(), myImage.getHeight());
		} catch (IOException e) {
			throw new ParseException("Not a valid image!");
		}
	}
	
	/**
	 * Determine if the given (x,y) coordinate is within the bounds of this image.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if the given (x,y) coordinate is within the bounds of this
	 *         image.
	 */
	public boolean isInBounds(int x, int y) {
		return (0 <= x && x < mySize.width) && (0 <= y && y < mySize.height);
	}
	
	/**
	 * Returns the color of the pixel at the given (x,y) coordinate if the
	 * coordinate is within the bounds of the image; otherwise returns the default
	 * color
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the color of the pixel at the given (x,y) coordinate if the
	 *         coordinate is within the bounds of the image; otherwise returns the
	 *         default color
	 */
	public Color getColor(int x, int y) {
		if (isInBounds(x, y)) {
			return new Color(myImage.getRGB(x, y));
		} else {
			
			return Color.BLUE;
		}
			
	}
	
	/**
	 * Returns the RGBColor of an image at a given x,y coordinate
	 * 
	 * @param x the x coordinate of the image
	 * @param y the y coordinate of the image
	 * @return the color of the image at given x,y coordinates
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		int xPixel = coorToPixel(x, this.mySize.width);
		int yPixel = coorToPixel(y, this.mySize.height);
		
		Color origColor = this.getColor(xPixel,yPixel);
		return new RGBColor(origColor);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Image)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		Image img = (Image) o;

		// check if their parameters are equal
		if (this.filename.equals(img.filename)) {
			return true;
		} else {
			return false;
		}
		
		//return true;
	}
	
	/**
	 * Converts a coordinate [-1, 1] to int pixels from 0 to length
	 * length can either by image width or image height
	 * 
	 * @param val 
	 * @param length the width or height of the image
	 * @return an int pixel corresponding proportionally to the coordinates
	 */
	private static int coorToPixel(double val, int length) {
		// if we add 1 to val, the range of val will be [0,2].
		// Dividing that range by 2 will return the proportional length of val from 0 to 2 as a percentage.
		// Multiplying that proportion by the pixel length (either width or height)
		// will return the pixel corresponding to the val
		int result = (int) (((val+1)/2)*length);
		
		// given the java syntax of .getRGB(), we cannot have output equal 
		// to the length or height of the image. 
		// Thus if we val = 1, after we convert to pixels, which equals length or height
		// we just subtract 1 pixel. Doesn't make a meaningful difference
		if (result == length) {
			result = result - 1;
		}
		return result;
	}
	
	public Dimension getSize() {
		return mySize;
	}
	
	

}
