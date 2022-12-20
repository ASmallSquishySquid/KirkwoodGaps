package model.strategies.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.balls.IBall;

/**
 * A strategy that paints a shape.
 * 
 * @author Kyle Derbabian
 */
public class ShapePaintStrategy extends APaintStrategy {

	/**
	 * The unit shape.
	 */
	protected Shape prototypeShape;

	/**
	 * Constructor for a new ShapePaintStrategy.
	 * 
	 * @param at an AffineTransform object
	 * @param prototypeShape the unit Shape object
	 */
	public ShapePaintStrategy(AffineTransform at, Shape prototypeShape) {
		super(at);
		this.prototypeShape = prototypeShape;
	}

	@Override
	public void paintTransform(Graphics g, IBall context, AffineTransform at) {
		((Graphics2D) g).fill(at.createTransformedShape(this.prototypeShape));
	}

}
