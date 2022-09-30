package model.strategies.criteria;

import model.balls.IBall;

/**
 * A strategy where the criteria is collision.
 * 
 * @author Phoebe Scaccia
 */
public class CollisionStrategy implements ICriteriaStrategy {

	@Override
	public void init(IBall context) {
		return;
	}

	/**
	 * Checks whether a collision between the two balls occurred.
	 */
	@Override
	public boolean satisfied(IBall context, IBall target) {
		if (context != target) {
			// Calculate the distance between the balls
			double dist = context.getLocation().distance(target.getLocation());
			// Calculate the minimum non-colliding distance (= max colliding distance)
			double minNonCollideDist = context.getRadius() + target.getRadius();
			// Check if interaction criteria is met (Check if they overlap):
			if (dist <= minNonCollideDist) {
				return true;
			}
		}
		return false;
	}

}
