package spaceshapes;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Color _color;
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(new Color(212, 212, 212));
	}

	/**
	 * @see spaceshapes.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * @see spaceshapes.Painter.fillRect.
	 */
	public void fillRect(int x, int y, int width, int height) {
		_g.setColor(getColor());
		_g.fillRect(x,  y , width, height);	
		_g.setColor(Color.white);
	}

	public Color getColor() {
		return _color;
		
	}
	
	/**
	 * @see spaceshapes.Painter.setColor.
	 */
	public void setColor(Color color) {
		_color = color;
	}
	
	/**
	 * @see spaceshapes.Painter.translate.
	 */
	public void translate(int x, int y) {
		_g.translate(x, y);
	}
	
	/**
	 * @see spaceshapes.Painter.drawCenteredText.
	 */
	public void drawCenteredText(int x, int y, String text) {
		// Get the data from fontMetrics
		FontMetrics font = _g.getFontMetrics();
		int ascent = font.getAscent();
		int descent = font.getDescent();
		
		// Shifts the x axis of the string to centre it
		int centered = (font.stringWidth(text))/2;
		// Shifts the y axis of the string if necessary
		int y1 = y + (ascent-descent)/2;
		
		_g.drawString(text, x-centered, y1);
		
	}


	public void drawImage(Image img, int x, int y, int width, int height) {
		_g.drawImage(img, x, y, width, height, null);
	}
}
