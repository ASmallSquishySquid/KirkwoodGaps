package model.strategies.update;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

import model.balls.IBall;
import model.visitors.cmds.IBallCmd;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * A strategy in which the ball teleports to a random point when it hits a wall.
 *
 * @author Phoebe Scaccia
 */
public class TeleportingStrategy implements IUpdateStrategy {

	@Override
	public void init(IBall context) {
		return;
	}

	/**
	 * Teleports the ball to a new location when it bounces.
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		if (didBounce) {
			context.setLocation(generateValidLocation(context));
		}
	}

	/**
	 * Generates a valid location for the ball within its bounds.
	 *
	 * @param context a ABall object
	 * @return a Point object
	 */
	private Point2D.Double generateValidLocation(IBall context) {
		Dimension dimension = context.getContainer().getSize();
		int radius = context.getRadius();

		Dimension usableDimension = new Dimension((int) dimension.getWidth() - radius * 2,
				(int) dimension.getHeight() - radius * 2);
		Point usablePoint = Randomizer.Singleton.randomLoc(usableDimension);

		usablePoint.x += radius;
		usablePoint.y += radius;

		return new Point2D.Double(usablePoint.x, usablePoint.y);
	}

}