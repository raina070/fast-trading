package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.components.trading.TradingComponent;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;


public class FOMTrading implements TradingComponent{

	protected String name = "FOMTrading";
	protected String type = "Trading";
	protected TradingManager tradingManager;
	
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
	
}
