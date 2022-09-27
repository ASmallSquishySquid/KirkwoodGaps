package model.strategies.paint.factory;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * A factory for polygons.
 * 
 * @author Kyle Derbabian
 */
public class PolygonShapeFactory implements IShapeFactory {

	/**
	 * An AffineTransfer.
	 */
	protected AffineTransform at;
	/**
	 * The scale factor.
	 */
	protected double scaleFactor;
	/**
	 * The polygon object.
	 */
	protected Polygon polygon = new Polygon();

	/**
	 * Constructor for a new PolygonShapeFactory.
	 * 
	 * @param at an AffineTransform object
	 * @param scaleFactor the scale factor
	 * @param points the points that make up the unit shape
	 */
	public PolygonShapeFactory(AffineTransform at, double scaleFactor, Point... points) {
		this.at = at;
		this.scaleFactor = scaleFactor;
		for (Point point : points) {
			this.polygon.addPoint((int) point.getX(), (int) point.getY());
		}
	}

	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		this.at.setToTranslation(x, y);
		this.at.scale(xScale * this.scaleFactor, yScale * this.scaleFactor);
		return this.at.createTransformedShape(this.polygon);
	}

}
