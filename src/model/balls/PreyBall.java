package model.balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import model.adapters.IModel2BallAdapter;
import model.visitors.algos.AConfigBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * A Prey Ball.
 * 
 * @author Phoebe Scaccia
 */
public class PreyBall extends ABall {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 1048678437196698827L;

	/**
	 *  The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(PreyBall.class.getName());
	
	/**
	 * Constructor for a new PreyBall.
	 * 
	 * @param p the position.
	 * @param r the radius.
	 * @param v the velocity.
	 * @param c the color.
	 * @param container the container.
	 * @param installAlgo The algo to complete the installation of strategies and any other desired operations
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	protected PreyBall( Point p, int r, Point v, Color c, Component container,
			AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter) {
		super(id, p, r, v, c, container, installAlgo, modelAdapter);
	}

}
