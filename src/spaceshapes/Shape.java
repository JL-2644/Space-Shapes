package spaceshapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements
 * a common movement algorithm. Shape subclasses must override method paint()
 * to handle shape-specific painting.
 * 
 * 1. Uses a general hierarchy design pattern. Shape has subclasses which have an inherit 
 * relationship. Carrier Shape also inherits Shape but has an aggregation relationship
 * with Shape.
 * 
 * @author JiaQi 
 * 
 */
public abstract class Shape {
	// === Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;
	
	protected static final int DEFAULT_Y_POS = 0;
	
	protected static final int DEFAULT_DELTA_X = 5;
	
	protected static final int DEFAULT_DELTA_Y = 5;
	
	protected static final int DEFAULT_HEIGHT = 35;

	protected static final int DEFAULT_WIDTH = 25;
	
	protected static int _boundWidth;
	
	protected static int _boundHeight;
	// ===

	// === Instance variables, accessible by subclasses.
	protected int _x;

	protected int _y;

	protected int _deltaX;

	protected int _deltaY;

	protected int _width;

	protected int _height;
	
	protected String _text;
	
	protected Color _color;
	
	protected CarrierShape _parent;
	
	private static List<Shape> revShapePath = new ArrayList<Shape>();
	
	private static List<Shape> shapePath = new ArrayList<Shape>();
	
	// ===

	/**
	 * Creates a Shape object with default values for instance variables.
	 */
	public Shape() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Shape object with a specified x and y position.
	 */
	public Shape(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Shape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public Shape(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
	}
	
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		_text = text;
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
	}
	
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height, String text,Color color) {
		_text = text;
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
		_color = color;
	}
	
	
	/**
	 * Moves this Shape object within the specified bounds. On hitting a 
	 * boundary the Shape instance bounces off and back into the two- 
	 * dimensional world. 
	 * @param width - width of two-dimensional world.
	 * @param height - height of two-dimensional world.
	 */
	public void move(int width, int height) {
		
		// Gets the width and height of the bounding box of graphics
		_boundWidth = width;
		_boundHeight = height;
		
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
		}

		_x = nextX;
		_y = nextY;
	}

	/**
	 * Method to be implemented by concrete subclasses to handle subclass
	 * specific painting.
	 * @param painter the Painter object used for drawing.
	 */
	protected abstract void paintShape(Painter painter);
	
	/**
	 * 2. Template method design pattern used, paintShape is overridden for the various shapes. 
	 * But each shape when supplied a text, draws the text centered.
	 * @param painter
	 * @param text
	 */
	public final void paint(Painter painter) {
		paintText(painter);
		paintShape(painter);
	}
	
	/**
	 * Setter method to set the text
	 * @param text
	 */
	public void setText(String text) {
		_text = text;
	}

	/**
	 * draws the text centered, start at the middle of the shape's bounding box
	 * Further adjustments made in graphics painter
	 * @param painter
	 */
	public void paintText(Painter painter) {
		if(_text != null) {
			painter.drawCenteredText(_width/2+_x, _height/2+_y, _text);
		}
	}

	/**
	 * Returns this Shape object's x position.
	 */
	public int x() {
		return _x;
	}
	
	/**
	 * Returns this Shape object's y position.
	 */
	public int y() {
		return _y;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaX() {
		return _deltaX;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaY() {
		return _deltaY;
	}
	
	/**
	 * Returns this Shape's width.
	 */
	public int width() {
		return _width;
	}
	
	/**
	 * Returns this Shape's height.
	 */
	public int height() {
		return _height;
	}
	
	public String text() {
		return _text;
	}
	
	/**
	 * Returns a String whose value is the fully qualified name of this class 
	 * of object. E.g., when called on a RectangleShape instance, this method 
	 * will return "spaceshapes.RectangleShape".
	 */
	public String toString() {
		return getClass().getName();
	}
	
	/**
	 * Returns the CarrierShape that contains the Shape that the method parent is
	 * called on.
	 */
	public CarrierShape parent() {
		return _parent;
	}
	
	/**
	 * Returns an ordered list of Shape objects 
	 * 
	 */
	public List<Shape> path() {
		shapePath.clear();
		if(_parent == null) { //Only root Shapes' parent is null
			shapePath.add(this);
			return shapePath;
		}
		else {
			revShapePath.add(this); // Adds the shape
			_parent.path(); // Recursively call 
		}

		if(shapePath.size() == 1) { // Resort only once
			for(int i = revShapePath.size()-1; i > -1; i--) {
				shapePath.add(revShapePath.get(i));
			}
			revShapePath.clear();
		}
		return shapePath;		
	}
}
