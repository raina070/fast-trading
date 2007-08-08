package es.us.lsi.tdg.fast.core.component;

public class UnwiredComponent extends RuntimeException {

	String unwiredAdaptor;
	
	public UnwiredComponent(String unwiredAdaptor) {
		this.unwiredAdaptor = unwiredAdaptor;

	}

	public String toString(){
		return toString()+":"+unwiredAdaptor;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
