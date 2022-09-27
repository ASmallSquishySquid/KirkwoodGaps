package model.strategies.paint;

import java.awt.geom.AffineTransform;

/**
 * Paints a narwhal that always stays upright.
 *
 * @author kyled
 *
 */
public class NarwhalStrategy extends UprightMultiPaintStrategy {

	/**
	 * Constructor for a new NarwhalStrategy.
	 */
	public NarwhalStrategy() {
		super(new AffineTransform(), new TriangleStrategy(new AffineTransform(), 0, -0.5, 1, 1),
				new TriangleStrategy(new AffineTransform(), 0, 0, -2, -1), new EllipseStrategy());
	}

}
