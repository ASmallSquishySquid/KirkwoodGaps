package model.balls;

import java.awt.Component;

import model.adapters.IModel2BallAdapter;
import model.visitors.algos.ConfigMarsBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * The Mars ball.
 * 
 * @author Phoebe Scaccia
 */
public class MarsBall extends ABall {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -1247738219158151064L;
	
	/**
	 * The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(MarsBall.class.getName());
	
	/**
	 * Constructor for a new MarsBall.
	 * 
	 * @param container the ABall's container.
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	public MarsBall(Component container, IModel2BallAdapter modelAdapter) {
		super(MarsBall.id, 2.279e8, 0, 3.396e3, 6.39e23, container, new ConfigMarsBallAlgo(), modelAdapter);
	}

}
