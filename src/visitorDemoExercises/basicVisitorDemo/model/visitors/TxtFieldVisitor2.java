package visitorDemoExercises.basicVisitorDemo.model.visitors;

import provided.basicVisitorFW.HostA;
import provided.basicVisitorFW.HostB;
import provided.basicVisitorFW.HostC;
import provided.basicVisitorFW.IVisitor;

/**
 * A visitor class that gives host and input dependent output. 
 * Assuming the input is a class's name, each host's output will print that the host is taking that class.
 * @author Annita Chang
 *
 */
public class TxtFieldVisitor2 implements IVisitor {

	@Override
	public Object caseHostA(HostA host, Object... params) {
		return "HostA is taking " + params[1];
	}

	@Override
	public Object caseHostB(HostB host, Object... params) {
		return "HostB is taking " + params[1];
	}

	@Override
	public Object caseHostC(HostC host, Object... params) {
		return "HostC is taking " + params[1];
	}

}
