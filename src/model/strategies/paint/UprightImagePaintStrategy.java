package model.strategies.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.balls.IBall;

/**
 * A strategy that paints an upright image.
 *
 * @author Phoebe Scaccia
 */
public class UprightImagePaintStrategy extends ImagePaintStrategy {

	/**
	 * Constructor for a new UprightImagePaintStrategy.
	 *
	 * @param fileName the relative path to the image
	 * @param fillFactor the percentage of the average of the width and height of the image that defines a unit radius for the image
	 */
	public UprightImagePaintStrategy(String fileName, double fillFactor) {
		super(fileName, fillFactor);
	}

	/**
	 * Constructor for a new UprightImagePaintStrategy.
	 *
	 * @param at an AffineTransform object
	 * @param fileName the relative path to the image
	 * @param fillFactor the percentage of the average of the width and height of the image that defines a unit radius for the image
	 */
	public UprightImagePaintStrategy(AffineTransform at, String fileName, double fillFactor) {
		super(at, fileName, fillFactor);
	}

	@Override
	protected void paintCfg(Graphics g, IBall context) {
		super.paintCfg(g, context);
		if (context.getVelocity().getX() < 0) {
			this.at.scale(1, -1);
		}
	}

}
