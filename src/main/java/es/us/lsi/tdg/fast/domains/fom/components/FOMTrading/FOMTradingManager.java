package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.components.TradingProcess;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;
import es.us.lsi.tdg.fast.core.roles.trading.TradingOrchestrator;

public class FOMTradingManager implements TradingManager {

	private TradingOrchestrator tradingOrchestrator;
	
	public TradingOrchestrator getTradingOrchestrator(
			TradingProcess tradingProcess) {
	
		TradingOrchestrator orchestrator=null;
		
		switch(FAST.currentDomainRole){
		
			"Customer": orchestrator = new FOMCustomerOrchestrator(tradingProcess); break;
			"Provider": orchestrator = new FOMProviderOrchestrator(tradingProcess); break;
			
			default: throw new InvalidDomainRoleException(); break;
		}
			
		return orchestrator;
	}

}
