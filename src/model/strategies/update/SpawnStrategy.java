package model.strategies.update;

import java.awt.Point;
import java.awt.Rectangle;

import model.balls.DefaultBall;
import model.balls.IBall;
import model.visitors.algos.CompositeConfigBallAlgo;
import model.visitors.algos.ConfigPaintBallAlgo;
import model.visitors.algos.ConfigUpdateBallAlgo;
import model.visitors.cmds.IBallCmd;
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
					context.getColor(), context.getContainer(), new CompositeConfigBallAlgo(
							new ConfigUpdateBallAlgo(null, new PoppingStrategy()), 
							new ConfigPaintBallAlgo(null, context.getPaintStrategy())), context.getAdapter()));

			context.getLogger().log(LogLevel.INFO, "Another one!");
		}
	}

}