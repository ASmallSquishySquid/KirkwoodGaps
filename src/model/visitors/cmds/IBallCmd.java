package model.visitors.cmds;

import model.balls.IBall;
import provided.utils.dispatcher.IDispatcher;

/**
 * A command to pass to the ball from the dispatcher.
 * 
 * @author Kyle Derbabian
 */
@FunctionalInterface
public interface IBallCmd {

	/**
	 * Something to do to the ball.
	 *
	 * @param context a ABall object
	 * @param disp a dispatcher
	 */
	public abstract void apply(IBall context, IDispatcher<IBallCmd> disp);

}
