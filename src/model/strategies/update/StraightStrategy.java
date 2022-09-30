package model.strategies.update;

import model.balls.IBall;
import model.visitors.cmds.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * A strategy where the ball moves and bounces according to Newtonian motion.
 *
 * @author Phoebe Scaccia
 */
public class StraightStrategy implements IUpdateStrategy {

	@Override
	public void init(IBall context) {
		return;
	}
	
	/**
	 * Does nothing.
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		return;
	}

}
