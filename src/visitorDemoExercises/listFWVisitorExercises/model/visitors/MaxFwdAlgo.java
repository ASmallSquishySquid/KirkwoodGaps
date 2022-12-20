package visitorDemoExercises.listFWVisitorExercises.model.visitors;

import provided.listFW.IListAlgo;
import provided.listFW.MTList;
import provided.listFW.NEList;

/**
 * Finds the maximum value in the list through forward aggregation.
 * 
 * @author Phoebe Scaccia
 */
public class MaxFwdAlgo implements IListAlgo {

	@Override
	public Object emptyCase(MTList host, Object... inp) {
		return Integer.MIN_VALUE;
	}

	@Override
	public Object nonEmptyCase(NEList host, Object... inp) {
		return host.getRest().execute(helper, host.getFirst());
	}
	
	/**
	 * Recursive helper that uses an accumulator as its input parameter.
	 */
	private IListAlgo helper = new IListAlgo() {

		@Override
		/**
		 * The result is the current accumulated value.
		 */
		public Object emptyCase(MTList host, Object... accs) {
			return accs[0];
		}

		@Override
		/**
		 * Add first to the incoming accumulated value and pass the new value to the rest of the list, recursively.
		 */
		public Object nonEmptyCase(NEList host, Object... accs) {
			return host.getRest().execute(this, Math.max((int) accs[0], (int) host.getFirst()));
		}

	};

}
