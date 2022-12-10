package model.balls;

import java.awt.Component;
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
	 * @param distance the ABall's position.
	 * @param angle the starting angle around the sun in radians
	 * @param radius the ABall's radius.
	 * @param mass the mass of this ball
	 * @param container the ABall's container.
	 * @param installAlgo The algo to complete the installation of strategies and any other desired operations
	 * @param modelAdapter The adapter to the model this ball is used in
	 * @return an IBall
	 */
	public IBall make(double distance, double angle, int radius, double mass, Component container, 
			AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter);

	@Override
	public String toString();

	/**
	 * A factory to handle errors.
	 */
	public static IBallFactory errorBallFactory = new IBallFactory() {

		@Override
		public IBall make(double distance, double angle, int radius, double mass, Component container, AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter) {
			return new ErrorBall(distance, angle, radius, mass, container, modelAdapter);
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
		public IBall make(double distance, double angle, int radius, double mass, Component container, AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter) {
			return new DefaultBall(distance, angle, radius, mass, container, installAlgo, modelAdapter);
		}

		@Override
		public String toString() {
			return "Default";
		}
	};
}
