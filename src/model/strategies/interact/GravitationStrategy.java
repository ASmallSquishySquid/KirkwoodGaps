package model.strategies.interact;

import java.awt.geom.Point2D;

import model.Constants;
import model.balls.IBall;
import model.visitors.cmds.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * A strategy that pulls a ball towards the current ball.
 * 
 * @author Phoebe Scaccia
 */
public class GravitationStrategy implements IInteractStrategy<IBallCmd> {

	/**
	 * The gravitational constant G.
	 */
	private double gravitationalConstant = Constants.gravitationalConstant / 1e17;

	@Override
	public void init(IBall context) {
		return;
	}

	@Override
	public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> dispatcher) {
		// Model the ball's "mass" as desired, e.g. proportional to ball's area
		double contextMass = context.getMass();
		double dist = context.getLocation().distance(target.getLocation());

		return new IBallCmd() {
			@Override
			public void apply(IBall contextBall, IDispatcher<IBallCmd> disp) {
	
				// Get the direction of the force
	
				Point2D.Double unitVector = calcUnitVec(target.getLocation(), context.getLocation(), dist);
	
				// Get the target's change in velocity.
	
				Point2D.Double dV = calcdV(contextMass, unitVector, dist);
				Point2D.Double newVelocity = target.getVelocity();
				newVelocity.x += dV.x;
				newVelocity.y += dV.y;
	
				// Apply the acceleration.
	
				target.setVelocity(newVelocity);
			}
		};
	}

	/**
	 * Calculate the unit vector (normalized vector) from the location of the source ball to the location of the target ball.
	 * 
	 * @param lSource Location of the source ball
	 * @param lTarget Location of the target ball
	 * @param distance Distance from the source ball to the target ball
	 * @return A double-precision vector (point)
	 */
	private Point2D.Double calcUnitVec(Point2D.Double lSource, Point2D.Double lTarget, double distance) {
		// Calculate the normalized vector, from source to target
		double nx = (lTarget.x - lSource.x) / distance;
		double ny = (lTarget.y - lSource.y) / distance;

		return new Point2D.Double(nx, ny);
	}

	/**
	 * Calculates the change in velocity as a result of the acceleration due to gravity.
	 *
	 * @param mass the mass of the gravitational source
	 * @param unitVector the direction towards the source
	 * @param distance the distance to the source
	 * @return a Point2D.Double vector representing the change in velocity
	 */
	private Point2D.Double calcdV(double mass, Point2D.Double unitVector, double distance) {
		double acceleration = (this.gravitationalConstant * mass) / Math.pow(distance, 2);
		Point2D.Double dV = new Point2D.Double(unitVector.x, unitVector.y);
		dV.x *= acceleration;
		dV.y *= acceleration;
		return dV;
	}

}
