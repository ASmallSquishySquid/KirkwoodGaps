package model;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.function.Supplier;

import javax.swing.JComponent;
import javax.swing.Timer;

import model.adapters.IBallAlgo2ModelAdapter;
import model.adapters.IModel2BallAdapter;
import model.adapters.IViewControlAdapter;
import model.adapters.IViewUpdateAdapter;
import model.balls.ErrorBall;
import model.balls.IBall;
import model.balls.IBallFactory;
import model.balls.JupiterBall;
import model.balls.MarsBall;
import model.balls.SunBall;
import model.strategies.criteria.ErrorCriteriaStrategy;
import model.strategies.criteria.ICriteriaStrategy;
import model.strategies.interact.ErrorInteractStrategy;
import model.strategies.interact.IInteractStrategy;
import model.strategies.paint.ErrorPaintStrategy;
import model.strategies.paint.IPaintStrategy;
import model.strategies.update.ErrorUpdateStrategy;
import model.strategies.update.IUpdateStrategy;
import model.visitors.algos.AConfigBallAlgo;
import model.visitors.algos.CompositeConfigBallAlgo;
import model.visitors.algos.ConfigCriteriaBallAlgo;
import model.visitors.algos.ConfigInteractBallAlgo;
import model.visitors.algos.ConfigPaintBallAlgo;
import model.visitors.algos.ConfigUpdateBallAlgo;
import model.visitors.algos.ErrorConfigBallAlgo;
import model.visitors.cmds.IBallCmd;
import provided.logger.ILoggerControl;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.dispatcher.IObserver;
import provided.utils.dispatcher.impl.SequentialDispatcher;
import provided.utils.displayModel.IATImage;
import provided.utils.loader.IObjectLoader;
import provided.utils.loader.impl.ObjectLoader;

/**
 * The BallModel class.
 */
public class BallModel {
	/**
	 * The time, in milliseconds, that the GUI updates.
	 */
	private int timeSlice = 20;
	
	/**
	 * The IObjectLoader object which loads in paint strategies.
	 */
	private IObjectLoader<IPaintStrategy> paintStrategyLoader = new ObjectLoader<IPaintStrategy>(
			(attempt, args) -> new ErrorPaintStrategy());
	/**
	 * The IObjectLoader object which loads in update strategies.
	 */
	private IObjectLoader<IUpdateStrategy> updateStrategyLoader = new ObjectLoader<IUpdateStrategy>(
			(attempt, args) -> new ErrorUpdateStrategy());

	/**
	 * The IObjectLoader object which loads in criteria strategies.
	 */
	private IObjectLoader<ICriteriaStrategy> criteriaStrategyLoader = new ObjectLoader<ICriteriaStrategy>(
			(attempt, args) -> new ErrorCriteriaStrategy());

	/**
	 * The IObjectLoader object which loads in interact strategies.
	 */
	private IObjectLoader<IInteractStrategy<IBallCmd>> interactStrategyLoader = new ObjectLoader<IInteractStrategy<IBallCmd>>(
			(attempt, args) -> new ErrorInteractStrategy());

	/**
	 * The IObjectLoader object which loads in criteria strategies.
	 */
	private IObjectLoader<AConfigBallAlgo> configAlgoLoader = new ObjectLoader<AConfigBallAlgo>(
			(attempt, args) -> new ErrorConfigBallAlgo());

	/**
	 * The IObjectLoader object which loads in ball types.
	 */
	private IObjectLoader<IBall> ballTypeLoader = new ObjectLoader<IBall>(
			(attempt, args) -> new ErrorBall(0, 0, 0, 0, null, null));

	/**
	 * The IDispatcher whose IObservers are ABall objects.
	 */
	private IDispatcher<IBallCmd> ballDispatcher = new SequentialDispatcher<IBallCmd>();
	/**
	 * The model to view update adapter created by the controller.
	 */
	private IViewUpdateAdapter viewUpdtAdpt = IViewUpdateAdapter.NULL_OBJECT;
	/**
	 * The model to view control adapter created by the controller.
	 */
	private IViewControlAdapter viewCtrlAdpt = IViewControlAdapter.NULL_OBJECT;
	/**
	 * The timer managing the update time.
	 */
	private Timer timer = new Timer(timeSlice, (e) -> viewUpdtAdpt.update());

