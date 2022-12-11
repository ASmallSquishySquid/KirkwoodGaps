package model.visitors.algos;

import model.balls.IBall;
import model.strategies.criteria.EverywhereStrategy;
import model.strategies.interact.GravitationStrategy;
import model.strategies.paint.MarsStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;

/**
 * Configures the Mars ball.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigMarsBallAlgo extends AConfigBallAlgo {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -7425371947343767878L;

	/**
	 * Constructor for a new ConfigSunBallAlgo.
	 */
	public ConfigMarsBallAlgo() {
		setDefaultCmd(new ABallAlgoCmd<Void, Void>() {

			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -2498773341125118428L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				installCriteriaStrategy(host, new EverywhereStrategy());
				installPaintStrategy(host, new MarsStrategy());
				installInteractStrategy(host, new GravitationStrategy());
				return null;
			}
		});		
	}
}
