package visitorDemoExercises.listFWVisitorExercises.model.visitors;

import provided.listFW.visitors.AAccumulator;

/**
 * Sums everything starting at the given index.
 * 
 * @author Phoebe Scaccia
 */
public class SumRightAcc extends AAccumulator {

	/**
	 * The start index.
	 */
	private int startIndex;
	/**
	 * The current index.
	 */
	private int curIndex;
	
	/**
	 * Constructor for a new SumRightAcc.
	 * 
	 * @param value the starting index
	 */
	public SumRightAcc(String value) {
		super(0);
		this.startIndex = Integer.parseInt(value);
		this.curIndex = 0;
	}

	@Override
	public void accumulate(Object x) {
		if (this.curIndex >= this.startIndex) {
			this.value = (int) this.value + (int) x;
		}
		this.curIndex++;
	}

}
