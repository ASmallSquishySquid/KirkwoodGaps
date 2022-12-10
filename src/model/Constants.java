package model;

import java.awt.geom.Point2D;

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
	 * Conversion value for km to pixels for GUI display.
	 */
	public static double kmToPixels = 1e4;
	
	/**
	 * The gravitational constant.
	 */
	public static double gravitationalConstant = 6.673e-11;
	
	/**
	 * The position of the sun in the GUI.
	 */
	public static Point2D.Double sunPosition = new Point2D.Double();
	
	/**
	 * Calculates the starting velocity.
	 *
	 * @param distance the distance from the sun in km
	 * @param angle the angle around the sun in radians
	 * @return the starting velocity as a Point2D.Double
	 */
	public static Point2D.Double calculateVelocity(double distance, double angle) {
		
		double underSquare = Constants.gravitationalConstant * Constants.sunMass / distance;
		
		Point2D.Double velocity = new Point2D.Double(Math.sqrt(underSquare), 0);
		
		VectorUtil.Singleton.rotate(velocity, angle);
		
		return velocity;
		
	}
}
