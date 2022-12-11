package model.balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.geom.Point2D;

import model.Constants;
import model.adapters.IModel2BallAdapter;
import model.visitors.algos.ConfigAsteroidBallAlgo;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.impl.VectorUtil;

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
	 * The color of this asteroid.
	 */
	private Color color;
	
	/**
	 * Constructor for a new MarsBall.
	 * 
	 * @param container the ABall's container.
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	public AsteroidBall(Component container, IModel2BallAdapter modelAdapter) {
		super(AsteroidBall.id, Constants.getRandomAsteroidDistance(), Math.toRadians(Math.random() * 360), 20, 
				Constants.getRandomAsteroidMass(), container, new ConfigAsteroidBallAlgo(), modelAdapter);
		Point2D distanceVector = VectorUtil.Singleton.vectorTo(Constants.sunPosition, this.getLocation());
		double distance = Math.sqrt(Math.pow(distanceVector.getX(), 2) + Math.pow(distanceVector.getY(), 2));
		this.color = Color.WHITE;
		if (distance > (3.74e8 / Constants.kmToPixels)) {
			this.color = Color.CYAN;
		}
		if (distance > (4.22e8 / Constants.kmToPixels)) {
			this.color = Color.MAGENTA;
		}
	}
	
	/**
	 * @return the color of this asteroid.
	 */
	public Color getColor() {
		return this.color;
	}
	
	@Override
	public void interact(IDispatcher<IBallCmd> dispatcher) {
		return;
	}
}
