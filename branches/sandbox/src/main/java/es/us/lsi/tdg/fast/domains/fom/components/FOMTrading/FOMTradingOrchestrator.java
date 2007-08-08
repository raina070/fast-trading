package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public class FOMTradingOrchestrator extends AbstractControllableProcess implements TradingOrchestrator {

	TradingProcess tradingProcess;
	
	public FOMTradingOrchestrator(TradingProcess tradingProcess) {
		this.tradingProcess = tradingProcess;
	}

	@Override
	protected void run() {
	
		
	}
	
}
