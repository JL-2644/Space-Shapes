package spaceshapes;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a CarrierShape: A shape which can contain other shapes.
 * @author Jia Qi
 *
 */
public class CarrierShape extends Shape {

	public List<Shape> _childShape = new ArrayList<Shape>();

	/**
	 * Creates a CarrierShape object with default values for state
	 * 
	 */
	public CarrierShape() {
		super();
	}
	
	/**
	 * Creates a CarrierShape Object with the specified location values
	 * default values for all other state items
	 */
	public CarrierShape(int x, int y) {
		super(x, y);
	}
	
	/**
	 * CarrierShape Object with the specified location values and the velocity in
	 * x y direction
	 * 
	 */
	public CarrierShape(int x, int y, int deltaX,int deltaY) {
		super(x, y, deltaX, deltaY);
	}
	
	/**
	 * CarrierShape Object with all its state specified
	 * 
	 */
	public CarrierShape(int x, int y, int deltaX,int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height, text);
	}
	
	/**
	 * Moves a CarrierShape Object (and its children) within the bounds
	 * specified by the width and height
	 */
	@Override
	public void move(int width, int height) {
		super.move(width, height);
		for(Shape s: _childShape) {
			s.move(this._width, this._height);
		}
	}
	/**
	 * Paints a CarrierShape object by drawing a rectangle 	around the edge of
	 * its bounding box
	 */
	@Override
	public void paintShape(Painter painter) {
		painter.drawRect(_x,_y, _width, _height);
		painter.translate(_x, _y);
		for(Shape s: _childShape) {
			s.paint(painter);
		}
		painter.translate(-_x, -_y);
	}
	
	/**
	 * Adds a shape to the CarrierShape Object
	 * 
	 */
	void add(Shape shape) throws IllegalArgumentException {
		if(shape._height + shape._y > _height || shape._width + shape._x > _width) {
			throw new IllegalArgumentException();
		}
		else if(shape.path().size() > 1) {
			throw new IllegalArgumentException();
		}
		else {
			_childShape.add(shape);
			shape._parent = this;
		}
	}
	
	/**
	 * Removes a particular SHape from a CarrierShape instance
	 * 
	 */
	void remove(Shape shape) {
		_childShape.remove(shape);
		shape._parent = null;
	}
	
	/**
	 * Returns the Shape at a specified position within a CarrierShape
	 * 
	 */
	public Shape shapeAt(int index) throws IndexOutOfBoundsException {
		return _childShape.get(index);
	}
	
	/**
	 * Returns the number of children contained inside the CarrierShape Object
	 * 
	 */
	public int shapeCount() {
		return _childShape.size();
	}
	/**
	 * Returns the index of a specified child inside the CarrierShape Object
	 * 
	 */
	public int indexOf(Shape shape) {
		return _childShape.indexOf(shape);
	}
	/**
	 *  Returns true if the Shape argument is a child of the CarrierShape
	 *  object the shape is called on, false otherwise.
	 */
	public boolean contains(Shape shape) {
		return _childShape.contains(shape);
	}

}
