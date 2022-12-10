package model.visitors.algos;

import model.balls.IBall;
import model.strategies.criteria.EverywhereStrategy;
import model.strategies.interact.GravitationStrategy;
import model.strategies.paint.SunStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;

/**
 * Sets up the sun ball.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigSunBallAlgo extends AConfigBallAlgo {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 8326898379402684372L;

	/**
	 * Constructor for a new ConfigSunBallAlgo.
	 */
	public ConfigSunBallAlgo() {
		setDefaultCmd(new ABallAlgoCmd<Void, Void>() {

			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -2498773341125118428L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				installCriteriaStrategy(host, new EverywhereStrategy());
				installPaintStrategy(host, new SunStrategy());
				installInteractStrategy(host, new GravitationStrategy());
				return null;
			}
		});		
	}

}
