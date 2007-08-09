package es.us.lsi.tdg.fast.core.component;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public interface Component {

	public String getName();
	public String getType();
	public void setTradingProcess(TradingProcess tradingProcess);
	public TradingProcess getTradingProcess();
	public void setWiringChoreography(Choreography wiringChoreography);
	
}
