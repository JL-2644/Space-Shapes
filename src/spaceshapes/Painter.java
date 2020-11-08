package spaceshapes;

import java.awt.Color;
import java.awt.Image;

/** 
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public interface Painter {
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);

	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the 
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);
	
	/**
	 * Fills the rectangle with a specified color. If no color is specified
	 * the default color is white
	 */
	public void fillRect(int x, int y, int width, int height);
	
	/**
	 * Gets the current color
	 * 
	 */
	public Color getColor();
	
	/**
	 * Sets the current color to a different color
	 * 
	 */
	public void setColor(Color color);
	
	/**
	 * Moves the origin of the bounding box to a specified x, y position
	 * 
	 */
	public void translate(int x, int y);
	
	/**
	 * Draws a text centered in the middle of the Shape's bounding box
	 * 
	 */
	public void drawCenteredText(int x, int y, String text );
	
	public void drawImage(Image img, int x, int y, int width, int height);
	
}
