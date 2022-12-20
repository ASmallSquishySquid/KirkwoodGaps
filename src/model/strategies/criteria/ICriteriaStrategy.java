package model.strategies.criteria;

import model.balls.IBall;

/**
 * An interface for criteria strategies.
 * 
 * @author Phoebe Scaccia
 */
public interface ICriteriaStrategy {
	/**
	 * Sets up the criteria strategy.
	 *
	 * @param context an IBall object
	 */
	public void init(IBall context);

	/**
	 * Checks if the two balls satisfy the criteria
	 *
	 * @param context the host IBall
	 * @param target an IBall
	 * @return <code>true</code> if the balls meet the criteria, <code>false</code> otherwise
	 */
	public boolean satisfied(IBall context, IBall target);

}