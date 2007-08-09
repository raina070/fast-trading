package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection;

import java.util.SortedSet;
import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.wiring.NewInformationNotification;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilderInquirerAdaptor;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public class FOMSelection implements Component {

	protected SortedSet<Agreement> 		agreementSet;
	protected String 					name = "FOMSelection";
	protected String 					type = "Selection";
	TradingProcess 						tradingProcess = null;

	// Adapters for the offered roles: 
	
	protected ProposalBuilderInquirerAdaptor proposalBuilderInquirerAdaptor;
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setWiringChoreography(Choreography wiringChoreography) {
		String woType = wiringChoreography.getType();
		
		if (woType.equals("NewInformationNotification"))
			this.proposalBuilderInquirerAdaptor = ((NewInformationNotification) wiringChoreography).getProposalBuilderInquirerAdaptor();
		
	}

	public void setTradingProcess(TradingProcess tradingProcess) {
		this.tradingProcess = tradingProcess;	
	}

	public ProposalBuilder getProposalBuilderInquirerAdaptor(){
		return proposalBuilderInquirerAdaptor;
	}
}
