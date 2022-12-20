package visitorDemoExercises.listFWVisitorExercises.model.visitors;

import provided.listFW.IListAlgo;
import provided.listFW.MTList;
import provided.listFW.NEList;

/**
 * Searches the list (forwards) for the given element.
 * 
 * @author Phoebe Scaccia
 */
public class ExistsFwdAlgo implements IListAlgo {

	@Override
	public Object emptyCase(MTList host, Object... inp) {
		return false;
	}

	@Override
	public Object nonEmptyCase(NEList host, Object... inp) {
		return host.getRest().execute(helper, (int) host.getFirst() == Integer.parseInt((String) inp[0]), inp[0]);
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
			return host.getRest().execute(this, ((int) host.getFirst() == Integer.parseInt((String) accs[1]) | (boolean) accs[0]), accs[1]);
		}

	};

}
