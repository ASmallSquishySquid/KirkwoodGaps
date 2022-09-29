package visitorDemoExercises.basicVisitorDemo.model.visitors;

import provided.basicVisitorFW.HostA;
import provided.basicVisitorFW.HostB;
import provided.basicVisitorFW.HostC;
import provided.basicVisitorFW.IVisitor;

public class TxtFieldVisitor1 implements IVisitor {

	@Override
	public Object caseHostA(HostA host, Object... params) {
		// TODO Auto-generated method stub
		return "HostA can " + params[1];
	}

	@Override
	public Object caseHostB(HostB host, Object... params) {
		// TODO Auto-generated method stub
		return "HostB can " + params[1];
	}

	@Override
	public Object caseHostC(HostC host, Object... params) {
		// TODO Auto-generated method stub
		return "HostC can " + params[1];
	}

}
