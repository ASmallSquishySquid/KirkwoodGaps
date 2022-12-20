package model.strategies.paint;

import java.awt.Graphics;

import model.balls.IBall;

/**
 * A strategy that has multiple IPaintStrategies inside.
 * 
 * @author Phoebe Scaccia
 */
public class CompositePaintStrategy implements IPaintStrategy {

	/**
	 * The first IPaintStrategy.
	 */
	private IPaintStrategy strat1;
	/**
	 * The second IPaintStrategy.
	 */
	private IPaintStrategy strat2;

	/**
	 * Constructor for a new CompositePaintStrategy.
	 * 
	 * @param strat1 an object that implements IPaintStrategy
	 * @param strat2 an object that implements IPaintStrategy
	 */
	public CompositePaintStrategy(IPaintStrategy strat1, IPaintStrategy strat2) {
		this.strat1 = strat1;
		this.strat2 = strat2;
	}

	@Override
	public void init(IBall context) {
		this.strat1.init(context);
		this.strat2.init(context);
	}

	@Override
	public void paint(Graphics g, IBall context) {
		this.strat1.paint(g, context);
		this.strat2.paint(g, context);
	}

}
