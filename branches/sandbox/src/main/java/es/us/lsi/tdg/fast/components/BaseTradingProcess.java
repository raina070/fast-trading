package es.us.lsi.tdg.fast.components;

import es.us.lsi.tdg.fast.FAST;

public class BaseTradingProcess implements TradingProcess{

	private Integer PID;
	private String orchName;
	
	public BaseTradingProcess(Integer PID, String orchName) {
		this.PID = PID;
		this.orchName = orchName;
	}

	public void start() {
		FAST.shell.showMessage("Starting Trading Process...");
		FAST.log.info("Obtaining Trading Manager...");

		try {
			Component tradingComponent = FAST.componentFactory.getComponentByName("BaseTradingComponent");
			FAST.shell.showMessage("  -> Geting Orchestrator...");
			
			//TODO: tradingComponent.g
			
			
			
			
			
		} catch (UnknownComponentException e) {
			FAST.shell.showMessage("FATAL ERROR: Can not obtain a Trading Component.");
			e.printStackTrace();
		}
		
	}

}
