package model.strategies.interact;

import model.balls.IBall;
import model.balls.PreyBall;
import model.visitors.algos.BallAlgo;
import model.visitors.cmds.ABallAlgoCmd;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;

/**
 * An type-dependent interaction strategy that "kills" the TARGET ball when the target is a PreyBall.
 * This strategy should be installed on the "hunter" (context) ball.   It does not matter what 
 * type of ball the "hunter" is!
 * @author Annita Chang
 *
 */
public class KillPreyStrategy extends AInteractStrategy {

	/**
	 * Constructor for the class
	 */
	public KillPreyStrategy() {
	}

	@Override
	public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
		target.execute(new BallAlgo<Void, Void>(new ABallAlgoCmd<>() {
				// Add generated serialVersionUID
				private static final long serialVersionUID = -8207136148708694148L;
				
				@Override
				public Void apply(IBallHostID index, IBall host, Void... params) {
					// no-op by default, i.e. non-PreyBalls will not have any interaction due to this strategy.
					return null;
				}
				
			}) {

				// Add generated serialVersionUID
				private static final long serialVersionUID = 1L;

				// Add additional commands in the "initializer block" of the ball algo's anonymous inner class
				{
					// Add different behavior for PreyBalls
					setCmd(PreyBall.id, new ABallAlgoCmd<Void, Void>() {
						// Add generated serialVersionUID
						private static final long serialVersionUID = -2000633596351150890L;
						@Override
						public Void apply(IBallHostID index, IBall host, Void... params) {
							disp.removeObserver(target); // "Kill" them!
							ILoggerControl.getSharedLogger().log(LogLevel.INFO, "Prey ball killed!");
							return null;
						}
					});
				
				}
		});
		return null;
	}
}