	/**
	 * Constructor for the BallModel.
	 * @param viewCtrlAdpt : the model to view control adapter.
	 * @param viewUpdtAdpt : the model to view update adapter.
	 */
	public BallModel(IViewControlAdapter viewCtrlAdpt, IViewUpdateAdapter viewUpdtAdpt) {
		this.viewUpdtAdpt = viewUpdtAdpt;
		this.viewCtrlAdpt = viewCtrlAdpt;
	}

	/**
	 * Starts the timer.
	 */
	public void start() {
		this.timer.start();
	}

	/**
	 * Clears all Balls from the dispatcher.
	 */
	public void clearBalls() {
		ballDispatcher.removeAllObservers();
	}

	/**
	 * Loads a ABall with a strategy from the given strategy factory.
	 * 
	 * @param ballFactory a ball factory
	 * @param ballAlgo an algorithm to configure the ball
	 */
	public void loadBall(IBallFactory ballFactory, AConfigBallAlgo ballAlgo) {
		
		IObserver<IBallCmd> mars = new MarsBall(viewCtrlAdpt.getCanvas(), new IModel2BallAdapter() {
			@Override
			public IATImage getImageWrapper(Image image) {
				return viewCtrlAdpt.getIATImage(image);
			}
		});
		
		ballDispatcher.addObserver(mars);
		
		IObserver<IBallCmd> sun = new SunBall(viewCtrlAdpt.getCanvas(), new IModel2BallAdapter() {
			@Override
			public IATImage getImageWrapper(Image image) {
				return viewCtrlAdpt.getIATImage(image);
			}
		});
		
		ballDispatcher.addObserver(sun);
		
		IObserver<IBallCmd> jupiter = new JupiterBall(viewCtrlAdpt.getCanvas(), new IModel2BallAdapter() {
			@Override
			public IATImage getImageWrapper(Image image) {
				return viewCtrlAdpt.getIATImage(image);
			}
		});
		
		ballDispatcher.addObserver(jupiter);
	}

