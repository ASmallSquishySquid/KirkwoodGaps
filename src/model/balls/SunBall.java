package model.balls;

import java.awt.Component;
import java.awt.geom.Point2D;

import model.Constants;
import model.adapters.IModel2BallAdapter;
import model.visitors.algos.ConfigSunBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * A ball that represents the sun.
 * 
 * @author Phoebe Scaccia
 */
public class SunBall extends ABall {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -4962465144107028618L;
	
	/**
	 * The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(SunBall.class.getName());
	
	/**
	 * Constructor for a new SunBall.
	 * 
	 * @param container the ABall's container.
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	public SunBall(Component container, IModel2BallAdapter modelAdapter) {
		super(SunBall.id, 0, 0, 6.957e5, Constants.sunMass, container, new ConfigSunBallAlgo(), modelAdapter);
		super.setVelocity(new Point2D.Double(0, 0));
	}
	
	/**
	 * @param newVelocity : the ABall's new velocity.
	 */
	@Override
	public void setVelocity(Point2D.Double newVelocity) {
		return;
	}
	
	/**
	 * @param pos : the ABall's new location.
	 */
	@Override
	public void setLocation(Point2D.Double pos) {
		return;
	}

}
