package model.balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import model.adapters.IModel2BallAdapter;
import model.visitors.algos.AConfigBallAlgo;

/**
 * A factory interface for balls.
 * 
 * @author Phoebe Scaccia
 */
public interface IBallFactory {
	/**
	 * Makes a new ball.
	 *
	 * @param p the position.
	 * @param r the radius.
	 * @param v the velocity.
	 * @param c the color.
	 * @param container the container.
	 * @param installAlgo The algo to complete the installation of strategies and any other desired operations
	 * @param modelAdapter The adapter to the model this ball is used in
	 * @return an IBall
	 */
	public IBall make(Point p, int r, Point v, Color c, Component container, AConfigBallAlgo installAlgo,
			IModel2BallAdapter modelAdapter);

	@Override
	public String toString();

	/**
	 * A factory to handle errors.
	 */
	public static IBallFactory errorBallFactory = new IBallFactory() {

		@Override
		public IBall make(Point p, int r, Point v, Color c, Component container, AConfigBallAlgo installAlgo,
				IModel2BallAdapter modelAdapter) {
			return new ErrorBall(p, r, v, c, container, installAlgo, modelAdapter);
		}

		@Override
		public String toString() {
			return "Error";
		}
	};

	/**
	 * A default ball factory.
	 */
	public static IBallFactory defaultBallFactory = new IBallFactory() {

		@Override
		public IBall make(Point p, int r, Point v, Color c, Component container, AConfigBallAlgo installAlgo,
				IModel2BallAdapter modelAdapter) {
			return new DefaultBall(p, r, v, c, container, installAlgo, modelAdapter);
		}

		@Override
		public String toString() {
			return "Default";
		}
	};
}
