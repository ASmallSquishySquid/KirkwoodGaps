package model.strategies.paint.factory;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * A factor that produces ellipses.
 * 
 * @author Kyle Derbabian
 */
public class EllipseShapeFactory implements IShapeFactory {

	/**
	 * A singleton for this factory.
	 */
	public static final EllipseShapeFactory SINGLETON = new EllipseShapeFactory();

	/**
	 * Constructor for a new EllipseShapeFactory.
	 */
	private EllipseShapeFactory() {
		return;
	}

	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		return new Ellipse2D.Double(x - xScale, y - yScale, xScale * 2, yScale * 2);
	}

}
