package visitorDemoExercises.listFWVisitorExercises.model.visitors;

import provided.listFW.visitors.AAccumulator;

/**
 * An accumulator that checks if a value exists.
 * 
 * @author Phoebe Scaccia
 */
public class ExistsAcc extends AAccumulator {

	/**
	 * The value to search for.
	 */
	private int search;
	
	/**
	 * Constructor for a new ExistsAcc.
	 * 
	 * @param value a String containing the value to search for
	 */
	public ExistsAcc(String value) {
		super(false);
		this.search = Integer.parseInt(value);
	}

	@Override
	public void accumulate(Object x) {
		this.value = (boolean) this.value | (int) x == search;
	}

}
