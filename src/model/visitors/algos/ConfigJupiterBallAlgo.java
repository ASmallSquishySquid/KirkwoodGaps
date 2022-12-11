package model.visitors.algos;

import model.balls.IBall;
import model.strategies.criteria.EverywhereStrategy;
import model.strategies.interact.GravitationStrategy;
import model.strategies.paint.JupiterStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;

/**
 * Configures a Jupiter ball.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigJupiterBallAlgo extends AConfigBallAlgo {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 4783580393660812475L;

	/**
	 * Constructor for a new ConfigSunBallAlgo.
	 */
	public ConfigJupiterBallAlgo() {
		setDefaultCmd(new ABallAlgoCmd<Void, Void>() {

			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -2498773341125118428L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				installCriteriaStrategy(host, new EverywhereStrategy());
				installPaintStrategy(host, new JupiterStrategy());
				installInteractStrategy(host, new GravitationStrategy());
				return null;
			}
		});		
	}
	
}
