package model.strategies.update;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import model.ABall;
import model.DefaultBall;
import model.IBall;
import model.IBallAlgo;
import model.IBallCmd;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * A strategy that bounces a random number of times before partying.
 *
 * @author Phoebe Scaccia
 */
public class PartyStrategy implements IUpdateStrategy {

	/**
	 * The Randomizer singleton object.
	 */
	private final Randomizer r = Randomizer.Singleton;

	/**
	 * The number of bounces before this ball parties.
	 */
	private int bounces = r.randomInt(1, 10);
	/**
	 * The original color of the ball.
	 */
	private Color originalColor;

	@Override
	public void init(IBall context) {
		this.originalColor = context.getColor();
	}
	
	/**
	 * Counts down the number of bounces.
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		if (didBounce) {
			this.bounces--;

			// When this ball is done bouncing, start the party.

			if (this.bounces < 0) {
				context.getLogger().log(LogLevel.INFO, "Time to party!");
				dispatcher.removeObserver((ABall) context);
				Point location = new Point((int) Math.round(context.getLocation().x), (int) Math.round(context.getLocation().y));
				// Generate a proportional number of popping balls.
				for (int i = 0; i < context.getRadius() / 3; i++) {

					// Get a random velocity up to two times greater than <code>context</code>'s in the x and y directions.

					Point newVelocity = r
							.randomVel(new Rectangle((int) Math.round(context.getVelocity().x * 2), (int) Math.round(context.getVelocity().y * 2)));

					dispatcher.addObserver(new DefaultBall(new Point(location), context.getRadius() / 2, newVelocity,
							this.originalColor, context.getContainer(), new IBallAlgo() {

								@Override
								public void caseDefault(IBall newContext) {
									newContext.setUpdateStrategy(new PoppingStrategy());
									newContext.setPaintStrategy(context.getPaintStrategy());
								}
							}, context.getAdapter()));
				}
			}
		} else if (this.bounces == 0) {
			context.setColor(Randomizer.Singleton.randomColor());
		} else if (this.bounces > 0) {
			this.originalColor = context.getColor();
		}
	}

}
