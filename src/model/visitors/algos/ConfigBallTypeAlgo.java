package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;

/**
 * Algo to configure the ball type.
 * 
 * @author Annita Chang
 */
public class ConfigBallTypeAlgo extends AConfigBallAlgo {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 7266464024304555341L;

	/**
	 * Constructor for a new ConfigBallTypeAlgo.
	 * 
	 * @param classname the String name
	 * @param newBall the ball type to install
	 */
	public ConfigBallTypeAlgo(String classname, IBall newBall) {
		super(ILoggerControl.getSharedLogger(), classname, IBallAlgo2ModelAdapter.NULL);
		setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -8036857938243545652L;

			public Void apply(IBallHostID index, IBall context, Void... params) {
				return null;
			}
		});
	}
}
