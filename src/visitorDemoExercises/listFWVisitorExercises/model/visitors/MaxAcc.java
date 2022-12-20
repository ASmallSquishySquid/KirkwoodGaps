package visitorDemoExercises.listFWVisitorExercises.model.visitors;

import provided.listFW.visitors.AAccumulator;

/**
 * A max accumulator.
 * 
 * @author Phoebe Scaccia
 */
public class MaxAcc extends AAccumulator {

	/**
	 * Constructor for a new MaxAcc.
	 */
	public MaxAcc() {
		super(Integer.MIN_VALUE);
	}
	
	@Override
	public void accumulate(Object x) {
		this.value = Math.max((int) value, (int) x);
	}

}
