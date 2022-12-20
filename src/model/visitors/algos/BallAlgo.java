package model.visitors.algos;

import model.balls.IBall;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.impl.ABallHostAlgo;

/**
 * A concrete ball algorithm.
 * 
 * @author Phoebe Scaccia
 *
 * @param <R> The return type of this visitor's process
 * @param <P> The input parameter type of this visitor's process
 */
public class BallAlgo<R, P> extends ABallHostAlgo<R, P, IBall> implements IBallAlgo<R, P> {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = -6119755916662028965L;

	/**
	 * A name for toString() to display.  
	 */
	private String name = super.toString();

	/**
	 * Constructor for a new BallAlgo.
	 * 
	 * @param defaultCmd the default command case to use
	 */
	public BallAlgo(ABallAlgoCmd<R, P> defaultCmd) {
		super(defaultCmd);
	}

	/**
	 * Constructor for the class.
	 * @param name Friendly name for the toString() method to return 
	 * @param defaultCmd  The default case command to use
	 */
	public BallAlgo(String name, ABallAlgoCmd<R, P> defaultCmd) {
		super(defaultCmd);
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 * <br/>
	 * Displays given friendly name string or inherited toString() if name not given.
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Accessor for the friendly name
	 * @return The friendly name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accessor for the friendly name
	 * @param name The friendly name to use
	 */
	public void setName(String name) {
		this.name = name;
	}

}