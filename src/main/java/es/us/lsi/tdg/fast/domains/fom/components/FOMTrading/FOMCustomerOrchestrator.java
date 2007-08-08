package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.components.information.BaseInformation;
import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.component.UnknownComponentException;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
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
		FAST.shell.showMessage("  Starting CustomerOrchestration for PID "+tradingProcess.getPID());
		
		try {
			
			FOMDiscovery disco = (FOMDiscovery) FAST.componentFactory.getByName("FOMDiscovery");
			FOMInformation info = (FOMInformation) FAST.componentFactory.getByName("FOMInformation");

			FAST.componentFactory.bind("PullPotentialCounterPartyNotification", disco, info);
			
			ControllableProcess trackerProcess = disco.getTrackerProcess();
//			ControllableProcess inquirerProcess = disco.getInquirerProcess();
	
			trackerProcess.start();
			
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
