package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.components.TradingProcess;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.trading.TradingOrchestrator;

public class FOMTradingOrchestrator extends AbstractControllableProcess implements TradingOrchestrator {

	TradingProcess tradingProcess;
	
	public FOMTradingOrchestrator(TradingProcess tradingProcess) {
		this.tradingProcess = tradingProcess;
	}

	@Override
	protected void run() {
	
		
	}
	
}
