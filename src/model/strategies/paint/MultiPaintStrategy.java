package model.strategies.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.balls.IBall;

/**
 * A strategy that combines multiple paint strategies.
 *
 * @author Phoebe Scaccia
 */
public class MultiPaintStrategy extends APaintStrategy {

	/**
	 * The paint strategies within this composite paint strategy.
	 */
	private APaintStrategy[] paintStrategies;

	/**
	 * Constructor for a new MultiPaintStrategy.
	 *
	 * @param paintStrategies the paint strategies
	 */
	public MultiPaintStrategy(APaintStrategy... paintStrategies) {
		super(new AffineTransform());
		this.paintStrategies = paintStrategies;
	}

	/**
	 * Constructor for a new MultiPaintStrategy.
	 *
	 * @param at an Affine Transform
	 * @param paintStrategies the paint strategies
	 */
	public MultiPaintStrategy(AffineTransform at, APaintStrategy... paintStrategies) {
		super(at);
		this.paintStrategies = paintStrategies;
	}

	@Override
	public void init(IBall context) {
		for (APaintStrategy paintStrategy : this.paintStrategies) {
			paintStrategy.init(context);
		}
	}

	@Override
	public void paintTransform(Graphics g, IBall context, AffineTransform at) {
		for (APaintStrategy paintStrategy : this.paintStrategies) {
			paintStrategy.paintTransform(g, context, at);
		}
	}

}
