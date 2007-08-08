package es.us.lsi.tdg.fast.core.trading;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.component.trading.TradingComponent;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;

public class BaseTradingProcess implements TradingProcess{

	private String PID;
	private String orchName;
	
	public String getPID() {
		return PID;
	}

	public String getOrchName() {
		return orchName;
	}

	public BaseTradingProcess(String PID, String orchName) {
		this.PID = PID;
		this.orchName = orchName;
	}

	public void start() {
		FAST.shell.showMessage("Starting Trading Process...");
		
		FAST.log.info("Obtaining Trading Component...");
		TradingComponent tradingComponent = (TradingComponent) FAST.componentFactory.getByType("Trading");
		
		if(tradingComponent != null){
			
			TradingManager tradingManager = (TradingManager) tradingComponent.getTradingManager();
			
			FAST.shell.showMessage("  -> Geting Orchestrator...");	
			TradingOrchestrator tradingOrchestrator = tradingManager.getTradingOrchestrator(this);
			
			tradingOrchestrator.start();
			
		}else{
			FAST.shell.showMessage("FATAL ERROR: Can not obtain a Trading Component.");
		}
			
	}
}
