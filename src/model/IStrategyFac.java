package model;

import model.strategies.update.IUpdateStrategy;

/**
 * An interface that defines a factory that instantiates a specific IUpdateStrategy.
 * 
 * @author Tim Louie
 * @author Phoebe Scaccia
 */
public interface IStrategyFac {

	/**
	 * Instantiates a specific IUpdateStrategy.
	 * @return IUpdateStrategy for the Ball.
	 */
	public IUpdateStrategy make();

}
