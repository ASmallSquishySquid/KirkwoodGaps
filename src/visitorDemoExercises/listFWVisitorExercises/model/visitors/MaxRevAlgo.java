package visitorDemoExercises.listFWVisitorExercises.model.visitors;

import provided.listFW.IListAlgo;
import provided.listFW.MTList;
import provided.listFW.NEList;

/**
 * Finds the max element in the list.
 * 
 * @author Phoebe Scaccia
 */
public class MaxRevAlgo implements IListAlgo {

	@Override
	public Object emptyCase(MTList host, Object... inp) {
		return Integer.MIN_VALUE;
	}

	@Override
	public Object nonEmptyCase(NEList host, Object... inp) {
		return Math.max((int) host.getFirst(), (int) host.getRest().execute(this));
	}

}
