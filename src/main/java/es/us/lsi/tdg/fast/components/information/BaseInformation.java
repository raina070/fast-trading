package es.us.lsi.tdg.fast.components.information;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.wiring.PotentialCounterPartyNotification;
import es.us.lsi.tdg.fast.core.component.information.InformationComponent;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerTrackerAdaptor;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public class BaseInformation implements InformationComponent{

	protected String name="BaseInformation";
	protected String type="Information";
	protected TradingProcess tradingProcess;
	
	// Adapters for the offered roles: 
	protected InquirerTrackerAdaptor inquirerTrackerAdaptor;
	protected InquirerProposalBuilderAdaptor inquirerProposalBuilderAdaptor;
	
	public BaseInformation(){
		
	}
	
	public BaseInformation(InquirerTrackerAdaptor inquirerTrackerAdaptor,
			InquirerProposalBuilderAdaptor inquirerProposalBuilderAdaptor,
			ControllableProcess inquirerProcess,
			ControllableProcess informantProcess) {
		super();
		this.inquirerTrackerAdaptor = inquirerTrackerAdaptor;
		this.inquirerProposalBuilderAdaptor = inquirerProposalBuilderAdaptor;
		this.inquirerProcess = inquirerProcess;
		this.informantProcess = informantProcess;
	}
	
	// Processes associated to the offered roles:
	protected ControllableProcess inquirerProcess;
	protected ControllableProcess informantProcess;
	
	
	// Offered Roles
	public Inquirer getInquirer()
	{
		return inquirerTrackerAdaptor;
	}
	
	// Controllable Processes of the offered roles:
	public ControllableProcess getInquirerProcess()
	{
		return inquirerProcess;
	}
	
	public ControllableProcess getInformantProcess()
	{
		return informantProcess;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setWiringChoreography(Choreography wiringChoreography) {
		String woType = wiringChoreography.getType();
		
		if (woType.equals("PotentialCounterPartyNotification"))
			this.inquirerTrackerAdaptor = ((PotentialCounterPartyNotification) wiringChoreography).getInquirerTrackerAdaptor();
		/**
		 else if (woType.equals("CounterPartyKowledgeNotification"))
			this.inquirerProposalBuilderAdaptor = ((CounterPartyKowledgeNotification) wiringChoreography).getProposalBuilderAdaptor();
		**/
		
		
	}

	public void setTradingProcess(TradingProcess tradingProcess) {
		this.tradingProcess = tradingProcess;
		
	}

	public TradingProcess getTradingProcess() {
		return tradingProcess;
	}
	
}
