package model;

import java.awt.Graphics;
import java.awt.Image;
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
	 * The time, in milliseconds, that the GUI updates.
	 */
	private int timeSlice = 20;

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
	private Timer timer = new Timer(timeSlice, (e) -> viewUpdtAdpt.update());

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
		
		for (int i = 0; i < 2000; i++) {
			IObserver<IBallCmd> asteroid = new AsteroidBall(viewCtrlAdpt.getCanvas(), new IModel2BallAdapter() {
				@Override
				public IATImage getImageWrapper(Image image) {
					return viewCtrlAdpt.getIATImage(image);
				}
			});
			
			ballDispatcher.addObserver(asteroid);
		}
	}

	/**
	 * Updates all that ABalls that are observers.
	 * @param g the Graphics object for the view.
	 */
	public void update(Graphics g) {
		ballDispatcher.updateAll(new IBallCmd() {

			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				context.move();
				context.interact(disp);
				context.updateState(disp);
				context.paint(g);
			}

		});
	}
}
