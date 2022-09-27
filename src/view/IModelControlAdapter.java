package view;

/**
 * The view to model control adapter.
 * @param <TDropListItem> : the type of options in the drop lists.
 */
public interface IModelControlAdapter<TDropListItem> {

	/**
	 * @return the default paint strategy
	 */
	public String getDefaultPaintStrategy();

	/**
	 * @return the default update strategy
	 */
	public String getDefaultUpdateStrategy();
	
	/**
	 * @return the default update strategy
	 */
	public String getDefaultCriteriaStrategy();
	
	/**
	 * @return the default update strategy
	 */
	public String getDefaultInteractStrategy();

	/**
	 * Adds a paint strategy to the drop lists.
	 *
	 * @param classname the class name of the strategy
	 * @return an object to add to the drop lists
	 */
	public TDropListItem addPaintStrategy(String classname);

	/**
	 * Adds an update strategy to the drop lists.
	 *
	 * @param classname the class name of the strategy
	 * @return an object to add to the drop lists
	 */
	public TDropListItem addUpdateStrategy(String classname);
	
	/**
	 * Adds a criteria strategy to the drop lists.
	 *
	 * @param classname the class name of the strategy
	 * @return an object to add to the drop lists
	 */
	public TDropListItem addCriteriaStrategy(String classname);
		
	/**
	 * Adds an interact strategy to the drop lists.
	 *
	 * @param classname the class name of the strategy
	 * @return an object to add to the drop lists
	 */
	public TDropListItem addInteractStrategy(String classname);

	/**
	 * Adds a switcher ball to the screen.
	 */
	public void makeSwitcherBall();

	/**
	 * Tells the model to clear the balls.
	 */
	public void clearBalls();

	/**
	 * Tells the model to make a Ball with strategy selectedItem.
	 * @param selectedItem : an object from the top drop list.
	 */
	public void makeBall(TDropListItem selectedItem);

	/**
	 * Return a new object to put on both lists, given 2 items from the list.
	 * @param selectedItem1 : an object from one drop list
	 * @param selectedItem2 : an object from the other drop list
	 * @return an object to put back on both lists.
	 */
	public TDropListItem combineStrategies(TDropListItem selectedItem1, TDropListItem selectedItem2);

	/**
	 * Makes all Switcher Balls switch their strategies to selectedItem.
	 * @param selectedItem : an object from the top drop list.
	 */
	public void switchStrategy(TDropListItem selectedItem);

	/**
	 * Default null object for the adapter.
	 * @param <TDropListItem> the type of options in the drop lists.
	 * @return an instance of the adapter that does nothing.
	 */
	public static <TDropListItem> IModelControlAdapter<TDropListItem> MAKE_NULL() {
		return new IModelControlAdapter<TDropListItem>() {
			@Override
			public TDropListItem addUpdateStrategy(String classname) {
				return null;
			}

			@Override
			public void makeSwitcherBall() {
			}

			@Override
			public void clearBalls() {
			}

			@Override
			public void makeBall(TDropListItem selectedItem) {
			}

			@Override
			public TDropListItem combineStrategies(TDropListItem selectedItem1, TDropListItem selectedItem2) {
				return null;
			}

			@Override
			public void switchStrategy(TDropListItem selectedItem) {
			}

			@Override
			public TDropListItem addPaintStrategy(String classname) {
				return null;
			}

			@Override
			public String getDefaultPaintStrategy() {
				return null;
			}

			@Override
			public String getDefaultUpdateStrategy() {
				return null;
			}

			@Override
			public String getDefaultInteractStrategy() {
				return null;
			}

			@Override
			public TDropListItem addInteractStrategy(String classname) {
				return null;
			}

			@Override
			public String getDefaultCriteriaStrategy() {
				return null;
			}

			@Override
			public TDropListItem addCriteriaStrategy(String classname) {
				return null;
			}
		};
	}

}
