package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.strategies.interact.IInteractStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;

/**
 * Configures the interact strategy.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigInteractBallAlgo extends AConfigBallAlgo {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 3971849401972624266L;

	/**
	 * Constructor for a new ConfigInteractBallAlgo.
	 * 
	 * @param classname the string name
	 * @param newStrat the Interact Strategy to install
	 */
	public ConfigInteractBallAlgo(String classname, IInteractStrategy<IBallCmd> newStrat) {
		super(ILoggerControl.getSharedLogger(), classname, IBallAlgo2ModelAdapter.NULL);
		setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 4046947541898904816L;

			public Void apply(IBallHostID index, IBall context, Void... params) {
				installInteractStrategy(context, newStrat);
				return null;
			}
		});
	}
}
