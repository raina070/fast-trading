package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public class FOMProviderOrchestrator 
			extends AbstractControllableProcess 
			implements TradingOrchestrator {

	TradingProcess tradingProcess;
	
	public FOMProviderOrchestrator(TradingProcess tradingProcess) {
		super("FOMProviderOrchestrator");
		this.tradingProcess = tradingProcess;
		// TODO Instantiate Components and Wire them
	}

	@Override
	protected void run() {
		// TODO Start Components
		
	}

}
