package es.us.lsi.tdg.fast.core.roles.trading;

import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public interface TradingManager {

		/**
	 * Returns a trading orchestrator for the given trading process 
	 */
	public TradingOrchestrator getTradingOrchestrator(TradingProcess tradingProcess);
	
}
