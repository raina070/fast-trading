package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.component.UnknownComponentException;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.FAST;

public class FOMCustomerOrchestrator 
			extends AbstractControllableProcess 
			implements TradingOrchestrator {

	protected TradingProcess tradingProcess;
	
	public FOMCustomerOrchestrator(TradingProcess tradingProcess) {
		super("FOMCustomerOrchestrator");
		this.tradingProcess = tradingProcess;
		this.tradingProcess.setOrchestrator(this);
		// TODO Instantiate Components and Wire them
	}

	public void start(){
		FAST.shell.showMessage("Starting CustomerOrchestration for PID "+tradingProcess.getPID());
		
		try {
			
			Component disco = FAST.componentFactory.getByName("FOMDiscovery");
			Component info = FAST.componentFactory.getByName("BaseInformation");

			FAST.componentFactory.bind("PullPotentialCounterPartyNotification", disco, info);
			
			
		} catch (UnknownComponentException e) {
			e.printStackTrace();
		}
		
		
			
		
		
	}
	
	public void stop(){
		FAST.shell.showMessage("Stoping CustomerOrchestration for PID "+tradingProcess.getPID());
	}
	
	public void event(String event) {
		
		if(event.equals("SLA_REACHED")){
			FAST.shell.showMessage("SLA Reached.");
			stop();
		}
	}

	@Override
	protected void run() {
	}

}
