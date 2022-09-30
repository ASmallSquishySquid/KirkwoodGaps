package model.strategies.update;

import java.awt.Graphics;

import model.balls.ABall;
import model.balls.IBall;
import model.strategies.criteria.ICriteriaStrategy;
import model.strategies.paint.IPaintStrategy;
import model.visitors.cmds.IBallCmd;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;

/**
 * A strategy for dealing with errors.
 *
 * @author Phoebe Scaccia
 */
public class ErrorUpdateStrategy implements IUpdateStrategy {

	/**
	 * The number of messages to print out before disappearing.
	 */
	private int messages = 4;

	/**
	 * The number of updates since the last error message was printed.
	 */
	private int updates = 50;

	/**
	 * Remove the ball as much as possible from the rest of the environment.
	 */
	@Override
	public void init(IBall context) {
		context.setRadius(0);
		
		context.setCriteriaStrategy(new ICriteriaStrategy() {
			
			@Override
			public boolean satisfied(IBall context, IBall target) {
				return false;
			}
			
			@Override
			public void init(IBall context) {
				return;
			}
		});
		
		context.setPaintStrategy(new IPaintStrategy() {
			
			@Override
			public void paint(Graphics g, IBall context) {
				return;
			}
			
			@Override
			public void init(IBall context) {
				return;
			}
		});
	}
	
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		if (updates == 0) {
			context.getLogger().log(LogLevel.INFO, "THAT IS NOT A BALL!");
			java.awt.Toolkit.getDefaultToolkit().beep();
			this.messages--;
			updates = 50;
		} else {
			updates--;
		}
		if (this.messages == 0) {
			context.getLogger().log(LogLevel.INFO, "ErrorBall removed.");
			dispatcher.removeObserver((ABall) context);
		}
	}	

}