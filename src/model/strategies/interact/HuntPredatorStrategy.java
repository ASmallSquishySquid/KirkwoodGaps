package model.strategies.interact;

import model.balls.IBall;
import model.balls.PredatorBall;
import model.visitors.algos.BallAlgo;
import model.visitors.cmds.ABallAlgoCmd;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;


/**
 * An type-dependent interaction strategy that the target ball gains mass when it is of type Predator.
 * @author Annita Chang
 *
 */
public class HuntPredatorStrategy extends AInteractStrategy {
	/**
	 * Constructor for the class
	 */
	public HuntPredatorStrategy() {
	}

	@Override
	public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
		target.execute(new BallAlgo<Void, Void>(new ABallAlgoCmd<>() {
			// Add generated serialVersionUID
				private static final long serialVersionUID = 8205255234000414526L;
				
				@Override
				public Void apply(IBallHostID index, IBall host, Void... params) {
					// no-op by default, i.e. non-PreyBalls will not have any interaction due to this strategy.
					return null;
				}
				
			}) {
			// Add generated serialVersionUID
				private static final long serialVersionUID = 1868334358866585207L;

				// Add additional commands in the "initializer block" of the ball algo's anonymous inner class
				{
					// Add different behavior for PreyBalls
					setCmd(PredatorBall.id, new ABallAlgoCmd<Void, Void>() {
						private static final long serialVersionUID = 7545117713512106388L;

						// Add generated serialVersionUID
						@Override
						public Void apply(IBallHostID index, IBall host, Void... params) {
							if (target.getRadius() < 75) {
								int change = (int) ((double) target.getRadius() * 0.05);
								target.setRadius(target.getRadius() + change);
								context.setRadius(Math.max(0, context.getRadius() - change));
								if (context.getRadius() == 0) {
									disp.removeObserver(context);
								}
							}
							//ILoggerControl.getSharedLogger().log(LogLevel.INFO, "Predator gained mass!");
							return null;
						}
						
						
					});
				
				}
		});
		return null;
	}

}
