package model.adapters;

import java.util.function.Supplier;

import javax.swing.JComponent;

/**
 * The adapter from the ball algos to the model
 * 
 * @author Phoebe Scaccia
 */
public interface IBallAlgo2ModelAdapter {
	/**
	 * The null adapter.
	 */
	public static final IBallAlgo2ModelAdapter NULL = new IBallAlgo2ModelAdapter() {

		@Override
		public void addConfigComponent(String label, Supplier<JComponent> compFac) {
			return;
		}
	};

	/**
	 * Adds a configuration pane.
	 *
	 * @param label the name of the pane
	 * @param compFac the JComponent to add
	 */
	public void addConfigComponent(String label, Supplier<JComponent> compFac);
}