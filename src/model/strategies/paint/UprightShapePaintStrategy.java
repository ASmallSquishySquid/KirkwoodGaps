/**
 *
 */
package model.strategies.paint;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.balls.IBall;

/**
 * A strategy that paints a shape that is always upright.
 *
 * @author Phoebe Scaccia
 */
public class UprightShapePaintStrategy extends ShapePaintStrategy {

	/**
	 * Constructor for a new UprightShapePaintStrategy.
	 *
	 * @param at an AffineTransform object
	 * @param prototypeShape the prototype shape
	 */
	public UprightShapePaintStrategy(AffineTransform at, Shape prototypeShape) {
		super(at, prototypeShape);
	}

	@Override
	protected void paintCfg(Graphics g, IBall context) {
		super.paintCfg(g, context);
		if (context.getVelocity().getX() < 0) {
			this.at.scale(1, -1);
		}
	}

}
