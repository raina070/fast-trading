package es.us.lsi.tdg.fast.components.trading;

import es.us.lsi.tdg.fast.components.Component;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;


public class BaseTradingComponent implements Component{

	protected String name = "BaseTradingComponent";
	
	// Processes associated to the offered roles:
	protected ControllableProcess tradingManagerProcess;

	// Controllable Processes of the offered roles:
	public ControllableProcess getTradingManagerProcess()
	{
		return tradingManagerProcess;
	}

	public String getName() {
		return name;
	}
	
	public ControllableProcess getTradingManager()
	{
		//TODO return tradingManager;
		return null;
	}
	
}
