package controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import model.BallModel;
import model.adapters.IViewControlAdapter;
import model.adapters.IViewUpdateAdapter;
import model.visitors.algos.IBallAlgo;
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
	private BallGUI<IBallAlgo<Void, Void>> view;

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
				view.update();
			}

		});

		this.view = new BallGUI<>(new IModelControlAdapter<IBallAlgo<Void, Void>>() {

			@Override
			public IBallAlgo<Void, Void> addPaintStrategy(String classname) {
				return model.makePaintStrategyAlgo(classname);
			}

			@Override
			public IBallAlgo<Void, Void> addUpdateStrategy(String classname) {
				return model.makeUpdateStrategyAlgo(classname);
			}
			

			@Override
			public IBallAlgo<Void, Void> addCriteriaStrategy(String classname) {
				return model.makeCriteriaStrategyAlgo(classname);
			}
			
			@Override
			public IBallAlgo<Void, Void> addInteractStrategy(String classname) {
				return model.makeInteractStrategyAlgo(classname);
			}

			@Override
			public void makeSwitcherBall() {
				model.loadBall(model.getSwitcherInstallAlgo());
			}

			@Override
			public void clearBalls() {
				model.clearBalls();
			}

			@Override
			public void makeBall(IBallAlgo<Void, Void> selectedItem) {
				model.loadBall(selectedItem);
			}

			@Override
			public IBallAlgo<Void, Void> combineStrategies(IBallAlgo<Void, Void> selectedItem1, IBallAlgo<Void, Void> selectedItem2) {
				return model.combineStrategyAlgos(selectedItem1, selectedItem2);
			}

			@Override
			public void switchStrategy(IBallAlgo<Void, Void> selectedItem) {
				model.switchStrategy(selectedItem);
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
