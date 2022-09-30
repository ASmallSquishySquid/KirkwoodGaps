package model.strategies.update;

import java.awt.Point;
import java.awt.Rectangle;

import model.DefaultBall;
import model.IBall;
import model.visitors.algos.BallAlgo;
import model.visitors.cmds.ABallAlgoCmd;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * A strategy that spawns a popping ball with the same strategy on each bounce.
 *
 * @author Phoebe Scaccia
 */
public class SpawnStrategy implements IUpdateStrategy {

	@Override
	public void init(IBall context) {
		return;
	}
	
	/**
	 * Spawns a new ball with the same strategies (plus popping) on a bounce.
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		if (didBounce) {

			// Spawn a new ball.

			Point newVelocity = Randomizer.Singleton
					.randomVel(new Rectangle((int) Math.round(context.getVelocity().x * 2), (int) Math.round(context.getVelocity().y * 2)));

			dispatcher.addObserver(new DefaultBall(new Point((int) Math.round(context.getLocation().x), (int) Math.round(context.getLocation().y)), context.getRadius() / 2, newVelocity,
					context.getColor(), context.getContainer(), new BallAlgo<Void, Void>(new ABallAlgoCmd<Void, Void>() {
						/**
						 * For serialization.
						 */
						private static final long serialVersionUID = 2748943448736414040L;

						@Override
						public Void apply(IBallHostID index, IBall newContext, Void... params) {
							newContext.setUpdateStrategy(new PoppingStrategy());
							newContext.setPaintStrategy(context.getPaintStrategy());
							return null;
						}
					}), context.getAdapter()));

			context.getLogger().log(LogLevel.INFO, "Another one!");
		}
	}

}