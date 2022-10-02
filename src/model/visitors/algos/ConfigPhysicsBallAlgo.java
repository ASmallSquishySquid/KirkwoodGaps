package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.DefaultBall;
import model.balls.IBall;
import model.strategies.criteria.EverywhereStrategy;
import model.strategies.interact.GravitationStrategy;
import model.strategies.paint.BallStrategy;
import model.strategies.paint.PlanetStrategy;
import model.strategies.update.GravityStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILogger;

/**
 * Configures default balls into planets with gravity.
 * Configures everything else into a bouncing ball.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigPhysicsBallAlgo extends AConfigBallAlgo {
	
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 8606948461179163588L;

	/**
	 * Constructor for a new ConfigPhysicsBallAlgo.
	 * 
	 * @param logger The logger to use
	 * @param algo2ModelAdpt the adapter to the model
	 */
	public ConfigPhysicsBallAlgo(ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt) {
		super(logger, algo2ModelAdpt);
		
		setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -3088873918774070128L;

			public Void apply(IBallHostID index, IBall context, Void... params) {
				installPaintStrategy(context, new BallStrategy());
				installUpdateStrategy(context, new GravityStrategy());
				return null;
			}
		});
		
		setCmd(DefaultBall.id, new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -6175097243789545398L;

			public Void apply(IBallHostID index, IBall context, Void... params) {
				installCriteriaStrategy(context, new EverywhereStrategy());
				installPaintStrategy(context, new PlanetStrategy());
				installInteractStrategy(context, new GravitationStrategy());
				return null;
			}
		});
	}
	
	@Override
	public String toString() {
		return "Physics";
	}

}
