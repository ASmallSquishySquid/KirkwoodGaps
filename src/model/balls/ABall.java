package model.balls;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;

import model.Constants;
import model.adapters.IModel2BallAdapter;
import model.strategies.criteria.ICriteriaStrategy;
import model.strategies.interact.IInteractStrategy;
import model.strategies.paint.IPaintStrategy;
import model.strategies.update.IUpdateStrategy;
import model.strategies.update.StraightStrategy;
import model.visitors.algos.AConfigBallAlgo;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.ABallHost;
import provided.logger.ILogger;
import provided.logger.ILoggerControl;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.displayModel.IATImage;

/**
 * Class for a ABall object.
 *
 * @author Phoebe Scaccia
 */
public abstract class ABall extends ABallHost<IBall> implements IBall {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -5595019792800389696L;
	
	/**
	 * The ABall's position.
	 */
	private Point2D.Double pos;
	
	/**
	 * The ABall's radius.
	 */
	private double radius;
	
	/**
	 * The mass of the object.
	 */
	private double mass;
	
	/**
	 * The ABall's velocity.
	 */
	private Point2D.Double vel;
	
	/**
	 * The Component the ABall is in.
	 */
	private Component container;
	
	/**
	 * The ABall's paint strategy.
	 */
	private IPaintStrategy paintStrategy = new IPaintStrategy() {

		@Override
		public void paint(Graphics g, IBall context) {
			return;
		}

		@Override
		public void init(IBall context) {
			return;
		}
	};
	/**
	 * The ABall's update strategy.
	 */
	private IUpdateStrategy updateStrategy = new StraightStrategy();

	/**
	 * The ball's criteria strategy.
	 */
	private ICriteriaStrategy criteriaStrategy = new ICriteriaStrategy() {

		@Override
		public boolean satisfied(IBall context, IBall target) {
			return false;
		}

		@Override
		public void init(IBall context) {
			return;
		}
	};

	/**
	 * The ABall's interact strategy.
	 */
	private IInteractStrategy<IBallCmd> interactStrategy = new IInteractStrategy<IBallCmd>() {

		@Override
		public IBallCmd interact(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
			return new IBallCmd() {

				@Override
				public void apply(IBall context, IDispatcher<IBallCmd> disp) {
					return;
				}
			};
		}

		@Override
		public void init(IBall context) {
			return;
		}
	};

	/**
	 * The logger to be used by all the strategies.
	 */
	private ILogger logger = ILoggerControl.getSharedLogger();
	
	/**
	 * The adapter to the model.
	 */
	private IModel2BallAdapter modelAdapter;

	/**
	 * Constructor for an ABall.
	 * 
	 * @param id the subclass ID
	 * @param distance the ABall's position.
	 * @param angle the starting angle around the sun in radians
	 * @param radius the ABall's radius.
	 * @param mass the mass of this ball
	 * @param container the ABall's container.
	 * @param installAlgo The algo to complete the installation of strategies and any other desired operations
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	protected ABall(IBallHostID id, double distance, double angle, double radius, double mass, Component container, AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter) {
		super(id);
		this.pos = Constants.calculateStartingPosition(distance, angle);
		this.vel = Constants.calculateVelocity(distance, angle);
		this.radius = Constants.calculateRadius(radius);
		this.mass = mass;
		this.container = container;
		this.modelAdapter = modelAdapter;
		this.execute(installAlgo);
	}

	@Override
	public void update(IDispatcher<IBallCmd> disp, IBallCmd cmd) {
		cmd.apply(this, disp);
	}

	/**
	 * @return the ABall's location.
	 */
	@Override
	public Point2D.Double getLocation() {
		return new Point2D.Double(this.pos.x, this.pos.y);
	}

	/**
	 * @param pos : the ABall's new location.
	 */
	@Override
	public void setLocation(Point2D.Double pos) {
		this.pos = pos;
	}

	/**
	 * @return the ABall's radius.
	 */
	@Override
	public double getRadius() {
		return this.radius;
	}

