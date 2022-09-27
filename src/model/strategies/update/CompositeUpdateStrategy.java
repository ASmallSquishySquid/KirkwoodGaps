/**
 *
 */
package model.strategies.update;

import model.IBall;
import model.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * A strategy that combines two strategies.
 *
 * @author Phoebe Scaccia
 */
public class CompositeUpdateStrategy implements IUpdateStrategy {

	/**
	 * The first strategy.
	 */
	private IUpdateStrategy strategy1;
	/**
	 * The second strategy.
	 */
	private IUpdateStrategy strategy2;

	/**
	 * Constructor for a new CompositeUpdateStrategy.
	 *
	 * @param strategy1 the first strategy
	 * @param strategy2 the second strategy
	 */
	public CompositeUpdateStrategy(IUpdateStrategy strategy1, IUpdateStrategy strategy2) {
		this.strategy1 = strategy1;
		this.strategy2 = strategy2;
	}

	/**
	 * Updates the state of <code>context</code> with both strategies.
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		this.strategy1.updateState(context, dispatcher, didBounce);
		this.strategy2.updateState(context, dispatcher, didBounce);
	}

	@Override
	public void init(IBall context) {
		this.strategy1.init(context);
		this.strategy2.init(context);
	}

}
