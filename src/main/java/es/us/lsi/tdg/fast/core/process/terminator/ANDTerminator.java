package es.us.lsi.tdg.fast.core.process.terminator;

public class ANDTerminator implements ProcessTerminator {

	private ProcessTerminator t1,t2;
	
	public ANDTerminator(ProcessTerminator t1, ProcessTerminator t2) {
		super();
		this.t1 = t1;
		this.t2 = t2;
	}

	public boolean terminate() {
		return (t1.terminate() && t2.terminate());
	}

}
