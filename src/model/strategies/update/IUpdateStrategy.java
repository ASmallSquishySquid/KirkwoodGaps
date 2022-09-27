package model.strategies.update;

import model.IBall;
import model.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * An interface for the possible Ball strategies.
 *
 * @author Tim Louie
 * @author Phoebe Scaccia
 */
public interface IUpdateStrategy {

	/**
	 * Sets up the update strategy.
	 * 
	 * @param context a Ball object
	 */
	public void init(IBall context);
	
	/**
	 * Updates the state of the Ball.
	 *
	 * @param context a Ball object
	 * @param dispatcher a Dispatcher object containing the ball <code>context</code>
	 * @param didBounce whether the <code>context</code> just bounced
	 */
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce);

}
