package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.domainRegistry.InvalidDomainRoleException;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public class FOMTradingManager implements TradingManager {

	
	public TradingOrchestrator getTradingOrchestrator(
			TradingProcess tradingProcess){
	
		TradingOrchestrator orchestrator=null;
		
		if (FAST.currentDomainRole.equals("customer"))
			orchestrator = new FOMCustomerOrchestrator(tradingProcess);
		else if (FAST.currentDomainRole.equals("provider"))
			orchestrator = new FOMProviderOrchestrator(tradingProcess);
		else throw new InvalidDomainRoleException();

		return orchestrator;
	}

}
