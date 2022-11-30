package model.strategies.paint;

import provided.utils.valueGenerator.impl.Randomizer;

/**
 * Paints a picture of the Earth.
 * 
 * @author Phoebe Scaccia
 */
public class PlanetStrategy extends ImagePaintStrategy {

	/**
	 * An array of the paths to the possible planet images.
	 */
	private static final String[] planets = {"images/Jupiter.png", "images/Mars.png", "images/Saturn.png"};

	/**
	 * Constructor for a new PlanetStrategy.
	 */
	public PlanetStrategy() {
		super(planets[Randomizer.Singleton.randomInt(0, planets.length - 1)], 1);
	}

}
