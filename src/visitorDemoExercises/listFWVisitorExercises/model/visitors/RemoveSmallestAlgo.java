package visitorDemoExercises.listFWVisitorExercises.model.visitors;

import provided.listFW.IList;
import provided.listFW.IListAlgo;
import provided.listFW.MTList;
import provided.listFW.NEList;

/**
 * Removes the smallest value from the list.
 * 
 * @author Phoebe Scaccia
 *
 */
public class RemoveSmallestAlgo implements IListAlgo {

	@Override
	public Object emptyCase(MTList host, Object... inp) {
		return host;
	}

	@Override
	public Object nonEmptyCase(NEList host, Object... inp) {
		int minIndex = (int) host.getRest().execute(helperFind, host.getFirst(), 0, 0);
		return host.execute(helperRemove, minIndex, 0);
	}
	
	/**
	 * Recursive helper that uses an accumulator as its input parameter.
	 */
	private IListAlgo helperFind = new IListAlgo() {

		@Override
		/**
		 * The result is the current accumulated value.
		 */
		public Object emptyCase(MTList host, Object... accs) {
			return accs[1];
		}

		@Override
		/**
		 * Add first to the incoming accumulated value and pass the new value to the rest of the list, recursively.
		 */
		public Object nonEmptyCase(NEList host, Object... accs) {
			int min = Math.min((int) accs[0], (int) host.getFirst());
			if (min < (int) accs[0]) {
				return host.getRest().execute(this, min, accs[2], (int) accs[2] + 1);
			}
			return host.getRest().execute(this, accs[0], accs[1], (int) accs[2] + 1);
		}

	};
	
	/**
	 * Helps remove the item at the index provided.
	 */
	private IListAlgo helperRemove = new IListAlgo() {
		
		@Override
		public Object nonEmptyCase(NEList host, Object... inp) {
			if (inp[0] != inp[1]) {
				return new NEList(host.getFirst(), (IList) host.getRest().execute(this, inp[0], (int) inp[1] + 1));
			}
			return (IList) host.getRest().execute(this, inp[0], (int) inp[1] + 1);
		}
		
		@Override
		public Object emptyCase(MTList host, Object... inp) {
			return host;
		}
	};
}
