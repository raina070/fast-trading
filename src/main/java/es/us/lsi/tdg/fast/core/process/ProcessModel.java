package es.us.lsi.tdg.fast.core.process;

import es.us.lsi.tdg.fast.core.process.terminator.ConstantTerminator;
import es.us.lsi.tdg.fast.core.process.terminator.ProcessTerminator;

public enum ProcessModel {
	SINGLE(new ConstantTerminator(true)),
	CONTINOUS(new ConstantTerminator(false)),
	CUSTOM(new ConstantTerminator(true));
	
	private ProcessTerminator terminator;
	

	private ProcessModel(ProcessTerminator value){
		terminator=value;
	}
	
	public ProcessTerminator getTerminator() {
		return terminator;
	}


	public void setTerminator(ProcessTerminator terminator) {
		this.terminator = terminator;
	}
	
	
	
} 
