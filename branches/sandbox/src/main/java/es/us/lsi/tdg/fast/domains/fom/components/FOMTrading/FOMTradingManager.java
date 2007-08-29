package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.domainRegistry.InvalidDomainRoleException;
import es.us.lsi.tdg.fast.core.process.ProcessModel;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public class FOMTradingManager implements TradingManager {

	
	public TradingOrchestrator getTradingOrchestrator(
			TradingProcess tradingProcess){
	
		TradingOrchestrator orchestrator=null;
		
		String domainRoleName = FAST.currentDomainRole.getName();
		
		if (domainRoleName.equals("customer"))
			orchestrator = new FOMCustomerOrchestrator(tradingProcess,ProcessModel.SINGLE);
		else if (domainRoleName.equals("provider"))
			orchestrator = new FOMProviderOrchestrator(tradingProcess,ProcessModel.SINGLE);
		else throw new InvalidDomainRoleException();

		return orchestrator;
	}

}
