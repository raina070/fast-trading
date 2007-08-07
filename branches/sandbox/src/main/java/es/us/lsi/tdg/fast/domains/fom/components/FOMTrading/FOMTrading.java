package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.components.Component;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;


public class FOMTrading implements Component{

	protected String name = "FOMTrading";
	protected TradingManager tradingManager;
	
	public FOMTrading(){
		tradingManager = new FOMTradingManager();
	}
	
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.components.trading.TradingComponent#getTradingManager()
	 */
	public TradingManager getTradingManager()
	{
		return tradingManager;
	}
	
}
