package controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingUtilities;

import model.BallModel;
import model.adapters.IViewControlAdapter;
import model.adapters.IViewUpdateAdapter;
import provided.utils.displayModel.IATImage;
import view.BallGUI;
import view.IModelControlAdapter;
import view.IModelUpdateAdapter;

/**
 * The controller for the balls and the frame.
 *
 * @author Phoebe Scaccia
 */
public class Controller {

	/**
	 * The model that handles the balls.
	 */
	private BallModel model;

	/**
	 * The frame where everything is displayed (the view).
	 */
	private BallGUI view;

	/**
	 * Constructor for a new Controller.
	 */
	public Controller() {
		this.model = new BallModel(new IViewControlAdapter() {

			@Override
			public Component getCanvas() {
				return view.getCanvas();
			}

			@Override
			public IATImage getIATImage(Image image) {
				return IATImage.FACTORY.apply(image, view.getCanvas());
			}

		}, new IViewUpdateAdapter() {

			@Override
			public void update() {
				SwingUtilities.invokeLater(() -> view.update());
			}

		});

		this.view = new BallGUI(
				new IModelControlAdapter() {

					@Override
					public void clearBalls() {
						new Thread() {
							public void run() {
								model.clearBalls();
							}
						}.start();
					}

					@Override
					public void makeBalls() {
						new Thread() {
							public void run() {
								model.loadBalls();
							}
						}.start();
					}
				}, new IModelUpdateAdapter() {

					@Override
					public void update(Graphics g) {
						model.paint(g);
					}
				});
	}

	/**
	 * The main startup function.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Controller controller = new Controller();
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Start up the view and model.
	 */
	public void start() {
		model.start();
		view.start();
	}

}
