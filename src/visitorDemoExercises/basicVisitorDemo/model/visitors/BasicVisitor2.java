package visitorDemoExercises.basicVisitorDemo.model.visitors;

import provided.basicVisitorFW.HostA;
import provided.basicVisitorFW.HostB;
import provided.basicVisitorFW.HostC;
import provided.basicVisitorFW.IVisitor;

public class BasicVisitor2 implements IVisitor {

	@Override
	public Object caseHostA(HostA host, Object... params) {
		// TODO Auto-generated method stub
		return "TEAM HOSTA!";
	}

	@Override
	public Object caseHostB(HostB host, Object... params) {
		// TODO Auto-generated method stub
		return "TEAM HOSTB!";
	}

	@Override
	public Object caseHostC(HostC host, Object... params) {
		// TODO Auto-generated method stub
		return "TEAM HOSTC!";
	}

}
