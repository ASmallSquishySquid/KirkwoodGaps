package model.strategies.paint;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import model.balls.IBall;

/**
 * A paint strategy that paints a circular ball.
 *
 * @author Phoebe Scaccia
 */
public class BallStrategy implements IPaintStrategy {

	/**
	 * Constructor for a new BallStrategy.
	 */
	public BallStrategy() {
	}

	@Override
	public void init(IBall context) {
		return;
	}

	/**
	 * Paints a circle.
	 */
	@Override
	public void paint(Graphics g, IBall context) {
		int radius = (int) context.getRadius();
		Point2D location = context.getLocation();

		g.fillOval((int) (location.getX() - radius), (int) (location.getY() - radius), 2 * radius, 2 * radius);
	}

}
