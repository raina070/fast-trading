package es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.process;

public class UnknownEndPoint extends RuntimeException {

	String endPoint;
	public UnknownEndPoint(String string) {
		endPoint = string;
		
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return super.toString()+" ("+ endPoint +" missing)";
	}
	
}
