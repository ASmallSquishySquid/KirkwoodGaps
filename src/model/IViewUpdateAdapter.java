package model;

/**
 * Interface that goes from the model to the view that enables the model to talk to the view.
 */
public interface IViewUpdateAdapter {

	/**
	 * The method that tells the view to update
	 */
	public void update();

	/**
	 * Default null object for the adapter.
	 */
	public static final IViewUpdateAdapter NULL_OBJECT = new IViewUpdateAdapter() {

		public void update() {
		}

	};

}