package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.function.Supplier;

import javax.swing.JComponent;
import javax.swing.Timer;

import model.adapters.IBallAlgo2ModelAdapter;
import model.adapters.IModel2BallAdapter;
import model.adapters.IViewControlAdapter;
import model.adapters.IViewUpdateAdapter;
import model.balls.DefaultBall;
import model.balls.ErrorBall;
import model.balls.IBall;
import model.balls.IBallFactory;
import model.strategies.criteria.ErrorCriteriaStrategy;
import model.strategies.criteria.ICriteriaStrategy;
import model.strategies.interact.ErrorInteractStrategy;
import model.strategies.interact.IInteractStrategy;
import model.strategies.paint.BallStrategy;
import model.strategies.paint.ErrorPaintStrategy;
import model.strategies.paint.IPaintStrategy;
import model.strategies.update.ErrorUpdateStrategy;
import model.strategies.update.IUpdateStrategy;
import model.strategies.update.StraightStrategy;
import model.visitors.algos.AConfigBallAlgo;
import model.visitors.algos.CompositeConfigBallAlgo;
import model.visitors.algos.ConfigCriteriaBallAlgo;
import model.visitors.algos.ConfigInteractBallAlgo;
import model.visitors.algos.ConfigPaintBallAlgo;
import model.visitors.algos.ConfigUpdateBallAlgo;
import model.visitors.algos.ErrorConfigBallAlgo;
import model.visitors.cmds.ABallAlgoCmd;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.dispatcher.IObserver;
import provided.utils.dispatcher.impl.SequentialDispatcher;
import provided.utils.displayModel.IATImage;
import provided.utils.loader.IObjectLoader;
import provided.utils.loader.impl.ObjectLoader;
import provided.utils.valueGenerator.IRandomizer;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * The BallModel class.
 */
public class BallModel {