	/**
	 * @param radius : the ABall's new radius.
	 */
	@Override
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * @return the ABall's velocity.
	 */
	@Override
	public Point2D.Double getVelocity() {
		return new Point2D.Double(this.vel.x, this.vel.y);
	}

	/**
	 * @param velocity : the ABall's new velocity.
	 */
	@Override
	public void setVelocity(Point2D.Double velocity) {
		this.vel = velocity;
	}

	/**
	 * @return the paintStrategy
	 */
	@Override
	public IPaintStrategy getPaintStrategy() {
		return paintStrategy;
	}

	/**
	 * @param paintStrategy the paintStrategy to set
	 */
	@Override
	public void setPaintStrategy(IPaintStrategy paintStrategy) {
		this.paintStrategy = paintStrategy;
		this.paintStrategy.init(this);
	}

	/**
	 * @return the ABall's strategy.
	 */
	@Override
	public IUpdateStrategy getUpdateStrategy() {
		return this.updateStrategy;
	}

	/**
	 * @param strategy : the ABall's new strategy.
	 */
	@Override
	public void setUpdateStrategy(IUpdateStrategy strategy) {
		this.updateStrategy = strategy;
		this.updateStrategy.init(this);
	}

	@Override
	public ICriteriaStrategy getCriteriaStrategy() {
		return this.criteriaStrategy;
	}

	@Override
	public void setCriteriaStrategy(ICriteriaStrategy strategy) {
		this.criteriaStrategy = strategy;
		this.criteriaStrategy.init(this);
	}

	@Override
	public IInteractStrategy<IBallCmd> getInteractStrategy() {
		return this.interactStrategy;
	}

	@Override
	public void setInteractStrategy(IInteractStrategy<IBallCmd> strategy) {
		this.interactStrategy = strategy;
		this.interactStrategy.init(this);
	}

	/**
	 * @return the logger
	 */
	@Override
	public ILogger getLogger() {
		return logger;
	}

	/**
	 * @return the container
	 */
	@Override
	public Component getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	@Override
	public void setContainer(Component container) {
		this.container = container;
	}

	@Override
	public IModel2BallAdapter getAdapter() {
		return this.modelAdapter;
	}

	public void move() {
		this.pos.x += this.vel.x;
		this.pos.y += this.vel.y;
	}

	public void updateState(IDispatcher<IBallCmd> dispatcher) {
		this.updateStrategy.updateState(this, dispatcher);
	}

	public void paint(Graphics g) {
		this.paintStrategy.paint(g, this);
	}

	public void interact(IDispatcher<IBallCmd> dispatcher) {
		IBall context = this;

		dispatcher.updateAll(new IBallCmd() {
			@Override
			public void apply(IBall other, IDispatcher<IBallCmd> disp) {
				IBallCmd contextPostInteractCmd = null;
//				IBallCmd otherPostInteractCmd = null;

				if (context.getCriteriaStrategy().satisfied(context, other)) {
					// Have the balls interact based on their interact strategies

					contextPostInteractCmd = context.interactWith(other, disp);
				}

//				if (other.getCriteriaStrategy().satisfied(other, context)) {
//					// Have the balls interact based on their interact strategies
//					otherPostInteractCmd = other.interactWith(context, disp);
//				}

				// Apply the interactions
				if (contextPostInteractCmd != null) {
					context.update(dispatcher, contextPostInteractCmd);
				}
//				if (otherPostInteractCmd != null) {
//					other.update(dispatcher, otherPostInteractCmd);
//				}
			}
		});
	}

	@Override
	public IATImage getIatImage(Image image) {
		return modelAdapter.getImageWrapper(image);
	}

	@Override
	public IBallCmd interactWith(IBall target, IDispatcher<IBallCmd> disp) {
		return this.getInteractStrategy().interact(this, target, disp);
	}

	@Override
	public double getMass() {
		return mass;
	}

	@Override
	public void setMass(double mass) {
		this.mass = mass;
	}

}