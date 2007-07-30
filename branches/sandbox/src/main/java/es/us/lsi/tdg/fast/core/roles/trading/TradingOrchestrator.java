package es.us.lsi.tdg.fast.core.roles.trading;

public interface TradingOrchestrator {
	
	/** 
	 * Starts a new trading process for current client
	 */
	public void start();
		
	public void stop();
	
}
