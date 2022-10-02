package model.strategies.update;

import java.awt.Color;

import model.balls.IBall;
import model.balls.PredatorBall;
import model.balls.PreyBall;
import model.balls.ScavengerBall;
import model.visitors.algos.BallAlgo;
import model.visitors.cmds.ABallAlgoCmd;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;

/**
 * @author Annita Chang
 *
 */
public class ColorStrategy implements IUpdateStrategy {

	@Override
	public void init(IBall context) {

	}

	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
		context.execute(new BallAlgo<Void, Void>(new ABallAlgoCmd<>() {
			// Add generated serialVersionUID
			private static final long serialVersionUID = -7896815473719026153L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// no-op by default
				return null;
			}
			
		}) {

			private static final long serialVersionUID = -4188164342674429676L;

			// Add generated serialVersionUID

			// Add additional commands in the "initializer block" of the ball algo's anonymous inner class
			{
				// Add different behavior for PreyBalls
				setCmd(PreyBall.id, new ABallAlgoCmd<Void, Void>() {
					private static final long serialVersionUID = -5382338068484350811L;

					// Add generated serialVersionUID
					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						context.setColor(Color.red);
						ILoggerControl.getSharedLogger().log(LogLevel.INFO, "Changed Prey balls to red.");
						return null;
					}
				});
			
			}
			
			{
				// Add different behavior for PredatorBalls

				setCmd(PredatorBall.id, new ABallAlgoCmd<Void, Void>() {

					private static final long serialVersionUID = 8419413408181856648L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						context.setColor(Color.blue);
						ILoggerControl.getSharedLogger().log(LogLevel.INFO, "Changed Predator balls to blue.");
						return null;
					}
				});
				// Add different behavior for ScavengerBalls
				setCmd(ScavengerBall.id, new ABallAlgoCmd<Void, Void>() {

					private static final long serialVersionUID = 6992176773986147329L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						context.setColor(Color.green);
						ILoggerControl.getSharedLogger().log(LogLevel.INFO, "Changed Scavenger balls to green.");
						return null;					}
				});
				
				// etc.
			}
	});
	}

}
