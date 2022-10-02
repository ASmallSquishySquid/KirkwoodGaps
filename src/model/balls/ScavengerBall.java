package model.balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import model.adapters.IModel2BallAdapter;
import model.visitors.algos.AConfigBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * A scavenger ball.
 * 
 * @author Phoebe Scaccia
 */
public class ScavengerBall extends ABall {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 7221106886392605605L;
	/**
	 * The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(ScavengerBall.class.getName());
	
	/**
	 * Constructor for a new ScavengerBall.
	 * 
	 * @param p the position.
	 * @param r the radius.
	 * @param v the velocity.
	 * @param c the color.
	 * @param container the container.
	 * @param installAlgo The algo to complete the installation of strategies and any other desired operations
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	protected ScavengerBall(Point p, int r, Point v, Color c, Component container,
			AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter) {
		super(id, p, r, v, c, container, installAlgo, modelAdapter);
	}

}