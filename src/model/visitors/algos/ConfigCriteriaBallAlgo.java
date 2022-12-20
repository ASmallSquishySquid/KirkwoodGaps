package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.strategies.criteria.ICriteriaStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;

/**
 * Algo to configure the criteria strategy.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigCriteriaBallAlgo extends AConfigBallAlgo {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 7266464024304555341L;

	/**
	 * Constructor for a new ConfigCriteriaBallAlgo.
	 * 
	 * @param classname the String name
	 * @param newStrat the Criteria Strategy to install
	 */
	public ConfigCriteriaBallAlgo(String classname, ICriteriaStrategy newStrat) {
		super(ILoggerControl.getSharedLogger(), classname, IBallAlgo2ModelAdapter.NULL);
		setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -8036857938243545652L;

			public Void apply(IBallHostID index, IBall context, Void... params) {
				installCriteriaStrategy(context, newStrat);
				return null;
			}
		});
	}
}
