package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.strategies.update.IUpdateStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;

/**
 * Updates the update strategy for a ball.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigUpdateBallAlgo extends AConfigBallAlgo {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -1252609994043909345L;

	/**
	 * Constructor for a new ConfigPaintBallAlgo.
	 * 
	 * @param classname the name returned by <code>toStrin()</code>
	 * @param newStrat the IPaintStrategy to install
	 */
	public ConfigUpdateBallAlgo(String classname, IUpdateStrategy newStrat) {
		super(ILoggerControl.getSharedLogger(), classname, IBallAlgo2ModelAdapter.NULL);
		this.setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 3299806968250426264L;

			public Void apply(IBallHostID index, IBall context, Void... params) {
				installUpdateStrategy(context, newStrat);
				return null;
			}
		});
	}
}
