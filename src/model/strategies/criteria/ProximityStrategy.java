package model.strategies.criteria;

import model.IBall;

/**
 * A strategy for an AOE criteria.
 * 
 * @author Phoebe Scaccia
 */
public class ProximityStrategy implements ICriteriaStrategy {

	@Override
	public void init(IBall context) {
		return;
	}

	/**
	 * Checks if the balls are close enough to each other.
	 */
	@Override
	public boolean satisfied(IBall context, IBall target) {
		if (context != target) {
			// Calculate the distance between the balls
			double dist = context.getLocation().distance(target.getLocation());
			// Calculate the minimum proximity distance
			double minProximityDistance = (context.getRadius() + target.getRadius()) * 2;
			// Check if interaction criteria is met
			if (dist <= minProximityDistance) {
				return true;
			}
		}
		return false;
	}

}
