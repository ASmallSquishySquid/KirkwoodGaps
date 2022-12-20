package model.strategies.criteria;

import model.balls.IBall;

/**
 * A strategy composed of two criteria strategies.
 * 
 * @author Phoebe Scaccia
 */
public class CompositeCriteriaStrategy implements ICriteriaStrategy {

	/**
	 * The first criteria strategy.
	 */
	private ICriteriaStrategy strat1;
	/**
	 * The second criteria strategy.
	 */
	private ICriteriaStrategy strat2;

	/**
	 * Constructor for a new CompositeCriteriaStrategy.
	 * 
	 * @param strat1 an ICriteriaStrategy
	 * @param strat2 an ICriteriaStrategy
	 */
	public CompositeCriteriaStrategy(ICriteriaStrategy strat1, ICriteriaStrategy strat2) {
		this.strat1 = strat1;
		this.strat2 = strat2;
	}

	@Override
	public void init(IBall context) {
		this.strat1.init(context);
		this.strat2.init(context);
	}

	@Override
	public boolean satisfied(IBall context, IBall target) {
		return this.strat1.satisfied(context, target) || this.strat2.satisfied(context, target);
	}

}
