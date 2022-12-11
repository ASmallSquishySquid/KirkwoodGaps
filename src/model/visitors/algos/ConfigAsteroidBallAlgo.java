package model.visitors.algos;

import model.balls.IBall;
import model.strategies.paint.BallStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;

/**
 * Configures an asteroid ball.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigAsteroidBallAlgo extends AConfigBallAlgo {
	
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -6218703835883810795L;

	/**
	 * Constructor for a new ConfigSunBallAlgo.
	 */
	public ConfigAsteroidBallAlgo() {
		setDefaultCmd(new ABallAlgoCmd<Void, Void>() {

			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -2498773341125118428L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				installPaintStrategy(host, new BallStrategy());
				return null;
			}
		});		
	}
}
