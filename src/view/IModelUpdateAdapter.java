package view;

import java.awt.Graphics;

/**
 * View to model adapter to update the screen.
 */
public interface IModelUpdateAdapter {

	/**
	 * Tells the model to update the screen.
	 * @param g : the Graphics object to update.
	 */
	public void update(Graphics g);

	/**
	 * Default null object for the adapter.
	 */
	public static final IModelUpdateAdapter NULL_OBJECT = new IModelUpdateAdapter() {
		public void update(Graphics g) {
		}
	};

}
