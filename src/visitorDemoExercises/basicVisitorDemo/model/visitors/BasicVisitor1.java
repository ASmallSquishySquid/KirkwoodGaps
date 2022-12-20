package visitorDemoExercises.basicVisitorDemo.model.visitors;

import provided.basicVisitorFW.HostA;
import provided.basicVisitorFW.HostB;
import provided.basicVisitorFW.HostC;
import provided.basicVisitorFW.IVisitor;

/**
 * A simple visitor class that has different outputs for different host.
 * @author Annita Chang
 *
 */
public class BasicVisitor1 implements IVisitor {

	@Override
	public Object caseHostA(HostA host, Object... params) {
		return "HostA output :)";
	}

	@Override
	public Object caseHostB(HostB host, Object... params) {
		return "HostB output :(";
	}

	@Override
	public Object caseHostC(HostC host, Object... params) {
		return "HostC output ;)";
	}

}
