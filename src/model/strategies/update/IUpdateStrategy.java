package model.strategies.update;

import model.balls.IBall;
import model.visitors.cmds.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * An interface for the possible ABall strategies.
 *
 * @author Tim Louie
 * @author Phoebe Scaccia
 */
public interface IUpdateStrategy {

	/**
	 * Sets up the update strategy.
	 * 
	 * @param context a ABall object
	 */
	public void init(IBall context);

	/**
	 * Updates the state of the ABall.
	 *
	 * @param context a ABall object
	 * @param dispatcher a Dispatcher object containing the ball <code>context</code>
	 */
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher);

}