	/**
	 * Updates all that ABalls that are observers.
	 * @param g the Graphics object for the view.
	 */
	public void update(Graphics g) {
		ballDispatcher.updateAll(new IBallCmd() {

			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				context.move();
				context.interact(disp);
				context.updateState(disp);
				context.paint(g);
			}

		});
	}

	/**
	 * Returns an IBallAlgo that can instantiate the strategy specified by
	 * <code>classname</code> and install it into the host ball by composing it with any
	 * existing update strategy in the ball.
	 * Installs an error strategy if <code>classname</code> is null or other error occurs
	 * during the loading process.
	 * The toString() of the returned algorithm is the given <code>classname</code>.
	 *
	 * @param classname Shortened name of desired strategy
	 * @return An algorithm to install the specified strategy
	 */
	public AConfigBallAlgo makeUpdateStrategyAlgo(final String classname) {
		return new ConfigUpdateBallAlgo(classname, loadUpdateStrategy(fixUpdateName(classname)));
	}

	/**
	 * Returns an IBallAlgo that can instantiate the strategy specified by
	 * <code>classname</code> and install it into the host ball by composing it with any
	 * existing paint strategy in the ball.
	 * Installs an error strategy if <code>classname</code> is null or other error occurs
	 * during the loading process.
	 * The toString() of the returned algorithm is the given <code>classname</code>.
	 *
	 * @param classname Shortened name of desired strategy
	 * @return An algorithm to install the specified strategy
	 */
	public AConfigBallAlgo makePaintStrategyAlgo(final String classname) {
		return new ConfigPaintBallAlgo(classname, loadPaintStrategy(fixPaintName(classname)));
	}

	/**
	 * Returns an IBallAlgo that can instantiate the strategy specified by
	 * <code>classname</code> and install it into the host ball by composing it with any
	 * existing criteria strategy in the ball.
	 * Installs an error strategy if <code>classname</code> is null or other error occurs
	 * during the loading process.
	 * The toString() of the returned algorithm is the given <code>classname</code>.
	 *
	 * @param classname Shortened name of desired strategy
	 * @return An algorithm to install the specified strategy
	 */
	public AConfigBallAlgo makeCriteriaStrategyAlgo(final String classname) {
		return new ConfigCriteriaBallAlgo(classname, loadCriteriaStrategy(fixCriteriaName(classname)));
	}

	/**
	 * Returns an IBallAlgo that can instantiate the strategy specified by
	 * <code>classname</code> and install it into the host ball by composing it with any
	 * existing interact strategy in the ball.
	 * Installs an error strategy if <code>classname</code> is null or other error occurs
	 * during the loading process.
	 * The toString() of the returned algorithm is the given <code>classname</code>.
	 *
	 * @param classname Shortened name of desired strategy
	 * @return An algorithm to install the specified strategy
	 */
	public AConfigBallAlgo makeInteractStrategyAlgo(final String classname) {
		return new ConfigInteractBallAlgo(classname, loadInteractStrategy(fixInteractName(classname)));
	}

	/**
	 * Returns an AConfigBallAlgo specified by
	 * <code>classname</code> and install it into the host ball by composing it with any
	 * existing interact strategy in the ball.
	 * Installs an error strategy if <code>classname</code> is null or other error occurs
	 * during the loading process.
	 * The toString() of the returned algorithm is the given <code>classname</code>.
	 *
	 * @param classname Shortened name of desired AConfigBallAlgo
	 * @return An AConfigBallAlgo
	 */
	public AConfigBallAlgo makeConfigBallAlgo(final String classname) {
		return loadConfigAlgo(classname);
	}

	/**
	 * Returns an IBall factory specified by
	 * <code>classname</code> and install it into the host ball by composing it with any
	 * existing interact strategy in the ball.
	 * Installs an error strategy if <code>classname</code> is null or other error occurs
	 * during the loading process.
	 * The toString() of the returned algorithm is the given <code>classname</code>.
	 *
	 * @param classname Shortened name of desired AConfigBallAlgo
	 * @return An IBallFactory
	 */
	public IBallFactory makeBallFactory(final String classname) {
		return new IBallFactory() {
			
			@Override
			public IBall make(double distance, double angle, int radius, double mass, Component container,
					AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter) {
				return loadBallType(fixBallTypeName(classname), distance, angle, radius, mass, container, installAlgo, modelAdapter);
			}

			@Override
			public String toString() {
				return classname;
			}
		};
	}

	/**
	 *  Returns a composite IBallAlgo that can instantiate a composition with the two
	 * strategies made by the two given IUpdateStrategyFac objects. Returns null if
	 * either supplied factory is null. The <code>toString()</code> of the returned factory
	 * is the <code>toString()</code>s of the two given factories, concatenated with "-".
	 * If either given algo is null, then an algo that installs an error strategy is returned.
	 *
	 * @param ballAlgo1 A ball processing algorithm
	 * @param ballAlgo2 Another ball processing algorithm
	 * @return An IBallAlgo that is a composition of the two algorithms
	 */
	public AConfigBallAlgo combineStrategyAlgos(final AConfigBallAlgo ballAlgo1, final AConfigBallAlgo ballAlgo2) {
		if (ballAlgo1 == null || ballAlgo2 == null) {
			return this.makeUpdateStrategyAlgo("Error");
		} else {
			return new CompositeConfigBallAlgo(ballAlgo1.toString() + "-" + ballAlgo2.toString(), ballAlgo1, ballAlgo2);
		}
	}

	/**
	 * @return the default ball type.
	 */
	public String getDefaultBallType() {
		return "Default";
	}

	/**
	 * @return the default ball strategy.
	 */
	public String getDefaultPaintStrategy() {
		return "Ball";
	}

	/**
	 * @return the default update strategy.
	 */
	public String getDefaultUpdateStrategy() {
		return "Straight";
	}

	/**
	 * @return the default criteria strategy.
	 */
	public String getDefaultCriteriaStrategy() {
		return "Collision";
	}

	/**
	 * @return the default interact strategy.
	 */
	public String getDefaultInteractStrategy() {
		return "Elastic";
	}

	/**
	 * @return the default configuration algorithm.
	 */
	public String getDefaultConfigAlgo() {
		return "Physics";
	}

	/**
	 * A helper function that adds the paint package name as a prefix to a class name.
	 *
	 * @param classname : the abbreviated form of a paint strategy.
	 * @return the fully-qualified name of the paint strategy.
	 */
	private String fixPaintName(Object classname) {
		return "model.strategies.paint." + classname + "Strategy";
	}

	/**
	 * A helper function that adds the update package name as a prefix to a class name.
	 *
	 * @param classname : the abbreviated form of an update strategy.
	 * @return the fully-qualified name of the update strategy.
	 */
	private String fixUpdateName(Object classname) {
		return "model.strategies.update." + classname + "Strategy";
	}

	/**
	 * A helper function that adds the criteria package name as a prefix to a class name.
	 *
	 * @param classname : the abbreviated form of a criteria strategy.
	 * @return the fully-qualified name of the criteria strategy.
	 */
	private String fixCriteriaName(Object classname) {
		return "model.strategies.criteria." + classname + "Strategy";
	}

	/**
	 * A helper function that adds the interact package name as a prefix to a class name.
	 *
	 * @param classname : the abbreviated form of an interact strategy.
	 * @return the fully-qualified name of the interact strategy.
	 */
	private String fixInteractName(Object classname) {
		return "model.strategies.interact." + classname + "Strategy";
	}

	/**
	 * A helper function that adds the algo package name as a prefix to a class name.
	 *
	 * @param classname : the abbreviated form of a config algo strategy.
	 * @return the fully-qualified name of the config algo strategy.
	 */
	private String fixConfigAlgoName(Object classname) {
		return "model.visitors.algos.Config" + classname + "BallAlgo";
	}

	/**
	 * A helper function that adds the balls package name as a prefix to a class name.
	 *
	 * @param classname : the abbreviated form of a ball type.
	 * @return the fully-qualified name of a ball type.
	 */
	private String fixBallTypeName(Object classname) {
		return "model.balls." + classname + "Ball";
	}

	/**
	 * A helper function that loads a new paint strategy in.
	 *
	 * @param name the fully-qualified name of the paint strategy to load
	 * @return a paint strategy
	 */
	private IPaintStrategy loadPaintStrategy(String name) {
		return this.paintStrategyLoader.loadInstance(name);
	}

	/**
	 * A helper function that loads a new update strategy in.
	 *
	 * @param name the fully-qualified name of the update strategy to load
	 * @return an update strategy
	 */
	private IUpdateStrategy loadUpdateStrategy(String name) {
		return this.updateStrategyLoader.loadInstance(name);
	}

	/**
	 * A helper function that loads a new criteria strategy in.
	 *
	 * @param name the fully-qualified name of the criteria strategy to load
	 * @return a criteria strategy
	 */
	private ICriteriaStrategy loadCriteriaStrategy(String name) {
		return this.criteriaStrategyLoader.loadInstance(name);
	}

	/**
	 * A helper function that loads a new interact strategy in.
	 *
	 * @param name the fully-qualified name of the interact strategy to load
	 * @return an interact strategy
	 */
	private IInteractStrategy<IBallCmd> loadInteractStrategy(String name) {
		return this.interactStrategyLoader.loadInstance(name);
	}

	/**
	 * A helper function that loads a new ball type.
	 *
	 * @param name the fully-qualified name of the ball type to load
	 * @param args the constructor arguments for the ball
	 * @return an IBall
	 */
	private IBall loadBallType(String name, Object... args) {
		return this.ballTypeLoader.loadInstance(name, args);
	}

	/**
	 * A helper function that loads a new config algo in.
	 *
	 * @param name the fully-qualified name of the config algo to load
	 * @return a config algo
	 */
	private AConfigBallAlgo loadConfigAlgo(String name) {
		return this.configAlgoLoader.loadInstance(fixConfigAlgoName(name), name, ILoggerControl.getSharedLogger(),
				new IBallAlgo2ModelAdapter() {

					@Override
					public void addConfigComponent(String label, Supplier<JComponent> compFac) {
						viewCtrlAdpt.addConfigComponent(label, compFac);
					}
				});
	}
}
