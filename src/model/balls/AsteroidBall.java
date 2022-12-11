package model.balls;

import java.awt.Component;

import model.Constants;
import model.adapters.IModel2BallAdapter;
import model.visitors.algos.ConfigAsteroidBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * An asteroid ball.
 * 
 * @author Phoebe Scaccia
 */
public class AsteroidBall extends ABall {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -3898009625683965962L;
	
	/**
	 * The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(AsteroidBall.class.getName());
	
	/**
	 * Constructor for a new MarsBall.
	 * 
	 * @param container the ABall's container.
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	public AsteroidBall(Component container, IModel2BallAdapter modelAdapter) {
		super(AsteroidBall.id, Constants.getRandomAsteroidDistance(), Math.toRadians(Math.random() * 360), 20, 
				Constants.getRandomAsteroidMass(), container, new ConfigAsteroidBallAlgo(), modelAdapter);
	}
}
