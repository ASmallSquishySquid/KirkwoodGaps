package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Timer;

import model.strategies.criteria.CompositeCriteriaStrategy;
import model.strategies.criteria.ErrorCriteriaStrategy;
import model.strategies.criteria.ICriteriaStrategy;
import model.strategies.interact.CompositeInteractStrategy;
import model.strategies.interact.ErrorInteractStrategy;
import model.strategies.interact.IInteractStrategy;
import model.strategies.paint.BallStrategy;
import model.strategies.paint.CompositePaintStrategy;
import model.strategies.paint.ErrorPaintStrategy;
import model.strategies.paint.IPaintStrategy;
import model.strategies.update.CompositeUpdateStrategy;
import model.strategies.update.ErrorUpdateStrategy;
import model.strategies.update.IUpdateStrategy;
import model.strategies.update.StraightStrategy;
import provided.ballworld.extVisitors.IBallHostID;
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
	private BallAlgo<Void, Void> clearStrategy = new BallAlgo<>(new ABallAlgoCmd<Void, Void>() {
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
	});
	/**
	 * The dummy switcher ball that hold all the strategies.
	 */
	private DefaultBall switcherBall = new DefaultBall(new Point(), 0, new Point(), null, null, new BallAlgo<>(new ABallAlgoCmd<Void, Void>() {
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
	}), new IModel2BallAdapter() {
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
	private BallAlgo<Void, Void> switcherInstallAlgo = new BallAlgo<>(new ABallAlgoCmd<Void, Void>() {
		/**
		 * For serialization.
		 */
		private static final long serialVersionUID = 3997292467495661508L;

		@Override
		public Void apply(IBallHostID index, IBall context, Void... params) {
			context.setUpdateStrategy(switcherUpdateStrategy);
			context.setPaintStrategy(switcherPaintStrategy);
			context.setCriteriaStrategy(switcherCriteriaStrategy);
			context.setInteractStrategy(switcherInteractStrategy);
			return null;
		}	
	});
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
	 * The IObjectLoader object which loads in update strategies.
	 */
	private IObjectLoader<ICriteriaStrategy> criteriaStrategyLoader = new ObjectLoader<ICriteriaStrategy>(
			(attempt, args) -> new ErrorCriteriaStrategy());
	
	/**
	 * The IObjectLoader object which loads in interact strategies.
	 */
	private IObjectLoader<IInteractStrategy<IBallCmd>> interactStrategyLoader = new ObjectLoader<IInteractStrategy<IBallCmd>>(
			(attempt, args) -> new ErrorInteractStrategy());
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
	 * @param ballAlgo an algorithm to configure the ball
	 */
	public void loadBall(IBallAlgo<Void, Void> ballAlgo) {
		if (ballAlgo == null) {
			ballAlgo = new BallAlgo<>(new ABallAlgoCmd<Void, Void>() {
				/**
				 * For serialization.
				 */
				private static final long serialVersionUID = 621278619163413741L;

				@Override
				public Void apply(IBallHostID index, IBall context, Void... params) {
					context.setUpdateStrategy(new ErrorUpdateStrategy());
					return null;
				}
			});
		}
		
		// Generate a new ball with random parameters.

		IRandomizer r = Randomizer.Singleton;

		Point position = r.randomLoc(viewCtrlAdpt.getCanvas().getSize());
		int radius = r.randomInt(minRadius, maxRadius);
		Point velocity = r.randomVel(new Rectangle(maxSpeed, maxSpeed));
		Color color = r.randomColor();

		IObserver<IBallCmd> ball = new DefaultBall(position, radius, velocity, color, viewCtrlAdpt.getCanvas(), ballAlgo,
				new IModel2BallAdapter() {
					@Override
					public IATImage getImageWrapper(Image image) {
						return viewCtrlAdpt.getIATImage(image);
					}
				});
		ballDispatcher.addObserver(ball);
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
	public IBallAlgo<Void, Void> makeUpdateStrategyAlgo(final String classname) {
		return new BallAlgo<>(classname, new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 6696640655546320160L;

			@Override
			public Void apply(IBallHostID index, IBall context, Void... params) {
				context.setUpdateStrategy(new CompositeUpdateStrategy(context.getUpdateStrategy(),
						loadUpdateStrategy(fixUpdateName(classname))));
				return null;
			}
		});
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
	public IBallAlgo<Void, Void> makePaintStrategyAlgo(final String classname) {
		return new BallAlgo<>(classname, new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 6696640655546320160L;

			@Override
			public Void apply(IBallHostID index, IBall context, Void... params) {
				context.setPaintStrategy(new CompositePaintStrategy(context.getPaintStrategy(),
						loadPaintStrategy(fixPaintName(classname))));
				return null;
			}
		});
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
	public IBallAlgo<Void, Void> makeCriteriaStrategyAlgo(final String classname) {
		return new BallAlgo<>(classname, new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 4090602791233660336L;

			@Override
			public Void apply(IBallHostID index, IBall context, Void... params) {
				context.setCriteriaStrategy(new CompositeCriteriaStrategy(context.getCriteriaStrategy(),
						loadCriteriaStrategy(fixCriteriaName(classname))));
				return null;
			}
		});
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
	public IBallAlgo<Void, Void> makeInteractStrategyAlgo(final String classname) {
		return new BallAlgo<>(classname, new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -7629858473454208289L;

			@Override
			public Void apply(IBallHostID index, IBall context, Void... params) {
				context.setInteractStrategy(new CompositeInteractStrategy(context.getInteractStrategy(),
						loadInteractStrategy(fixInteractName(classname))));
				return null;
			}
		});
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
	public IBallAlgo<Void, Void> combineStrategyAlgos(final IBallAlgo<Void, Void> ballAlgo1, final IBallAlgo<Void, Void> ballAlgo2) {
		if (ballAlgo1 == null || ballAlgo2 == null) {
			return this.makeUpdateStrategyAlgo("Error");
		} else {
			return new BallAlgo<>(ballAlgo1.toString() + "-" + ballAlgo2.toString(), new ABallAlgoCmd<Void, Void>() {
				/**
				 * For serialization.
				 */
				private static final long serialVersionUID = -7539908168085002601L;

				@Override
				public Void apply(IBallHostID index, IBall context, Void... params) {
					context.execute(ballAlgo1);
					context.execute(ballAlgo2);
					return null;
				}
			});
		}
	}

	/**
	 * @return the switcherInstallAlgo
	 */
	public IBallAlgo<Void, Void> getSwitcherInstallAlgo() {
		return switcherInstallAlgo;
	}

	/**
	 * Switch the strategies of all the switcher balls.
	 *
	 * @param newAlgo a new set of strategies to use
	 */
	public void switchStrategy(IBallAlgo<Void, Void> newAlgo) {
		this.switcherBall.execute(clearStrategy);
		this.switcherBall.execute(newAlgo);
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
	 * @param classname : the abbreviated form of an update strategy.
	 * @return the fully-qualified name of the update strategy.
	 */
	private String fixCriteriaName(Object classname) {
		return "model.strategies.criteria." + classname + "Strategy";
	}
	
	/**
	 * A helper function that adds the interact package name as a prefix to a class name.
	 *
	 * @param classname : the abbreviated form of an update strategy.
	 * @return the fully-qualified name of the update strategy.
	 */
	private String fixInteractName(Object classname) {
		return "model.strategies.interact." + classname + "Strategy";
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
	 * @param name the fully-qualified name of the paint strategy to load
	 * @return an update strategy
	 */
	private IUpdateStrategy loadUpdateStrategy(String name) {
		return this.updateStrategyLoader.loadInstance(name);
	}
	
	/**
	 * A helper function that loads a new criteria strategy in.
	 *
	 * @param name the fully-qualified name of the paint strategy to load
	 * @return an update strategy
	 */
	private ICriteriaStrategy loadCriteriaStrategy(String name) {
		return this.criteriaStrategyLoader.loadInstance(name);
	}
	
	/**
	 * A helper function that loads a new interact strategy in.
	 *
	 * @param name the fully-qualified name of the paint strategy to load
	 * @return an update strategy
	 */
	private IInteractStrategy<IBallCmd> loadInteractStrategy(String name) {
		return this.interactStrategyLoader.loadInstance(name);
	}
}
