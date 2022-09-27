package model.strategies.paint;

import java.awt.geom.AffineTransform;

import model.strategies.paint.factory.EllipseShapeFactory;

/**
 * A strategy that paints an ellipse.
 * 
 * @author Kyle Derbabian
 */
public class EllipseStrategy extends ShapePaintStrategy {

	/**
	 * Constructor for a new EllipseStrategy.
	 */
	public EllipseStrategy() {
		this(new AffineTransform(), 0, 0, 4.0 / 3.0, 2.0 / 3.0);
	}

	/**
	 * Constructor for a new EllipseStrategy.
	 * 
	 * @param at an AffineTransform object
	 * @param x the x position of the ellipse
	 * @param y the y position of the ellipse
	 * @param width the width of the ellipse
	 * @param height the height of the ellipse
	 */
	public EllipseStrategy(AffineTransform at, double x, double y, double width, double height) {
		super(at, EllipseShapeFactory.SINGLETON.makeShape(x, y, width, height));
	}

}
