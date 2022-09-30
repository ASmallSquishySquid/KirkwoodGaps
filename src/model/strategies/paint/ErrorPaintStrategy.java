/**
 * 
 */
package model.strategies.paint;

import java.awt.Graphics;

import model.balls.IBall;
import model.strategies.update.ErrorUpdateStrategy;

/**
 * A strategy for handling erroneous paints.
 * 
 * @author Phoebe Scaccia
 */
public class ErrorPaintStrategy implements IPaintStrategy {

	/**
	 * Set's the ball's update strategy to be the error strategy.
	 */
	@Override
	public void init(IBall context) {
		context.setUpdateStrategy(new ErrorUpdateStrategy());
	}

	@Override
	public void paint(Graphics g, IBall context) {
		return;
	}

}
