package es.us.lsi.tdg.fast.core.trading;

import es.us.lsi.tdg.fast.core.process.ControllableProcess;

public interface TradingOrchestrator extends ControllableProcess{

	void event(String event);
	
}
