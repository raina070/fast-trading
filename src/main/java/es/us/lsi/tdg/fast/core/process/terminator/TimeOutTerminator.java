package es.us.lsi.tdg.fast.core.process.terminator;

public class TimeOutTerminator implements ProcessTerminator {

	private long initMilisec;
	private long milisecInterval;
	
	public TimeOutTerminator(long milisecInterval){
		this.milisecInterval = milisecInterval;
		this.initMilisec = System.currentTimeMillis();
	}

	public void init(){
		this.initMilisec = System.currentTimeMillis();
	}

	public boolean terminate() {	
		return ((initMilisec+milisecInterval)<=System.currentTimeMillis());
	}

}
