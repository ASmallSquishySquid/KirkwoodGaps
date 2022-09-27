package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;

import model.strategies.criteria.ICriteriaStrategy;
import model.strategies.interact.IInteractStrategy;
import model.strategies.paint.IPaintStrategy;
import model.strategies.update.IUpdateStrategy;
import provided.logger.ILogger;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.displayModel.IATImage;

/**
 * The interface for a ball
 *
 * @author Phoebe Scaccia
 */
public interface IBall {

	/**
	 * Updates the Ball's status.
	 * 
	 * @param disp the IDispatcher of the Ball.
	 * @param cmd a command to apply to the Ball
	 */
	void update(IDispatcher<IBallCmd> disp, IBallCmd cmd);

	/**
	 * Moves the ball according to its velocity.
	 */
	public void move();

	/**
	 * Determines if a ball should bounce. If it should, set its position and velocity accordingly.
	 *
	 * @return true if the ball bounced, false otherwise.
	 */
	public boolean bounce();

	/**
	 * Updates the state of the ball.
	 *
	 * @param dispatcher the dispatcher holding this ball
	 * @param didBounce whether the ball just bounced or not
	 */
	public void updateState(IDispatcher<IBallCmd> dispatcher, boolean didBounce);

	/**
	 * Paints the Ball.
	 *
	 * @param g the Graphics object to paint on.
	 */
	public void paint(Graphics g);
	
	/**
	 * Has the Ball interact with other balls.
	 *
	 * @param dispatcher the dispatcher.
	 */
	public void interact(IDispatcher<IBallCmd> dispatcher);

	/**
	 * Execute the given algorithm
	 *
	 * @param algo The algorithm to execute
	 */
	public void execute(IBallAlgo algo);

	/**
	 * @return the Ball's location.
	 */
	Point2D.Double getLocation();

	/**
	 * @param pos : the Ball's new location.
	 */
	void setLocation(Point2D.Double pos);

	/**
	 * @return the Ball's radius.
	 */
	int getRadius();

	/**
	 * @param radius : the Ball's new radius.
	 */
	void setRadius(int radius);

	/**
	 * @return the Ball's velocity.
	 */
	Point2D.Double getVelocity();

	/**
	 * @param velocity : the Ball's new velocity.
	 */
	void setVelocity(Point2D.Double velocity);

	/**
	 * @return the Ball's color.
	 */
	Color getColor();

	/**
	 * @param color : the Ball's new color.
	 */
	void setColor(Color color);

	/**
	 * @return the Ball's paint strategy.
	 */
	IPaintStrategy getPaintStrategy();

	/**
	 * @param strategy : the Ball's new paint strategy.
	 */
	void setPaintStrategy(IPaintStrategy strategy);

	/**
	 * @return the Ball's update strategy.
	 */
	IUpdateStrategy getUpdateStrategy();

	/**
	 * @param strategy : the Ball's new update strategy.
	 */
	void setUpdateStrategy(IUpdateStrategy strategy);
	
	/**
	 * @return the Ball's criteria strategy.
	 */
	ICriteriaStrategy getCriteriaStrategy();

	/**
	 * @param strategy : the Ball's new criteria strategy.
	 */
	void setCriteriaStrategy(ICriteriaStrategy strategy);
	
	/**
	 * @return the Ball's strategy.
	 */
	IInteractStrategy<IBallCmd> getInteractStrategy();

	/**
	 * @param strategy : the Ball's new strategy.
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