package model.strategies.criteria;

import model.balls.IBall;

/**
 * A strategy for the ball to interact with everything, everywhere.
 * 
 * @author Phoebe Scaccia
 */
public class EverywhereStrategy implements ICriteriaStrategy {

	@Override
	public void init(IBall context) {
		return;
	}

	@Override
	public boolean satisfied(IBall context, IBall target) {
		if (context != target) {
			return true;
		} else {
			return false;
		}
	}

}
