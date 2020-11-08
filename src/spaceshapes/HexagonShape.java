package spaceshapes;


/**
 * Class to represent a simple hexagon space-shape.
 * 
 * @author JiaQi Li 
 * 
 */

public class HexagonShape extends Shape {
	/**
	 * Default constructor that creates a HexagonShape instance whose instance
	 * variables are set to default values.
	 */
	public HexagonShape() {
		super();
	}
	
	/**
	 * Creates a HexagonShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public HexagonShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}
	
	/**
	 * Creates a HexagonShape instance with specified values for instance 
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
	public HexagonShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}
	
	public HexagonShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height, text);
	}
	
	/**
	 * Paints this HexagonShape object using the supplied Painter object.
	 * Two types, a normal hexagon if width > 40 or  a 4 sided diamond
	 * if width less than 40.
	 */
	
	protected void paintShape(Painter painter) { 
		// Starts paint from the left most vertex point, moves clockwise
		if (_width < 40) {
			int x1 = _x + _width/2;
			int x2 = _x + _width;
			int y1 = _y + _height/2;
			int y2 = _y + _height;
			painter.drawLine(_x, y1, x1, _y);
			painter.drawLine(x1, _y, x2, y1);
			painter.drawLine(x2, y1, x1, y2);
			painter.drawLine(x1, y2, _x, y1);
		}
		else {
			int x1 = _x + 20;
			int x2 = _x + _width - 20;
			int x3 = _x + _width;
			int y1 = _height/2 + _y;
			int y2 = _y + _height;	
			painter.drawLine(_x, y1, x1, _y);
			painter.drawLine(x1, _y, x2, _y);
			painter.drawLine(x2, _y, x3, y1);
			painter.drawLine(x3, y1, x2, y2);
			painter.drawLine(x2, y2, x1, y2);
			painter.drawLine(x1, y2, _x, y1);
		}
	}
}
