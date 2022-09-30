package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.visitors.cmds.ABallAlgoCmd;
import provided.logger.ILoggerControl;

/**
 * Interface for ball configuration algorithms.
 * 
 * @author Phoebe Scaccia
 */
public interface IConfigBallAlgo extends IBallAlgo<Void, Void> {
	/**
	 * Instantiate a configuration algorithm that defaults to a no-op and where additional commands can be added later.
	 * @return A configuration algo defaulting to no-op
	 */
	public static IConfigBallAlgo MakeDefaultNULL() {
		return new AConfigBallAlgo(ILoggerControl.getSharedLogger(), IBallAlgo2ModelAdapter.NULL) {
		
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 6104726835094776778L;

			@Override
			public String toString() {
				return "IConfigBallAlgo.NULL";
			}
		
		};
		
	}; 

	/**
	 * Return a configuration algorithm that defaults to an error command and where additional commands can be added later/
	 * @return A configuration algo defaulting to error.
	 */
	public static IConfigBallAlgo MakeDefaultERROR() { 
		return new AConfigBallAlgo(ILoggerControl.getSharedLogger(), IBallAlgo2ModelAdapter.NULL) {

			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -9126885024661987013L;
	
			{
				setDefaultCmd(ABallAlgoCmd.MakeError(null));
			}
			
			@Override
			public String toString() {
				return "IConfigBallAlgo.ERROR";
			}
		}; 
	}
}