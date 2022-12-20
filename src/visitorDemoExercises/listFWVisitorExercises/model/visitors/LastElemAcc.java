package visitorDemoExercises.listFWVisitorExercises.model.visitors;

import provided.listFW.visitors.AAccumulator;

/**
 * Accumulates the last element.
 * 
 * @author Phoebe Scaccia
 */
public class LastElemAcc extends AAccumulator {

	/**
	 * Constructor for a new LastElemAcc.
	 */
	public LastElemAcc() {
		super(null);
	}
	
	@Override
	public void accumulate(Object x) {
		this.value = x;
	}

}
