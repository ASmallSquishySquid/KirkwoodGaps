package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.strategies.update.ErrorUpdateStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;

/**
 * A config algo to handle errors.
 * 
 * @author Phoebe Scaccia
 */
public class ErrorConfigBallAlgo extends AConfigBallAlgo {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -5622241858391572861L;

	/**
	 * Constructor for a new ErrorConfigBallAlgo.
	 */
	public ErrorConfigBallAlgo() {
		super(ILoggerControl.getSharedLogger(), IBallAlgo2ModelAdapter.NULL);
		setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 2522362071478378881L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				installUpdateStrategy(host, new ErrorUpdateStrategy());
				return null;
			}
			
		});
	}

}