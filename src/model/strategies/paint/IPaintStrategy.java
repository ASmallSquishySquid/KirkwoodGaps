package model.strategies.paint;

import java.awt.Graphics;

import model.balls.IBall;

/**
 * An interface for the possible paint strategies.
 *
 * @author Phoebe Scaccia
 */
public interface IPaintStrategy {

	/**
	 * Sets up the paint strategy.
	 * 
	 * @param context a ABall object
	 */
	public void init(IBall context);

	/**
	 * Paints according to the strategy.
	 *
	 * @param g a Graphics object
	 * @param context a ABall object
	 */
	public void paint(Graphics g, IBall context);

}
