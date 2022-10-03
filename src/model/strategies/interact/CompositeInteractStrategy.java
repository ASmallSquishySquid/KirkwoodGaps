package model.strategies.interact;

import model.balls.IBall;
import model.visitors.cmds.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * An interact strategy that combines two interact strategies.
 * 
 * @author Michael Cuenca
 * @author Phoebe Scaccia
 */
public class CompositeInteractStrategy implements IInteractStrategy<IBallCmd> {

	/**
	 * The first IInteractStrategy
	 */
	IInteractStrategy<IBallCmd> interactStrat1;

	/**
	 * The second IInteractStrategy
	 */
	IInteractStrategy<IBallCmd> interactStrat2;

	/**
	 * @param strat1 an IInteractStrategy
	 * @param strat2 an IInteractStrategy
	 */
	public CompositeInteractStrategy(IInteractStrategy<IBallCmd> strat1, IInteractStrategy<IBallCmd> strat2) {
		this.interactStrat1 = strat1;
		this.interactStrat2 = strat2;
	}

	@Override
	public void init(IBall context) {
		interactStrat1.init(context);
		interactStrat2.init(context);
	}

	@Override
	public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> disp) {

		// Get strategy commands.

		IBallCmd interactStrat1Cmd = interactStrat1.interact(context, target, disp);
		IBallCmd interactStrat2Cmd = interactStrat2.interact(context, target, disp);

		return new IBallCmd() {

			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				if (interactStrat1Cmd != null) {
					interactStrat1Cmd.apply(context, disp);
				}
				if (interactStrat2Cmd != null) {
					interactStrat2Cmd.apply(context, disp);
				}
			}
		};
	}

}
