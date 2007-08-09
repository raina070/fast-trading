package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.wiring.PotentialCounterPartyNotification;
import es.us.lsi.tdg.fast.core.choreographies.wiring.NewInformationNotification;
import es.us.lsi.tdg.fast.core.component.information.InformationComponent;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerTrackerAdaptor;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public class FOMInformation implements InformationComponent {


	protected String name = "FOMInformation";
	protected String type = "Information";
	TradingProcess tradingProcess = null;

	// Adapters for the offered roles: 
	protected InquirerTrackerAdaptor inquirerTrackerAdaptor;
	protected InquirerProposalBuilderAdaptor inquirerProposalBuilderAdaptor;
	
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
		else if (woType.equals("NewInformationNotification")){
			this.inquirerProposalBuilderAdaptor = ((NewInformationNotification) wiringChoreography).getInquirerProposalBuilderAdaptor();
		}
	}

	public void setTradingProcess(TradingProcess tradingProcess) {
		this.tradingProcess = tradingProcess;	
	}

	public Inquirer getInquirerTrackerAdaptor(){
		return inquirerTrackerAdaptor;
	}
	
	public Inquirer getInquirerProposalBuilderAdaptor(){
		return inquirerProposalBuilderAdaptor;
	}
}
