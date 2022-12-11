package model.balls;

import java.awt.Component;

import model.adapters.IModel2BallAdapter;
import model.visitors.algos.ConfigJupiterBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * A Jupiter ball.
 * 
 * @author Phoebe Scaccia
 */
public class JupiterBall extends ABall {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 7623402589999138207L;
	
	/**
	 * The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(JupiterBall.class.getName());
	
	/**
	 * Constructor for a new MarsBall.
	 * 
	 * @param container the ABall's container.
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	public JupiterBall(Component container, IModel2BallAdapter modelAdapter) {
		super(JupiterBall.id, 778.479e6, 0, 69911, 1.8982e27, container, new ConfigJupiterBallAlgo(), modelAdapter);
	}
}
