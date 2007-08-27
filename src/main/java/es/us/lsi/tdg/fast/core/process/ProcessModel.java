package es.us.lsi.tdg.fast.core.process;

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
