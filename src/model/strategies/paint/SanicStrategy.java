package model.strategies.paint;

import model.balls.IBall;
import provided.logger.LogLevel;

/**
 * Paints Sanic :)
 *
 * @author Phoebe Scaccia
 */
public class SanicStrategy extends UprightImagePaintStrategy {
	/**
	 * Constructor for a new SanicStrategy.
	 */
	public SanicStrategy() {
		super("images/Sanic.gif", 1);
	}

	@Override
	public void init(IBall context) {
		super.init(context);
		context.getLogger().log(LogLevel.INFO, "Gotta go FAST!");
	}

}
