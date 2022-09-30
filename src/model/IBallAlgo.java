package model;

import provided.ballworld.extVisitors.IBallHostAlgo;

/**
 * An algorithm to process a host ball.
 * 
 * @author Phoebe Scaccia
 * 
 * @param <R> The return type of the algorithm
 * @param <P> The input type of the algorithm
 */
public interface IBallAlgo<R, P> extends IBallHostAlgo<R, P, IBall>{

}