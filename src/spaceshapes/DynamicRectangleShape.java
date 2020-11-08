package spaceshapes;

import java.awt.Color;

/**
 * Implements a dynamic shape
 * 
 * @author JiaQi Li 
 * 
 */

public class DynamicRectangleShape extends Shape{
	
	private Color _color = Color.white;
	private boolean _fill;
	/**
	 * Default constructor that creates a filled rectangle shape instance whose instance
	 * variables are set to default values.
	 */
	public DynamicRectangleShape() {
		super();
	}
	
	public DynamicRectangleShape(Color color) {
		super();
		_color = color;
	}
	/**
	 * Creates a filled rectangle shape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, Color color) {
		super(x,y,deltaX,deltaY);
		_color = color;
	}
	
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}
	
	/**
	 * Creates a RectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
		super(x,y,deltaX,deltaY,width,height);
		_color = color;
	}
	
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text,Color color) {
		super(x,y,deltaX,deltaY,width,height,text, color);
		_color = color;
	}
	
	/**
	 * Paints the Rectangle Shape object using the supplied Painter object.
	 */
	
	public void paintShape(Painter painter) {
		painter.drawRect(_x, _y, _width, _height);
		// If the shape hits the left or right wall. Fill is set to true
		// In cases of corners, shape is always filled
		if(_x == 0 || _x == Shape._boundWidth - _width) {
			_fill = true;
		}
		else if(_y == 0 || _y == Shape._boundHeight - _height) {
			_fill = false;
		}
		// when true, fill in the shape with the specified color, default is white
		if(_fill) {
			painter.setColor(_color);
			painter.fillRect(_x,_y,_width,_height);
		}
	}
}
