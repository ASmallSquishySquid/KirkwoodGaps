package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.strategies.criteria.CompositeCriteriaStrategy;
import model.strategies.criteria.ICriteriaStrategy;
import model.strategies.interact.CompositeInteractStrategy;
import model.strategies.interact.IInteractStrategy;
import model.strategies.paint.IPaintStrategy;
import model.strategies.update.IUpdateStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import model.visitors.cmds.IBallCmd;
import provided.logger.ILogger;
import provided.logger.ILoggerControl;

/**
 * Abstract implementation of a ball configuration algorithm.
 * 
 * @author Phoebe Scaccia
 */
public abstract class AConfigBallAlgo extends BallAlgo<Void, Void> implements IConfigBallAlgo {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -7752690986786102917L;

	/**
	 * The adapter to the model in use.
	 */
	protected IBallAlgo2ModelAdapter algo2ModelAdpt = IBallAlgo2ModelAdapter.NULL;

	/**
	 * The logger in use
	 */
	protected ILogger logger = ILoggerControl.getSharedLogger();

	/**
	 * Constructs a configuration algorithm with a no-op default case and the given adapter to the model.
	 * Use this constructor when the default case command needs to reference instance fields and methods of 
	 * the configuration algorithm.   In that scenario, setDefaultCmd() must be explicitly called to 
	 * override the default no-op behavior of the default case.
	 * @param logger The logger to use
	 * @param algo2ModelAdpt The adapter to the model.
	 */
	public AConfigBallAlgo(ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt) {
		this(logger, algo2ModelAdpt, ABallAlgoCmd.MakeNull());
	}

	/**
	 * Constructs a configuration algorithm with a no-op default case and the given adapter to the model.
	 * Use this constructor when the default case command needs to reference instance fields and methods of 
	 * the configuration algorithm.   In that scenario, setDefaultCmd() must be explicitly called to 
	 * override the default no-op behavior of the default case.
	 * @param logger The logger to use
	 * @param name A friendly name for the toString() method to return.
	 * @param algo2ModelAdpt The adapter to the model.
	 */
	public AConfigBallAlgo(ILogger logger, String name, IBallAlgo2ModelAdapter algo2ModelAdpt) {
		this(logger, name, algo2ModelAdpt, ABallAlgoCmd.MakeNull());
	}

	/**
	 * Constructs a configuration algorithm with the given default case and the given adapter to the model.
	 * @param logger The logger to use
	 * @param algo2ModelAdpt adapter to the model.
	 * @param defaultCmd The default case command
	 */
	public AConfigBallAlgo(ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt, ABallAlgoCmd<Void, Void> defaultCmd) {
		super(defaultCmd);
		this.algo2ModelAdpt = algo2ModelAdpt;
		this.logger = logger;
	}

	/**
	 * Constructs a configuration algorithm with the given default case and the given adapter to the model.
	 * @param logger The logger to use
	 * @param name A friendly name for the toString() method to return.
	 * @param algo2ModelAdpt adapter to the model.
	 * @param defaultCmd The default case command
	 */
	public AConfigBallAlgo(ILogger logger, String name, IBallAlgo2ModelAdapter algo2ModelAdpt,
			ABallAlgoCmd<Void, Void> defaultCmd) {
		super(name, defaultCmd);
		this.algo2ModelAdpt = algo2ModelAdpt;
		this.logger = logger;
	}

	/**
	 * Accessor for the adapter to the model
	 * @return The adapter to the model
	 */
	protected IBallAlgo2ModelAdapter getAlgo2ModelAdpt() {
		return algo2ModelAdpt;
	}

	/**
	 * Convenience method to install an update strategy into the given host ball.
	 * @param host  The host ball to install the given update strategy into
	 * @param newStrat The update strategy to install into the host ball
	 */
	protected void installUpdateStrategy(IBall host, IUpdateStrategy newStrat) {
		// Make composite with existing strategy
		host.setUpdateStrategy(newStrat);
	}

	/**
	 * Convenience method to install a paint strategy into the given host ball.
	 * @param host  The host ball to install the given paint strategy into
	 * @param newStrat The paint strategy to install into the host ball
	 */
	protected void installPaintStrategy(IBall host, IPaintStrategy newStrat) {
		// Make composite with existing strategy
		host.setPaintStrategy(newStrat);
	}

	/**
	 * Convenience method to install a paint strategy into the given host ball.
	 * @param host  The host ball to install the given paint strategy into
	 * @param newStrat The paint strategy to install into the host ball
	 */
	protected void installCriteriaStrategy(IBall host, ICriteriaStrategy newStrat) {
		// Make composite with existing strategy
		host.setCriteriaStrategy(new CompositeCriteriaStrategy(host.getCriteriaStrategy(), newStrat));
	}

	/**
	 * Convenience method to install an interact strategy into the given host ball.
	 * @param host  The host ball to install the given interact strategy into
	 * @param newStrat The interact strategy to install into the host ball
	 */
	protected void installInteractStrategy(IBall host, IInteractStrategy<IBallCmd> newStrat) {
		// Make composite with existing strategy
		host.setInteractStrategy(new CompositeInteractStrategy(host.getInteractStrategy(), newStrat));
	}
}