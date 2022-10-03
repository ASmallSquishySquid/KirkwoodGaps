package model.strategies.update;

import java.awt.Color;
import java.util.Random;

import model.balls.IBall;
import model.balls.PredatorBall;
import model.balls.PreyBall;
import model.balls.ScavengerBall;
import model.visitors.algos.BallAlgo;
import model.visitors.cmds.ABallAlgoCmd;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.utils.dispatcher.IDispatcher;

/**
 * Flashes different shades of colors every 15 frame counts, based on ball type: orange for prey, purple for predator, and green for scavenger.
 * @author Annita Chang
 *
 */
public class ColorStrategy implements IUpdateStrategy {
	/**
	 * An integer keeping track of the frame update counts.
	 */
	int count = 0;
	/**
	 * A Random object used to generate random color.
	 */
	Random rand = new Random();
	
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
						count += 1;
						if (count == 10) {
							context.setColor(new Color(255, rand.nextInt(100)+100, rand.nextInt(100)));
							count = 0;
						} 
						//ILoggerControl.getSharedLogger().log(LogLevel.INFO, "Changed Prey balls to red.");
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
						count += 1;
						if (count == 10) {
							context.setColor(new Color(rand.nextInt(100)+100, rand.nextInt(100), 255));
							count = 0;
						} 
						//ILoggerControl.getSharedLogger().log(LogLevel.INFO, "Changed Predator balls to blue.");
						return null;
					}
				});
				// Add different behavior for ScavengerBalls
				setCmd(ScavengerBall.id, new ABallAlgoCmd<Void, Void>() {

					private static final long serialVersionUID = 6992176773986147329L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						count += 1;
						if (count == 10) {
							context.setColor(new Color(rand.nextInt(100)+100, 255, rand.nextInt(100)));
							count = 0;
						} 
						//ILoggerControl.getSharedLogger().log(LogLevel.INFO, "Changed Scavenger balls to green.");
						return null;					}
				});
				
				// etc.
			}
	});
	}

}
