package model.strategies.paint;

import java.awt.Point;
import java.awt.geom.AffineTransform;

import model.strategies.paint.factory.PolygonShapeFactory;

/**
 * Draws a Triangle.
 *
 * @author kyled
 *
 */
public class TriangleStrategy extends ShapePaintStrategy {

	/**
	 * Constructor for a triangle strategy with default values.
	 */
	public TriangleStrategy() {
		this(new AffineTransform(), 0, 0, 1.0, 1.0);
	}

	/**
	 * Constructor for a triangle strategy.
	 * @param at an AffineTransform
	 * @param x x translation
	 * @param y y translation
	 * @param width the width of the triangle
	 * @param height the height of the triangle
	 */
	public TriangleStrategy(AffineTransform at, double x, double y, double width, double height) {
		super(at, new PolygonShapeFactory(at, 1, new Point((int) x, (int) y), new Point((int) x + 1, (int) y + 1),
				new Point((int) x + 1, (int) y - 1)).makeShape(x, y, width, height));
	}

}
