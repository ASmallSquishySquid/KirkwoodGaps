package model.strategies.interact;

import java.awt.Point;
import java.awt.Rectangle;

import model.Ball;
import model.IBall;
import model.IBallAlgo;
import model.IBallCmd;
import model.strategies.update.PoppingStrategy;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * A strategy where the ball explodes the ball it is interacting with.
 * 
 * @author Phoebe Scaccia
 */
public class ExplosionStrategy implements IInteractStrategy<IBallCmd> {

	@Override
	public void init(IBall context) {
		return;
	}

	@Override
	public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> dispatcher) {
		return new IBallCmd() {
			
			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				target.getLogger().log(LogLevel.INFO, "BOOM!");
				dispatcher.removeObserver((Ball) target);
				
				Point location = new Point((int) Math.round(target.getLocation().x), (int) Math.round(target.getLocation().y));
				int explodedRadius = target.getRadius() / 2;
				
				// If the balls aren't too tiny
				
				if (explodedRadius > 2) {
					
					// Generate a proportional number of popping balls.
					
					for (int i = 0; i < target.getRadius() / 3; i++) {

						// Get a random velocity up to two times greater than <code>context</code>'s in the x and y directions.

						Point newVelocity = Randomizer.Singleton
								.randomVel(new Rectangle((int) Math.round(target.getVelocity().x * 2), (int) Math.round(target.getVelocity().y * 2)));

						dispatcher.addObserver(new Ball(new Point(location), explodedRadius, newVelocity,
								target.getColor(), target.getContainer(), new IBallAlgo() {

									@Override
									public void caseDefault(IBall newContext) {
										newContext.setUpdateStrategy(new PoppingStrategy());
										newContext.setPaintStrategy(target.getPaintStrategy());
									}
								}, target.getAdapter()));
					}
				}
			}
		};
	}

}
