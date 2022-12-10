package model.balls;

import java.awt.Component;
import model.adapters.IModel2BallAdapter;
import model.visitors.algos.ErrorConfigBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * A ball that handles errors.
 * 
 * @author Phoebe Scaccia
 */
public class ErrorBall extends ABall {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -8935415963329780965L;

	/**
	 *  The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(ErrorBall.class.getName());

	/**
	 * Constructor for a new ErrorBall.
	 * 
	 * @param distance the ABall's position.
	 * @param angle the starting angle around the sun in radians
	 * @param radius the ABall's radius.
	 * @param mass the mass of this ball
	 * @param container the ABall's container.
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	public ErrorBall(double distance, double angle, int radius, double mass, Component container, IModel2BallAdapter modelAdapter) {
		super(id, distance, angle, radius, mass, container, new ErrorConfigBallAlgo(), modelAdapter);
	}

}
