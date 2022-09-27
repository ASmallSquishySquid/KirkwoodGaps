package model.strategies.paint.factory;

import java.awt.Shape;

/**
 * Interface for a shape factory.
 *
 * @author kyled
 *
 */
public interface IShapeFactory {

	/**
	 * Constructor for a shape.
	 * @param x x translation
	 * @param y y translation
	 * @param xScale x scaling
	 * @param yScale y scaling
	 * @return a Shape object
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale);

}
