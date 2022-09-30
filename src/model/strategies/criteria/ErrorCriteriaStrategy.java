package model.strategies.criteria;

import model.balls.IBall;
import model.strategies.update.ErrorUpdateStrategy;

/**
 * A strategy to handle errors.
 * 
 * @author Phoebe Scaccia
 */
public class ErrorCriteriaStrategy implements ICriteriaStrategy {

	@Override
	public void init(IBall context) {
		context.setUpdateStrategy(new ErrorUpdateStrategy());
	}

	@Override
	public boolean satisfied(IBall context, IBall target) {
		return false;
	}

}