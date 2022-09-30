package model.strategies.paint;

import java.awt.Graphics;

import model.balls.IBall;

/**
 * A strategy that paints the DVD image symbol.
 *
 * @author Kyle Derbabian
 */
public class DVDStrategy extends ImagePaintStrategy {

	/**
	 * Constructor for a new DVDStrategy.
	 */
	public DVDStrategy() {
		super("images/DVD.png", 1);
	}

	/**
	 * Rotates the DVD image so that it is always in its original position.
	 */
	@Override
	protected void paintCfg(Graphics g, IBall context) {
		super.paintCfg(g, context);
		if (context.getVelocity().getY() < 0) {
			this.at.rotate(Math.atan(context.getVelocity().getX() / context.getVelocity().getY()));
		} else {
			this.at.rotate(Math.atan(context.getVelocity().getX() / context.getVelocity().getY()) + Math.PI);
		}
		this.at.rotate(Math.PI / 2);
	}

}
