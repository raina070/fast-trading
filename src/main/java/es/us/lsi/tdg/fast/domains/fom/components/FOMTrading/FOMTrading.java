package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.component.trading.TradingComponent;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;


public class FOMTrading implements TradingComponent{

	protected String name = "FOMTrading";
	protected String type = "Trading";
	protected TradingManager tradingManager;
	TradingProcess tradingProcess = null;
	
	public FOMTrading(){
		tradingManager = new FOMTradingManager();
	}
	
	public String getName() {
		return name;
	}
	
	/* 
	 * @see es.us.lsi.tdg.fast.components.trading.TradingComponent#getTradingManager()
	 */
	public TradingManager getTradingManager()
	{
		return tradingManager;
	}

	public String getType() {
		return type;
	}

	public void setWiringChoreography(Choreography wiringChoreography) {
		// TODO Auto-generated method stub
		
	}

	public void setTradingProcess(TradingProcess tradingProcess) {
		this.tradingProcess = tradingProcess;
		
	}

	public TradingProcess getTradingProcess() {		
		return tradingProcess;
	}
	
}
