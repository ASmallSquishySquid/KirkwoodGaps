package model.balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import model.adapters.IModel2BallAdapter;
import model.visitors.algos.AConfigBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;

public class ErrorBall extends ABall {

	public ErrorBall(IBallHostID id, Point p, int r, Point v, Color c, Component container,
			AConfigBallAlgo installAlgo, IModel2BallAdapter modelAdapter) {
		super(id, p, r, v, c, container, installAlgo, modelAdapter);
		// TODO Auto-generated constructor stub
	}

}
