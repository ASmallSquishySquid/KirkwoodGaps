package model.visitors.algos;

import model.balls.IBall;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;

/**
 * Checks if two balls are the same type.
 * 
 * @author Phoebe Scaccia
 */
public class CheckSameTypeBallAlgo extends BallAlgo<Boolean, IBall> {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -2157705235029289882L;

	/**
	 * Singleton instance.
	 */
	public static final CheckSameTypeBallAlgo Singleton = new CheckSameTypeBallAlgo();

	/**
	 * Constructor for a new CheckSameTypeBallAlgo.
	 */
	private CheckSameTypeBallAlgo() {
		// Only a default case is needed here because the main purpose here is to get the current ID value of the host
		// and to create an second visitor algorithm based on that ID value.
		super(new ABallAlgoCmd<>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = -6901262765853580813L;

			@Override
			public Boolean apply(IBallHostID hostID, IBall host, IBall... otherBalls) {

				// Delegate to the other ball to see if its type matches the host.
				// Return whatever the result of the delegation is.
				return otherBalls[0].execute(new BallAlgo<Boolean, Void>(new ABallAlgoCmd<>() {

					/**
					 * For serialization.
					 */
					private static final long serialVersionUID = -7658186199267192942L;

					@Override
					public Boolean apply(IBallHostID otherID, IBall other, Void... nu) {
						// Since there is a case for the same type as the host, the answer here is unequivocally false.
						return false;
					}
				}) {

					/**
					 * For serialization.
					 */
					private static final long serialVersionUID = -1880522660574783917L;

					// The use of an initializer block here eliminates the need for a separate line of code to add 
					// additional cases and thus this algorithm can be used without ever having to assign it to a variable.
					{
						// Add a case for the same type as the host, whatever that is.
						setCmd(hostID, new ABallAlgoCmd<>() {

							/**
							 * For serialization.
							 */
							private static final long serialVersionUID = -837269533414275126L;

							@Override
							public Boolean apply(IBallHostID otherID, IBall other, Void... nu) {
								// The case for the same type as the host, thus the answer here is unequivocally true.
								return true;
							}
						});
					}
				});
			}
		});
	}
}
