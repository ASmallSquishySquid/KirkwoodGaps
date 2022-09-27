package model.strategies.paint;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import model.IBall;
import provided.utils.displayModel.IATImage;

/**
 * Paint strategy that paints an image from a file, scaled to the host Ball's radius.
 * 
 * @author Phoebe Scaccia
 *
 */
public class ImagePaintStrategy extends APaintStrategy {

	/**
	 * An affine transform used to transform the image into its unit size and location.
	 */
	protected AffineTransform unitAT = new AffineTransform();

	/**
	 * The percentage of the average of the width and height of the image that defines a unit radius for the image.
	 */
	private double fillFactor = 1.0;

	/**
	 * Ratio of the unit radius circle to the effective radius size of the image.
	 */
	private double scaleFactor = 1.0;

	/**
	 * The loaded in image.
	 */
	private Image loadedImage;

	/**
	 * The image.
	 */
	private IATImage image;

	/**
	 * Constructor for a new ImagePaintStrategy.
	 * 
	 * @param fileName the relative path to the image
	 * @param fillFactor the percentage of the average of the width and height of the image that defines a unit radius for the image
	 */
	public ImagePaintStrategy(String fileName, double fillFactor) {
		super(new AffineTransform());
		this.fillFactor = fillFactor;
		this.loadedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(fileName));
	}

	/**
	 * Constructor for a new ImagePaintStrategy.
	 * 
	 * @param at an AffineTransform object
	 * @param fileName the relative path to the image
	 * @param fillFactor the percentage of the average of the width and height of the image that defines a unit radius for the image
	 */
	public ImagePaintStrategy(AffineTransform at, String fileName, double fillFactor) {
		this(fileName, fillFactor);
		this.at = at;
	}

	public void init(IBall context) {
		this.image = context.getIatImage(this.loadedImage);

		this.scaleFactor = 2.0 / (fillFactor * (this.image.getWidth() + this.image.getHeight()) / 2.0);

		this.unitAT.setToScale(scaleFactor, scaleFactor);
		this.unitAT.translate(-1 * this.image.getWidth() / 2.0, -1 * this.image.getHeight() / 2.0);
	}

	@Override
	public void paintTransform(Graphics g, IBall context, AffineTransform at) {
		AffineTransform tempAT = new AffineTransform();
		tempAT.setToScale(scaleFactor, scaleFactor);
		tempAT.translate(-image.getWidth() / 2.0, -image.getHeight() / 2.0);
		tempAT.preConcatenate(at);
		this.image.draw(g, tempAT);
	}

}
