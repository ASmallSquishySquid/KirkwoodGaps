package model.adapters;

import java.awt.Component;
import java.awt.Image;
import java.util.function.Supplier;

import javax.swing.JComponent;

import provided.utils.displayModel.IATImage;

/**
 * The model to view control adapter.
 */
public interface IViewControlAdapter {

	/**
	 * @return the GUI canvas.
	 */
	public Component getCanvas();

	/**
	 * Return an IATImage that wraps the given Image object 
	 * and the ball's canvas from the view.
	 * 
	 * @param image The Image object to imbed in the IATImage instance
	 * @return An IATImage instance
	 */
	public IATImage getIATImage(Image image);
	
	/**
	 * Adds a configuration component to the view.
	 *
	 * @param label the name of the component
	 * @param compFac the compnent factory
	 */
	public void addConfigComponent(String label, Supplier<JComponent> compFac);

	/**
	 * Default null object for the adapter.
	 */
	public static final IViewControlAdapter NULL_OBJECT = new IViewControlAdapter() {

		public Component getCanvas() {
			return null;
		}

		public IATImage getIATImage(Image image) {
			return null;
		}

		@Override
		public void addConfigComponent(String label, Supplier<JComponent> compFac) {
			return;
		}

	};

}
