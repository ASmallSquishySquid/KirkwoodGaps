/**
 *
 */
package model.strategies.update;

import java.awt.geom.Point2D;

import model.IBall;
import model.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * A strategy that simulates the acceleration of gravity on the ball.
 *
 * @author Phoebe Scaccia
 */
public class GravityStrategy implements IUpdateStrategy {

	@Override
	public void init(IBall context) {
		return;
	}
	
	/**
	 * Applies the acceleration of gravity to the ball.
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		Point2D.Double velocity = context.getVelocity();

		velocity.y += 1;
		context.setVelocity(velocity);
	}

}