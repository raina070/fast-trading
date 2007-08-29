package es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.process;

public class UnknownEndPoint extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7129651828334598133L;
	String endPoint;
	public UnknownEndPoint(String string) {
		endPoint = string;
		
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return super.toString()+" ("+ endPoint +" missing)";
	}
	
}
