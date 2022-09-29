package visitorDemoExercises.basicVisitorDemo.model.visitors;

import provided.basicVisitorFW.HostA;
import provided.basicVisitorFW.HostB;
import provided.basicVisitorFW.HostC;
import provided.basicVisitorFW.IVisitor;

public class BasicVisitor1 implements IVisitor {

	@Override
	public Object caseHostA(HostA host, Object... params) {
		// TODO Auto-generated method stub
		return "HostA output :)";
	}

	@Override
	public Object caseHostB(HostB host, Object... params) {
		// TODO Auto-generated method stub
		return "HostB output :(";
	}

	@Override
	public Object caseHostC(HostC host, Object... params) {
		// TODO Auto-generated method stub
		return "HostC output ;)";
	}

}
