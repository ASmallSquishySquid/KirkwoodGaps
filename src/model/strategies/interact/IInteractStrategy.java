package model.strategies.interact;


import model.IBall;
import provided.utils.dispatcher.IDispatcher;

/**
 * @author michaelcuenca
 * @author Phoebe Scaccia
 * 
 * Interface for ball interactions
 * @param <IBallCmd> An IBallCmd
 */
public interface IInteractStrategy<IBallCmd> {
	
	/**
	 * Null strategy with no-op behavior.
	 */
	public static final IInteractStrategy<model.IBallCmd> NULL_STRATEGY = new IInteractStrategy<model.IBallCmd>() {

		@Override
		/**
		 * Null init method
		 */
		public void init(IBall context) {}

		@Override
		public model.IBallCmd interact(IBall context, IBall target, IDispatcher<model.IBallCmd> dispatcher) {
			return null;
		}
		
	};

	/**
	 * Sets up the interact strategy.
	 * 
	 * @param context a Ball object
	 */
	public void init(IBall context);
	
	/**
	 * Handles the interactions between the context and target
	 * 
	 * @param context a Ball object
	 * @param target a Ball object that is not the context
	 * @param dispatcher the dispatcher
	 * 
	 * @return an IBallCmd
	 */
	public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> dispatcher);
	
}
