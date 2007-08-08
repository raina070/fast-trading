package es.us.lsi.tdg.fast.core.trading;


public interface TradingProcess {

	void start();

	public String getPID();
	public String getOrchName();

	void setOrchestrator(TradingOrchestrator customerOrchestrator);
	
}
