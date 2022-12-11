package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Timer;

import model.adapters.IModel2BallAdapter;
import model.adapters.IViewControlAdapter;
import model.adapters.IViewUpdateAdapter;
import model.balls.AsteroidBall;
import model.balls.IBall;
import model.balls.JupiterBall;
import model.balls.MarsBall;
import model.balls.SunBall;
import model.visitors.cmds.IBallCmd;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.dispatcher.IObserver;
import provided.utils.dispatcher.impl.SequentialDispatcher;
import provided.utils.displayModel.IATImage;

/**
 * The BallModel class.
 */
public class BallModel {
	/**
	 * The number of screenshots taken.
	 */
	private int captures = 0;
	/**
	 * The IDispatcher whose IObservers are ABall objects.
	 */
	private IDispatcher<IBallCmd> ballDispatcher = new SequentialDispatcher<IBallCmd>();
	/**
	 * The model to view update adapter created by the controller.
	 */
	private IViewUpdateAdapter viewUpdtAdpt = IViewUpdateAdapter.NULL_OBJECT;
	/**
	 * The model to view control adapter created by the controller.
	 */
	private IViewControlAdapter viewCtrlAdpt = IViewControlAdapter.NULL_OBJECT;
	/**
	 * The timer managing the update time.
	 */
	private Timer timer = new Timer(10000, (e) -> viewUpdtAdpt.update());
	/**
	 * The timer managing the update time.
	 */
	private Timer timer2 = new Timer(900000, (e) -> {
		new Thread() {
			public void run() {
				screenshot();
			}
		}.start();
	});

	/**
	 * Constructor for the BallModel.
	 * @param viewCtrlAdpt : the model to view control adapter.
	 * @param viewUpdtAdpt : the model to view update adapter.
	 */
	public BallModel(IViewControlAdapter viewCtrlAdpt, IViewUpdateAdapter viewUpdtAdpt) {
		this.viewUpdtAdpt = viewUpdtAdpt;
		this.viewCtrlAdpt = viewCtrlAdpt;
	}

	/**
	 * Starts the timer.
	 */
	public void start() {
		this.timer.start();
		this.timer2.start();
	}

	/**
	 * Clears all Balls from the dispatcher.
	 */
	public void clearBalls() {
		ballDispatcher.removeAllObservers();
	}

	/**
	 * Loads all the balls in.
	 */
	public void loadBalls() {		
		IObserver<IBallCmd> mars = new MarsBall(viewCtrlAdpt.getCanvas(), new IModel2BallAdapter() {
			@Override
			public IATImage getImageWrapper(Image image) {
				return viewCtrlAdpt.getIATImage(image);
			}
		});
		
		ballDispatcher.addObserver(mars);
		
		IObserver<IBallCmd> sun = new SunBall(viewCtrlAdpt.getCanvas(), new IModel2BallAdapter() {
			@Override
			public IATImage getImageWrapper(Image image) {
				return viewCtrlAdpt.getIATImage(image);
			}
		});
		
		ballDispatcher.addObserver(sun);
		
		IObserver<IBallCmd> jupiter = new JupiterBall(viewCtrlAdpt.getCanvas(), new IModel2BallAdapter() {
			@Override
			public IATImage getImageWrapper(Image image) {
				return viewCtrlAdpt.getIATImage(image);
			}
		});
		
		ballDispatcher.addObserver(jupiter);
		
		for (int i = 0; i < 500; i++) {
			IObserver<IBallCmd> asteroid = new AsteroidBall(viewCtrlAdpt.getCanvas(), new IModel2BallAdapter() {
				@Override
				public IATImage getImageWrapper(Image image) {
					return viewCtrlAdpt.getIATImage(image);
				}
			});
			
			ballDispatcher.addObserver(asteroid);
		}
		
		while (true) {
			update();
		}
	}

	/**
	 * Updates all that ABalls that are observers.
	 */
	public void update() {
		ballDispatcher.updateAll(new IBallCmd() {

			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				context.move();
				context.interact(disp);
				context.updateState(disp);
			}

		});
	}
	
	/**
	 * Paints the system to the view.
	 *
	 * @param g the graphics object provided by the view.
	 */
	public void paint(Graphics g) {
		ballDispatcher.updateAll(new IBallCmd() {

			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				context.paint(g);
			}

		});
	}
	
	/**
	 * Takes a screenshot of the progress.
	 */
	public void screenshot() {
		try {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = new Robot().createScreenCapture(screenRect);
			ImageIO.write(capture, "bmp", new File("C:\\Users\\2scac\\Desktop\\Kirkwood Progress\\" + captures++ + ".bmp"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
