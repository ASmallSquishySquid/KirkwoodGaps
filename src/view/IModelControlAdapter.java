package view;

/**
 * The view to model control adapter.
 */
public interface IModelControlAdapter {

	/**
	 * Tells the model to clear the balls.
	 */
	public void clearBalls();

	/**
	 * Tells the model to make a ABall with strategy selectedItem.
	 */
	public void makeBalls();

	/**
	 * Default null object for the adapter.
	 * @return an instance of the adapter that does nothing.
	 */
	public static IModelControlAdapter MAKE_NULL() {
		return new IModelControlAdapter() {

			@Override
			public void clearBalls() {
			}

			@Override
			public void makeBalls() {
			}
		};
	}

}
