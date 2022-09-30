package model.strategies.update;

import java.awt.Color;

import model.ABall;
import model.IBall;
import model.visitors.cmds.IBallCmd;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * A strategy for a ball that fades a little every time it bounces.
 *
 * @author Phoebe Scaccia
 */
public class FadingStrategy implements IUpdateStrategy {

	@Override
	public void init(IBall context) {
		return;
	}
	
	/**
	 * Updates the transparency of the ball and reflects the ball off the wall.
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		if (didBounce) {
			int fade = Randomizer.Singleton.randomInt(10, 60);
			this.updateTransparency(context, fade);
			if (context.getColor().getAlpha() == 0) {
				context.getLogger().log(LogLevel.INFO, "It faded away :(");
				dispatcher.removeObserver((ABall) context);
			}
		}
	}

	/**
	 * Updates the transparency of the ball <code>context</code> to be <code>amount</code> lower than it previously was.
	 *
	 * @param context the ball to update
	 * @param amount the amount by which to decrease the transparency
	 */
	private void updateTransparency(IBall context, int amount) {
		Color color = context.getColor();
		context.setColor(
				new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(color.getAlpha() - amount, 0)));
	}

}
