package model.balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import model.adapters.IModel2BallAdapter;
import model.visitors.algos.AConfigBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * A predator ball
 * 
 * @author Phoebe Scaccia
 */
public class PredatorBall extends ABall {
	
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 6598028933626593084L;
	/**
	 * The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(PredatorBall.class.getName());
	
	/**
	 * Constructor for a new PredatorBall.
	 * 
	 * @param p the position.
	 * @param r the radius.
	 * @param v the velocity.
	 * @param c the color.
	 * @param container the container.
	 * @param installAlgo The algo to complete the installation of strategies and any other desired operations
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	protected PredatorBall(Point p, int r, Point v, Color c, Component container,
			AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter) {
		super(id, p, r, v, c, container, installAlgo, modelAdapter);
	}

}
