package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * The default ball object.
 * 
 * @author Phoebe Scaccia
 */
public class DefaultBall extends ABall {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -4845345229951036636L;
	
	/**
	 * The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(DefaultBall.class.getName());

	/**
	 * Constructor for a new DefaultBall.
	 * 
	 * @param p the ABall's position.
	 * @param r the ABall's radius.
	 * @param v the ABall's velocity.
	 * @param c the ABall's color.
	 * @param container the ABall's container.
	 * @param installAlgo The algo to complete the installation of strategies and any other desired operations
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	public DefaultBall(Point p, int r, Point v, Color c, Component container, IBallAlgo installAlgo,
			IModel2BallAdapter modelAdapter) {
		super(id, p, r, v, c, container, installAlgo, modelAdapter);
	}
	

}
