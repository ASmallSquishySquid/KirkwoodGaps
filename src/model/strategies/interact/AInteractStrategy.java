package model.strategies.interact;

import model.balls.IBall;
import model.visitors.cmds.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * An abstract implmenting IInteractStrategy.
 * @author Annita Chang
 *
 */
public abstract class AInteractStrategy implements IInteractStrategy<IBallCmd> {

	@Override
	public void init(IBall context) {
	}

	@Override
	public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> dispatcher) {
		return null;
	}

}
