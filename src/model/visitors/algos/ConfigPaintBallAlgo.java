package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.strategies.paint.IPaintStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;

/**
 * COnfigures the paint strategy.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigPaintBallAlgo extends AConfigBallAlgo {
	
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 3817483329592753538L;

	/**
	 * Constructor for a new ConfigPaintBallAlgo.
	 * 
	 * @param classname the name returned by <code>toStrin()</code>
	 * @param newStrat the IPaintStrategy to install
	 */
	public ConfigPaintBallAlgo(String classname, IPaintStrategy newStrat) {
		super(ILoggerControl.getSharedLogger(), classname, IBallAlgo2ModelAdapter.NULL);
		this.setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 3299806968250426264L;

			public Void apply(IBallHostID index, IBall context, Void... params) {
				installPaintStrategy(context, newStrat);
				return null;
			}
		});
	}
}
