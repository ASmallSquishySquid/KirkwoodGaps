package model.strategies.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.balls.IBall;

/**
 * A multi paint strategy that always stays upright.
 *
 * @author Phoebe Scaccia
 */
public class UprightMultiPaintStrategy extends MultiPaintStrategy {

	/**
	 * Constructor for a new UprightMultiPaintStrategy.
	 * 
	 * @param at an AffineTransform object
	 * @param strats a list of strategies to paint
	 */
	public UprightMultiPaintStrategy(AffineTransform at, APaintStrategy... strats) {
		super(new AffineTransform(), strats);
	}

	/**
	 * Keeps the image upright.
	 */
	@Override
	protected void paintCfg(Graphics g, IBall context) {
		Math.atan2(0.1, 0.1);
		super.paintCfg(g, context);
		if (context.getVelocity().getX() < 0) {
			this.at.scale(1, -1);
		}
	}

}