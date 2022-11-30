package controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.function.Supplier;

import javax.swing.JComponent;

import model.BallModel;
import model.adapters.IViewControlAdapter;
import model.adapters.IViewUpdateAdapter;
import model.balls.IBallFactory;
import model.visitors.algos.AConfigBallAlgo;
import provided.utils.displayModel.IATImage;
import view.BallGUI;
import view.IModelControlAdapter;
import view.IModelUpdateAdapter;

/**
 * The controller for the balls and the frame.
 *
 * @author Timothy Louie
 * @author Phoebe Scaccia
 *
 */
public class Controller {

	/**
	 * The model that handles the balls.
	 */
	private BallModel model;

	/**
	 * The frame where everything is displayed (the view).
	 */
	private BallGUI<AConfigBallAlgo, IBallFactory> view;

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

			@Override
			public void addConfigComponent(String label, Supplier<JComponent> compFac) {
				view.addComponent(label, compFac);//				
			}

		}, new IViewUpdateAdapter() {

			@Override
			public void update() {
				view.update();
			}

		});

		this.view = new BallGUI<AConfigBallAlgo, IBallFactory>(
				new IModelControlAdapter<AConfigBallAlgo, IBallFactory>() {

					@Override
					public AConfigBallAlgo addPaintStrategy(String classname) {
						return model.makePaintStrategyAlgo(classname);
					}

					@Override
					public AConfigBallAlgo addUpdateStrategy(String classname) {
						return model.makeUpdateStrategyAlgo(classname);
					}

					@Override
					public AConfigBallAlgo addCriteriaStrategy(String classname) {
						return model.makeCriteriaStrategyAlgo(classname);
					}

					@Override
					public AConfigBallAlgo addInteractStrategy(String classname) {
						return model.makeInteractStrategyAlgo(classname);
					}

					@Override
					public AConfigBallAlgo addConfigAlgo(String classname) {
						return model.makeConfigBallAlgo(classname);
					}

					@Override
					public IBallFactory addBallType(String classname) {
						return model.makeBallFactory(classname);
					}

					@Override
					public void clearBalls() {
						model.clearBalls();
					}

					@Override
					public void makeBall(IBallFactory selectedItem1, AConfigBallAlgo selectedItem2) {
						model.loadBall(selectedItem1, selectedItem2);
					}

					@Override
					public AConfigBallAlgo combineStrategies(AConfigBallAlgo selectedItem1,
							AConfigBallAlgo selectedItem2) {
						return model.combineStrategyAlgos(selectedItem1, selectedItem2);
					}

					@Override
					public String getDefaultPaintStrategy() {
						return model.getDefaultPaintStrategy();
					}

					@Override
					public String getDefaultUpdateStrategy() {
						return model.getDefaultUpdateStrategy();
					}

					@Override
					public String getDefaultCriteriaStrategy() {
						return model.getDefaultCriteriaStrategy();
					}

					@Override
					public String getDefaultInteractStrategy() {
						return model.getDefaultInteractStrategy();
					}

					@Override
					public String getDefaultBallType() {
						return model.getDefaultBallType();
					}

					@Override
					public String getDefaultConfigAlgo() {
						return model.getDefaultConfigAlgo();
					}
				}, new IModelUpdateAdapter() {

					@Override
					public void update(Graphics g) {
						model.update(g);
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
