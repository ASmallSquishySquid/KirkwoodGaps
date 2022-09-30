package model.strategies.update;

import model.IBall;
import model.visitors.cmds.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * A strategy for a ball that switches strategies.
 *
 * @author Phoebe Scaccia
 */
public class SwitcherStrategy implements IUpdateStrategy {

	/**
	 * The current strategy for this switcher.
	 */
	private IUpdateStrategy currStrategy;

	/**
	 * Constructor for a new SwitcherStrategy.
	 */
	public SwitcherStrategy() {
		this.currStrategy = new StraightStrategy();
	}

	@Override
	public void init(IBall context) {
		return;
	}
	
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		this.currStrategy.updateState(context, dispatcher, didBounce);
	}

	/**
	 * Updates the strategy to be <code>newStrategy</code>.
	 *
	 * @param newStrategy a strategy
	 */
	public void setStrategy(IUpdateStrategy newStrategy) {
		this.currStrategy = newStrategy;
	}

}
