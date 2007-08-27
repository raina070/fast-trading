package es.us.lsi.tdg.fast.core.process;

public class ConstantTerminator implements ProcessTerminator {

	private boolean value;
	
	
	
	public ConstantTerminator(boolean value) {
		super();
		this.value = value;
	}



	public boolean terminate() {
		return value;
	}

}
