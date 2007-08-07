package es.us.lsi.tdg.fast.components;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;
import es.us.lsi.tdg.fast.core.roles.trading.TradingOrchestrator;

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
		
		try {
			FAST.log.info("Obtaining Trading Manager...");
			Component tradingComponent = FAST.componentFactory.getByType("Trading");
			
			TradingManager tradingManager = tradingComponent.getTradingManager();
			
			FAST.shell.showMessage("  -> Geting Orchestrator...");	
			TradingOrchestrator tradingOrchestrator = tradingManager.getTradingOrchestrator(this);
			
			tradingOrchestrator.start();
			
		} catch (UnknownComponentException e) {
			FAST.shell.showMessage("FATAL ERROR: Can not obtain a Trading Component.");
			e.printStackTrace();
		}
		
	}

}
