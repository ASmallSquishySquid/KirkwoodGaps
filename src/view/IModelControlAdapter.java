package view;

/**
 * The view to model control adapter.
 * @param <TDropListItem1> : the type of options in the primary drop lists.
 * @param <TDropListItem2> the type of options in the secondary drop lists.
 */
public interface IModelControlAdapter<TDropListItem1, TDropListItem2> {

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
	public TDropListItem1 addPaintStrategy(String classname);

	/**
	 * Adds an update strategy to the drop lists.
	 *
	 * @param classname the class name of the strategy
	 * @return an object to add to the drop lists
	 */
	public TDropListItem1 addUpdateStrategy(String classname);
	
	/**
	 * Adds a criteria strategy to the drop lists.
	 *
	 * @param classname the class name of the strategy
	 * @return an object to add to the drop lists
	 */
	public TDropListItem1 addCriteriaStrategy(String classname);
		
	/**
	 * Adds an interact strategy to the drop lists.
	 *
	 * @param classname the class name of the strategy
	 * @return an object to add to the drop lists
	 */
	public TDropListItem1 addInteractStrategy(String classname);
	
	/**
	 * Adds a ball type to the drop lists.
	 *
	 * @param typename the name of the ball type
	 * @return an object to add to the drop lists
	 */
	public TDropListItem2 addBallType(String typename);

	/**
	 * Adds a switcher ball to the screen.
	 * 
	 * @param selectedItem the selected ball type
	 */
	public void makeSwitcherBall(TDropListItem2 selectedItem);

	/**
	 * Tells the model to clear the balls.
	 */
	public void clearBalls();

	/**
	 * Tells the model to make a ABall with strategy selectedItem.
	 * @param selectedItem1 an object from the ball type drop list.
	 * @param selectedItem2 an object from the drop down list
	 */
	public void makeBall(TDropListItem2 selectedItem1, TDropListItem1 selectedItem2);

	/**
	 * Return a new object to put on both lists, given 2 items from the list.
	 * @param selectedItem1 : an object from one drop list
	 * @param selectedItem2 : an object from the other drop list
	 * @return an object to put back on both lists.
	 */
	public TDropListItem1 combineStrategies(TDropListItem1 selectedItem1, TDropListItem1 selectedItem2);

	/**
	 * Makes all Switcher Balls switch their strategies to selectedItem.
	 * @param selectedItem : an object from the top drop list.
	 */
	public void switchStrategy(TDropListItem1 selectedItem);

	/**
	 * Default null object for the adapter.
	 * @param <TDropListItem1> the type of options in the algo lists
	 * @param <TDropListItem2> the type of options in the ball type list
	 * @return an instance of the adapter that does nothing.
	 */
	public static <TDropListItem1, TDropListItem2> IModelControlAdapter<TDropListItem1, TDropListItem2> MAKE_NULL() {
		return new IModelControlAdapter<TDropListItem1, TDropListItem2>() {
			@Override
			public TDropListItem1 addUpdateStrategy(String classname) {
				return null;
			}

			@Override
			public void makeSwitcherBall(TDropListItem2 selectedItem) {
			}

			@Override
			public void clearBalls() {
			}

			@Override
			public void makeBall(TDropListItem2 selectedItem1, TDropListItem1 selectedItem2) {
			}

			@Override
			public TDropListItem1 combineStrategies(TDropListItem1 selectedItem1, TDropListItem1 selectedItem2) {
				return null;
			}

			@Override
			public void switchStrategy(TDropListItem1 selectedItem) {
			}

			@Override
			public TDropListItem1 addPaintStrategy(String classname) {
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
			public TDropListItem1 addInteractStrategy(String classname) {
				return null;
			}

			@Override
			public String getDefaultCriteriaStrategy() {
				return null;
			}

			@Override
			public TDropListItem1 addCriteriaStrategy(String classname) {
				return null;
			}

			@Override
			public TDropListItem2 addBallType(String typename) {
				return null;
			}
		};
	}


}
