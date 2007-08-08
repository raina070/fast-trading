package es.us.lsi.tdg.fast.core.component;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public interface Component {

	String getName();
	String getType();
	void setTradingProcess(TradingProcess tradingProcess);
	void setWiringChoreography(Choreography wiringChoreography);
	
}
