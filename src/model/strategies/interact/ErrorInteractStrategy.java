package model.strategies.interact;

import model.IBall;
import model.IBallCmd;
import model.strategies.update.ErrorUpdateStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * A strategy for dealing with interact strategy errors.
 * 
 * @author Phoebe Scaccia
 */
public class ErrorInteractStrategy implements IInteractStrategy<IBallCmd> {

	@Override
	public void init(IBall context) {
		context.setUpdateStrategy(new ErrorUpdateStrategy());
	}

	@Override
	public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> dispatcher) {
		return null;
	}

}
