package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;

/**
 * A composite of multiple config ball algos.
 * 
 * @author Phoebe Scaccia
 */
public class CompositeConfigBallAlgo extends AConfigBallAlgo {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 1889374189334814689L;

	/**
	 * Constructor for a new ConfigPaintBallAlgo.
	 * 
	 * @param algos the config algos being combined
	 */
	public CompositeConfigBallAlgo(AConfigBallAlgo... algos) {
		super(ILoggerControl.getSharedLogger(), IBallAlgo2ModelAdapter.NULL);
		this.setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -8567277113191415641L;

			public Void apply(IBallHostID index, IBall context, Void... params) {
				for (AConfigBallAlgo algo : algos) {
					context.execute(algo);
				}
				return null;
			}
		});
	}
	
	/**
	 * Constructor for a new ConfigPaintBallAlgo.
	 * 
	 * @param classname the name returned by <code>toStrin()</code>
	 * @param algos the config algos being combined
	 */
	public CompositeConfigBallAlgo(String classname, AConfigBallAlgo... algos) {
		super(ILoggerControl.getSharedLogger(), classname, IBallAlgo2ModelAdapter.NULL);
		this.setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -8567277113191415641L;

			public Void apply(IBallHostID index, IBall context, Void... params) {
				for (AConfigBallAlgo algo : algos) {
					context.execute(algo);
				}
				return null;
			}
		});
	}
}
