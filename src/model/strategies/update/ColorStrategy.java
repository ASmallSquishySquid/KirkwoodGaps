package model.strategies.update;

import java.awt.Color;
import java.util.Random;
import java.util.function.Supplier;

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
 * Can be disabled.
 * 
 * @author Annita Chang
 * @author Phoebe Scaccia
 */
public class ColorStrategy implements IUpdateStrategy {
	/**
	 * A Random object used to generate random color.
	 */
	Random rand = new Random();
	
	/**
	 * The accessor for whether this strategy is enabled or not.
	 */
	private Supplier<Boolean> isEnabled;
	
	/**
	 * Constructor for a new ColorStrategy.
	 */
	public ColorStrategy() {
		super();
		this.isEnabled = () -> true;
	}
	
	/**
	 * Constructor for a new ColorStrategy that can be disabled.
	 * 
	 * @param isEnabled a Supplier for a Boolean
	 */
	public ColorStrategy(Supplier<Boolean> isEnabled) {
		super();
		this.isEnabled = isEnabled;
	}
	
	@Override
	public void init(IBall context) {
		return;
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

			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -4188164342674429676L;

			// Add additional commands in the "initializer block" of the ball algo's anonymous inner class
			{
				// Add different behavior for PreyBalls
				setCmd(PreyBall.id, new ABallAlgoCmd<Void, Void>() {
					/**
					 * For serialization.
					 */
					private static final long serialVersionUID = -5382338068484350811L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						if (isEnabled.get()) {
							context.setColor(new Color(255, rand.nextInt(100)+100, rand.nextInt(100)));
						}
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
						if (isEnabled.get()) {
							context.setColor(new Color(rand.nextInt(100)+100, rand.nextInt(100), 255));
						}
						return null;
					}
				});
				// Add different behavior for ScavengerBalls
				setCmd(ScavengerBall.id, new ABallAlgoCmd<Void, Void>() {

					private static final long serialVersionUID = 6992176773986147329L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						if (isEnabled.get()) {
							context.setColor(new Color(rand.nextInt(100)+100, 255, rand.nextInt(100)));
						}
						return null;					}
				});
				
				// etc.
			}
	});
	}

}
