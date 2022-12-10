package model.balls;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;

import model.adapters.IModel2BallAdapter;
import model.strategies.criteria.ICriteriaStrategy;
import model.strategies.interact.IInteractStrategy;
import model.strategies.paint.IPaintStrategy;
import model.strategies.update.IUpdateStrategy;
import model.visitors.cmds.IBallCmd;
import provided.ballworld.extVisitors.IBallHost;
import provided.logger.ILogger;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.dispatcher.IObserver;
import provided.utils.displayModel.IATImage;

/**
 * The interface for a ball
 *
 * @author Phoebe Scaccia
 */
public interface IBall extends IObserver<IBallCmd>, IBallHost<IBall> {

	/**
	 * Updates the ABall's status.
	 * 
	 * @param disp the IDispatcher of the ABall.
	 * @param cmd a command to apply to the ABall
	 */
	void update(IDispatcher<IBallCmd> disp, IBallCmd cmd);

	/**
	 * Moves the ball according to its velocity.
	 */
	public void move();

	/**
	 * Updates the state of the ball.
	 *
	 * @param dispatcher the dispatcher holding this ball
	 */
	public void updateState(IDispatcher<IBallCmd> dispatcher);

	/**
	 * Paints the ABall.
	 *
	 * @param g the Graphics object to paint on.
	 */
	public void paint(Graphics g);

	/**
	 * Has the ABall interact with other balls.
	 *
	 * @param dispatcher the dispatcher.
	 */
	public void interact(IDispatcher<IBallCmd> dispatcher);

	/**
	 * @return the ABall's location.
	 */
	Point2D.Double getLocation();

	/**
	 * @param pos : the ABall's new location.
	 */
	void setLocation(Point2D.Double pos);

	/**
	 * @return the ABall's radius.
	 */
	int getRadius();

	/**
	 * @param radius : the ABall's new radius.
	 */
	void setRadius(int radius);
	
	/**
	 * @return the mass
	 */
	public double getMass();

	/**
	 * @param mass the mass to set
	 */
	public void setMass(double mass);

	/**
	 * @return the ABall's velocity.
	 */
	Point2D.Double getVelocity();

	/**
	 * @param velocity : the ABall's new velocity.
	 */
	void setVelocity(Point2D.Double velocity);

	/**
	 * @return the ABall's paint strategy.
	 */
	IPaintStrategy getPaintStrategy();

	/**
	 * @param strategy : the ABall's new paint strategy.
	 */
	void setPaintStrategy(IPaintStrategy strategy);

	/**
	 * @return the ABall's update strategy.
	 */
	IUpdateStrategy getUpdateStrategy();

	/**
	 * @param strategy : the ABall's new update strategy.
	 */
	void setUpdateStrategy(IUpdateStrategy strategy);

	/**
	 * @return the ABall's criteria strategy.
	 */
	ICriteriaStrategy getCriteriaStrategy();

	/**
	 * @param strategy : the ABall's new criteria strategy.
	 */
	void setCriteriaStrategy(ICriteriaStrategy strategy);

	/**
	 * @return the ABall's strategy.
	 */
	IInteractStrategy<IBallCmd> getInteractStrategy();

	/**
	 * @param strategy : the ABall's new strategy.
	 */
	void setInteractStrategy(IInteractStrategy<IBallCmd> strategy);

	/**
	 * @return the logger
	 */
	ILogger getLogger();

	/**
	 * @return the container
	 */
	Component getContainer();

	/**
	 * @param container the container to set
	 */
	void setContainer(Component container);

	/**
	 * @return the ball's adapter to the model
	 */
	IModel2BallAdapter getAdapter();

	/**
	 * Gets an IATImage for the given Image.
	 *
	 * @param image an Image object
	 * @return an IATImage object
	 */
	public IATImage getIatImage(Image image);

	/**
	 * Returns the result of an interaction with the target
	 *
	 * @param target an IBall
	 * @param disp the dispatcher
	 * @return an IBallCmd
	 */
	IBallCmd interactWith(IBall target, IDispatcher<IBallCmd> disp);

}