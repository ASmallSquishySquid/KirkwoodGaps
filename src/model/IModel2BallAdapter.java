package model;

import java.awt.Image;

import provided.utils.displayModel.IATImage;

/**
 * An adapter to the model for the ball.
 * 
 * @author Phoebe Scaccia
 */
public interface IModel2BallAdapter {
	/**
	 * Gets the IATImage to load in an image.
	 * 
	 * @param image an Image
	 * @return an IATImge object
	 */
	public IATImage getImageWrapper(Image image);

}