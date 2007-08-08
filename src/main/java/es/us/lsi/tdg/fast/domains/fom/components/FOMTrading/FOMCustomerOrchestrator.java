package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.FAST;

public class FOMCustomerOrchestrator 
			extends AbstractControllableProcess 
			implements TradingOrchestrator {

	TradingProcess tradingProcess;
	
	public FOMCustomerOrchestrator(TradingProcess tradingProcess) {
		super("FOMCustomerOrchestrator");
		this.tradingProcess = tradingProcess;
		// TODO Instantiate Components and Wire them
	}

	public void start(){
		FAST.shell.showMessage("Starting CustomerOrchestration for PID "+tradingProcess.getPID());
		super.start();
	}
	
	@Override
	protected void run() {
		// TODO Start Components
		
	}

}
