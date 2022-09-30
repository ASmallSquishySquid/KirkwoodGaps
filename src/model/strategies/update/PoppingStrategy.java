package model.strategies.update;

import model.ABall;
import model.IBall;
import model.IBallCmd;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * A strategy that pops the ball after a given number of bounces.
 *
 * @author Phoebe Scaccia
 */
public class PoppingStrategy implements IUpdateStrategy {

	/**
	 * The number of bounces before this ball pops.
	 */
	private int bounces = Randomizer.Singleton.randomInt(1, 10);

	@Override
	public void init(IBall context) {
		return;
	}
	
	/**
	 * Counts down the number of bounces.
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		if (didBounce) {
			this.bounces--;
			if (this.bounces < 0) {
				context.getLogger().log(LogLevel.INFO, "POP!");
				dispatcher.removeObserver((ABall) context);
			}
		}
	}

}
