package model.strategies.criteria;

import java.awt.Color;

import model.IBall;

/**
 * A strategy where the criteria is similar colors.
 * 
 * @author Phoebe Scaccia
 */
public class ColorMatch implements ICriteriaStrategy {

	@Override
	public void init(IBall context) {
		return;
	}

	/**
	 * Checks if the two balls are similarly colored.
	 */
	@Override
	public boolean satisfied(IBall context, IBall target) {
		if (context != target) {
			Color contextColor = context.getColor();
			Color targetColor = target.getColor();
			double colorDist = Math.abs(contextColor.getRed() - targetColor.getRed()) + 
					Math.abs(contextColor.getGreen() - targetColor.getGreen()) + Math.abs(contextColor.getBlue() - targetColor.getBlue());
			// Calculate the distance between the balls
			double dist = context.getLocation().distance(target.getLocation());
			// Calculate the minimum proximity distance
			double minProximityDistance = (context.getRadius() + target.getRadius()) * 2;
			
			if((colorDist < 150) & (dist <= minProximityDistance)) {
				return true;
			}
		}
		return false;
	}

}
