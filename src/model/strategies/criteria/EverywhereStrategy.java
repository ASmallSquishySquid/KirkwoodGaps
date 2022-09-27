package model.strategies.criteria;

import model.IBall;
import model.strategies.update.ErrorUpdateStrategy;

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
		if (context != target && !(target.getUpdateStrategy() instanceof ErrorUpdateStrategy) && 
				!(context.getUpdateStrategy() instanceof ErrorUpdateStrategy)) {
			return true;
		} else {
			return false;
		}
	}

}
