package model.visitors.cmds;

import model.balls.IBall;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.ABallHostAlgoCmd;
import provided.logger.ILoggerControl;
import provided.logger.LogLevel;

/**
 * An abstract command.
 * 
 * @author Phoebe Scaccia
 * 
 * @param <R> The return type of the algorithm
 * @param <P> The input type of the algorithm
 */
public abstract class ABallAlgoCmd<R, P> extends ABallHostAlgoCmd<R, P, IBall> {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = -4448150298704708249L;

	/**
	 * Static factory to create a null cmd that matches the generic typing of the target variable.
	 * @param <TReturn> The return type of the null cmd
	 * @param <TParam> The input parameter type of the null cmd
	 * @return Always returns null
	 */
	public static final <TReturn, TParam> ABallAlgoCmd<TReturn, TParam> MakeNull() {
		return new ABallAlgoCmd<>() {
			/**
			 * For serialization
			 */
			private static final long serialVersionUID = 8487872090388982189L;

			@Override
			public TReturn apply(IBallHostID index, IBall host, @SuppressWarnings("unchecked") TParam... params) {
				ILoggerControl.getSharedLogger().log(LogLevel.DEBUG, "ABallAlgoCmd.NULL invoked.  Returned null.");
				return null;
			}

		};
	}

	/**
	 * Static factory to create a error cmd that matches the generic typing of the target variable.
	 * @param <TReturn> The return type of the null cmd
	 * @param <TParam> The input parameter type of the null cmd
	 * @param errMsg An error message to display when the error algo is executed, e.g. the erroneous classname that lead to the error. 
	 * @return Always returns null
	 */
	public static final <TReturn, TParam> ABallAlgoCmd<TReturn, TParam> MakeError(String errMsg) {
		return new ABallAlgoCmd<>() {
			/**
			 * For serialization
			 */
			private static final long serialVersionUID = 3045945985422382688L;

			@Override
			public TReturn apply(IBallHostID index, IBall host, @SuppressWarnings("unchecked") TParam... params) {
				ILoggerControl.getSharedLogger().log(LogLevel.ERROR,
						"ABallAlgoCmd.ERROR invoked.  Returned null. Error msg: " + errMsg);
				return null;
			}
		};
	}
}