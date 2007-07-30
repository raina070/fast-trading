package es.us.lsi.tdg.fast.core.roles.trading;

public interface TradingManager {

	/**
	 * Returns a trading orchestrator for the specific TOID (Trading Orchestration ID) 
	 */
	public TradingOrchestrator getTradingOrchestrator(String TOID, String CID);

	/**
	 * Returns a trading orchestrator based on several things (preferences, etc...) 
	 */
	public TradingOrchestrator getTradingOrchestrator(String CID);
	
}
