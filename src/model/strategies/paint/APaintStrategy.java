package model.strategies.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.IBall;

/**
 * The invariant aspects of a paint strategy.
 *
 * @author Phoebe Scaccia
 */
public abstract class APaintStrategy implements IPaintStrategy {

	/**
	 * The affine transform object.
	 */
	protected AffineTransform at;

	/**
	 * Constructor for a new APaintStrategy.
	 *
	 * @param at an Affine Transform
	 */
	public APaintStrategy(AffineTransform at) {
		this.at = at;
	}

	@Override
	public void init(IBall context) {
		return;
	}

	@Override
	public void paint(Graphics g, IBall context) {
		double scale = context.getRadius();
		this.at.setToTranslation(context.getLocation().x, context.getLocation().y);
		this.at.scale(scale, scale);
		this.at.rotate(context.getVelocity().x, context.getVelocity().y);
		g.setColor(context.getColor());
		this.paintCfg(g, context);
		this.paintTransform(g, context, this.at);
	}

	/**
	 * Paints and transforms the ball.
	 *
	 * @param g a Graphics object
	 * @param context a ABall
	 * @param at an AffineTransform object
	 */
	public abstract void paintTransform(Graphics g, IBall context, AffineTransform at);

	/**
	 * Configures the painting of the object.
	 *
	 * @param g a Graphics object
	 * @param context a ABall
	 */
	protected void paintCfg(Graphics g, IBall context) {
		return;
	}

	/**
	 * @return the Affine Transform
	 */
	protected AffineTransform getAt() {
		return new AffineTransform(this.at);
	}

}
