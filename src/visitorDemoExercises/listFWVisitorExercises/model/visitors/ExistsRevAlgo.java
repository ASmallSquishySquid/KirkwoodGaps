package visitorDemoExercises.listFWVisitorExercises.model.visitors;

import provided.listFW.IListAlgo;
import provided.listFW.MTList;
import provided.listFW.NEList;

/**
 * Searches the list (reverse) for the given element.
 * 
 * @author Phoebe Scaccia
 */
public class ExistsRevAlgo implements IListAlgo {

	@Override
	public Object emptyCase(MTList host, Object... inp) {
		return false;
	}

	@Override
	public Object nonEmptyCase(NEList host, Object... inp) {
		return (int) host.getFirst() == Integer.parseInt((String) inp[0]) | (boolean) host.getRest().execute(this, inp);
	}

}