	/**
	 * The maximum radius of a ABall allowed.
	 */
	private int maxRadius = 30;
	/**
	 * THe minimum radius of a ABall allowed.
	 */
	private int minRadius = 5;
	/**
	 * The maximum speed allowed for a ABall.
	 */
	private int maxSpeed = 10;
	/**
	 * The time, in milliseconds, that the GUI updates.
	 */
	private int timeSlice = 20;
	/**
	 * A strategy that clears the current strategy.
	 */
	private AConfigBallAlgo clearStrategy = new AConfigBallAlgo(ILoggerControl.getSharedLogger(), IBallAlgo2ModelAdapter.NULL, new ABallAlgoCmd<Void, Void>() {
		/**
		 * For serialization.
		 */
		private static final long serialVersionUID = -2452545543095265330L;

		@Override
		public Void apply(IBallHostID index, IBall context, Void... params) {
			context.setPaintStrategy(new IPaintStrategy() {

				@Override
				public void paint(Graphics g, IBall context) {
					return;
				}

				@Override
				public void init(IBall context) {
					return;
				}
			});
			
			context.setUpdateStrategy(new StraightStrategy());
			
			context.setCriteriaStrategy(new ICriteriaStrategy() {
				
				@Override
				public boolean satisfied(IBall context, IBall target) {
					return false;
				}
				
				@Override
				public void init(IBall context) {
					return;
				}
			});
			
			context.setInteractStrategy(new IInteractStrategy<IBallCmd>() {
				
				@Override
				public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> dispatcher) {
					return null;
				}
				
				@Override
				public void init(IBall context) {
					return;
				}
			});
			
			return null;
		}
	}) {

		/**
		 * For serialization.
		 */
		private static final long serialVersionUID = 1L;
		
	};
	/**
	 * The dummy switcher ball that hold all the strategies.
	 */
	private DefaultBall switcherBall = new DefaultBall(new Point(), 0, new Point(), null, null, 
			new AConfigBallAlgo(ILoggerControl.getSharedLogger(), IBallAlgo2ModelAdapter.NULL, new ABallAlgoCmd<Void, Void>() {
				/**
				 * For serialization.
				 */
				private static final long serialVersionUID = -8185269178029656500L;
		
				@Override
				public Void apply(IBallHostID index, IBall context, Void... params) {
					context.execute(clearStrategy);
					context.setPaintStrategy(new BallStrategy());
					return null;
				}
			}) {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -1340622403010369255L;
		}, new IModel2BallAdapter() {
			@Override
			public IATImage getImageWrapper(Image image) {
				return viewCtrlAdpt.getIATImage(image);
			}
		});
	/**
	 * The one switcher update strategy instance in the system. Allows all balls made with this strategy to be controlled at once.
	 */
	private IUpdateStrategy switcherUpdateStrategy = new IUpdateStrategy() {
		
		@Override
		public void init(IBall context) {
			switcherBall.getUpdateStrategy().init(context);
		}
		
		@Override
		public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher, boolean didBounce) {
			// delegate to the strategy in the dummy ball
			switcherBall.getUpdateStrategy().updateState(context, dispatcher, didBounce);
		}
	};
	/**
	 * The one switcher paint strategy instance in the system. Allows all balls made with this strategy to be controlled at once.
	 */
	private IPaintStrategy switcherPaintStrategy = new IPaintStrategy() {

		@Override
		public void paint(Graphics g, IBall host) {
			// Delegate to the strategy in the dummy ball
			switcherBall.getPaintStrategy().paint(g, host);
		}

		@Override
		public void init(IBall context) {
			switcherBall.getPaintStrategy().init(context);
		}
	};
	/**
	 * The one switcher paint strategy instance in the system. Allows all balls made with this strategy to be controlled at once.
	 */
	private ICriteriaStrategy switcherCriteriaStrategy = new ICriteriaStrategy() {
		
		@Override
		public boolean satisfied(IBall context, IBall target) {
			return switcherBall.getCriteriaStrategy().satisfied(context, target);
		}
		
		@Override
		public void init(IBall context) {
			switcherBall.getCriteriaStrategy().init(context);
		}
	};
	/**
	 * The one switcher interact strategy instance in the system. Allows all balls made with this strategy to be controlled at once.
	 */
	private IInteractStrategy<IBallCmd> switcherInteractStrategy = new IInteractStrategy<IBallCmd>() {
		
		@Override
		public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> dispatcher) {
			return switcherBall.getInteractStrategy().interact(context, target, dispatcher);
		}
		
		@Override
		public void init(IBall context) {
			switcherBall.getInteractStrategy().init(context);			
		}
	};
	/**
	 * The algorithm to build a new switcher ball.
	 */
	private AConfigBallAlgo switcherInstallAlgo = new CompositeConfigBallAlgo( 
			new ConfigUpdateBallAlgo(null, switcherUpdateStrategy), 
			new ConfigPaintBallAlgo(null, switcherPaintStrategy),
			new ConfigInteractBallAlgo(null, switcherInteractStrategy), 
			new ConfigCriteriaBallAlgo(null, switcherCriteriaStrategy));
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
			(attempt, args) -> new ErrorBall(new Point(0, 0), 0, new Point(0, 0), null, null, null, null));
	
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
		if (ballAlgo == null) {
			ballAlgo = new ConfigUpdateBallAlgo(null, new ErrorUpdateStrategy());
		}
		
		if (ballFactory == null) {
			ballFactory = IBallFactory.defaultBallFactory;
		}
		
		// Generate a new ball with random parameters.

		IRandomizer r = Randomizer.Singleton;

		Point position = r.randomLoc(viewCtrlAdpt.getCanvas().getSize());
		int radius = r.randomInt(minRadius, maxRadius);
		Point velocity = r.randomVel(new Rectangle(maxSpeed, maxSpeed));
		Color color = r.randomColor();

		IObserver<IBallCmd> ball = ballFactory.make(position, radius, velocity, color, viewCtrlAdpt.getCanvas(), ballAlgo,
				new IModel2BallAdapter() {
			@Override
			public IATImage getImageWrapper(Image image) {
				return viewCtrlAdpt.getIATImage(image);
			}
		});
		ballDispatcher.addObserver(ball);
	}
	
	/**
	 * Loads a new switcher ball.
	 * 
	 * @param ballFactory a ball factory
	 */
	public void loadSwitcherBall(IBallFactory ballFactory) {
		if (ballFactory == null) {
			ballFactory = IBallFactory.defaultBallFactory;
		}
		loadBall(ballFactory, switcherInstallAlgo);
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
				boolean didBounce = context.bounce();
				context.updateState(disp, didBounce);
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
			public IBall make(Point p, int r, Point v, Color c, Component container, AConfigBallAlgo installAlgo,
					IModel2BallAdapter modelAdapter) {
				return loadBallType(fixBallTypeName(classname), p, r, v, c, container, installAlgo, modelAdapter);
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
	 * @return the switcherInstallAlgo
	 */
	public AConfigBallAlgo getSwitcherInstallAlgo() {
		return switcherInstallAlgo;
	}

	/**
	 * Switch the strategies of all the switcher balls.
	 *
	 * @param newAlgo a new set of strategies to use
	 */
	public void switchStrategy(AConfigBallAlgo newAlgo) {
		this.switcherBall.execute(clearStrategy);
		this.switcherBall.execute(newAlgo);
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
		return this.configAlgoLoader.loadInstance(fixConfigAlgoName(name), name, ILoggerControl.getSharedLogger(), new IBallAlgo2ModelAdapter() {
			
			@Override
			public void addConfigComponent(String label, Supplier<JComponent> compFac) {
				viewCtrlAdpt.addConfigComponent(label, compFac);
			}
		});
	}
}
