package model.strategies.interact;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import model.balls.IBall;
import model.visitors.cmds.IBallCmd;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * A strategy that teleports away a ball that satisfies the criteria.
 * 
 * @author michaelcuenca
 * @author Phoebe Scaccia
 */
public class TeleportStrategy implements IInteractStrategy<IBallCmd> {

	/**
	 * The randomizer.
	 */
	private Randomizer r = Randomizer.Singleton;

	public void init(IBall context) {
		return;
	}

	@Override
	public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> dispatcher) {
		return new IBallCmd() {
			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				target.update(dispatcher, new IBallCmd() {
					@Override
					public void apply(IBall context, IDispatcher<IBallCmd> disp) {
						//						Point newLocation = r.randomLoc(target.getContainer().getSize());
						//						target.setLocation(new Point2D.Double(newLocation.x, newLocation.y));
						Point newVel = r.randomVel(new Rectangle(10, 10));
						Point2D.Double newVelocity = new Point2D.Double(newVel.x, newVel.y);
						target.setVelocity(newVelocity);
						Color newColor = r.randomColor();
						target.setColor(newColor);
						int newRadius = r.randomInt(5, 20);
						target.setRadius(newRadius);

					}
				});
			}
		};
	}
}
