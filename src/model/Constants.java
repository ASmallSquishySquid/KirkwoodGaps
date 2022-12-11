package model;

import java.awt.geom.Point2D;

import provided.utils.valueGenerator.impl.Randomizer;
import provided.utils.valueGenerator.impl.VectorUtil;

/**
 * Holds the constants.
 * 
 * @author Phoebe Scaccia
 */
public class Constants {
	/**
	 * The mass of the sun.
	 */
	public static double sunMass = 1.9885e30;
	
	/**
	 * The gravitational constant.
	 */
	public static double gravitationalConstant = 6.673e-11;
	
	/**
	 * The position of the sun in the GUI.
	 */
	public static Point2D.Double sunPosition = new Point2D.Double(722.5, 395.5);
	
	/**
	 * Conversion value for km to pixels for GUI display.
	 */
	public static double kmToPixels = 2e6;
	
	/**
	 * The scaling for the radii.
	 */
	private static double radiusScale = 1e3;
	
	/**
	 * The mass range for an asteroid.
	 */
	private static Point2D.Double asteroidMassRange = new Point2D.Double(1e10, 1e18);
	
	/**
	 * The distance range for an asteroid.
	 */
	private static Point2D.Double asteroidDistanceRange = new Point2D.Double(3.08e8, 4.79e8);
	
	/**
	 * Calculates the starting position.
	 *
	 * @param distance the distance from the sun in km
	 * @param angle the angle around the sun in radians
	 * @return the starting velocity as a Point2D.Double
	 */
	public static Point2D.Double calculateStartingPosition(double distance, double angle) {
		Point2D.Double position = new Point2D.Double(distance / kmToPixels, 0);
		
		VectorUtil.Singleton.rotate(position, angle);
		
		position.x += sunPosition.x;
		position.y += sunPosition.y;
		
		return position;
		
	}
	
	/**
	 * Calculates the starting velocity.
	 *
	 * @param distance the distance from the sun in km
	 * @param angle the angle around the sun in radians
	 * @return the starting velocity as a Point2D.Double
	 */
	public static Point2D.Double calculateVelocity(double distance, double angle) {
		double underSquare = gravitationalConstant * (sunMass / 1e17) / (distance / kmToPixels);
		
		Point2D.Double velocity = new Point2D.Double(0, -1 * Math.sqrt(underSquare));
		
		VectorUtil.Singleton.rotate(velocity, angle);
		
		return velocity;
	}
	
	/**
	 * Scales the radius.
	 *
	 * @param radius the object's actual radius
	 * @return the scaled radius
	 */
	public static double calculateRadius(double radius) {
		return Math.max(10 * Math.log10(radius / radiusScale), 1);
	}
	
	/**
	 * Gets a random asteroid mass.
	 *
	 * @return a mass
	 */
	public static double getRandomAsteroidMass() {
		return Randomizer.Singleton.randomDouble(asteroidMassRange.x, asteroidMassRange.y);
	}
	
	/**
	 * Gets a random asteroid distance.
	 *
	 * @return a distance
	 */
	public static double getRandomAsteroidDistance() {
		return Randomizer.Singleton.randomDouble(asteroidDistanceRange.x, asteroidDistanceRange.y);		
	}
}
