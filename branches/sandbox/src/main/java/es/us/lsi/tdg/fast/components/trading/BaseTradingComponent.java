package es.us.lsi.tdg.fast.components.trading;

import es.us.lsi.tdg.fast.components.Component;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;


public class BaseTradingComponent implements Component{

	protected String name = "BaseTradingComponent";
	
	
	// Processes associated to the offered roles:
	protected ControllableProcess tradingManagerProcess;

	// Controllable Processes of the ofered roles:
	public ControllableProcess getTradingManagerProcess()
	{
		return tradingManagerProcess;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	//TODO To decide the best way of obtaining trading manager
	/*public ControllableProcess getTradingManager()
	{
		return tradingManager;
	}
	*/
}
