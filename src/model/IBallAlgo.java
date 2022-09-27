package model;

/**
 * An algorithm to process a host ball.
 * 
 * @author Phoebe Scaccia
 */
public interface IBallAlgo {
	/**
	 * The default case process
	 * 
	 * @param context The host ball to process.
	 */
	public void caseDefault(IBall context);

}