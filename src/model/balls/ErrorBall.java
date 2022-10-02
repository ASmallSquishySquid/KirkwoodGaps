package model.balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import model.adapters.IModel2BallAdapter;
import model.visitors.algos.AConfigBallAlgo;
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
	 * @param p the position.
	 * @param r the radius.
	 * @param v the velocity.
	 * @param c the color.
	 * @param container the container.
	 * @param installAlgo the algo to complete the installation of strategies and any other desired operations
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	public ErrorBall(Point p, int r, Point v, Color c, Component container, 
			AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter) {
		super(id, p, r, v, c, container, new ErrorConfigBallAlgo(), modelAdapter);
	}

}