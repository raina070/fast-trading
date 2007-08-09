package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection;

import java.util.SortedSet;
import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.wiring.NewInformationNotification;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.component.UnwiredComponent;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilderInquirerAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcher;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcherAgreementMakerAdaptor;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process.FOMProposalBuilderProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process.FOMProposalDispatcherProcess;

public class FOMSelection implements Component {

	protected SortedSet<Proposal> 		proposalSet;
	protected String 					name = "FOMSelection";
	protected String 					type = "Selection";
	TradingProcess 						tradingProcess = null;

	protected ControllableProcess proposalBuilderProcess=null;
	protected ControllableProcess proposalDispatcherProcess=null;
	
	// Adapters for the offered roles: 
	
	protected ProposalBuilderInquirerAdaptor 			proposalBuilder;
	protected ProposalDispatcherAgreementMakerAdaptor 	proposalDispatcher;
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setWiringChoreography(Choreography wiringChoreography) {
		String woType = wiringChoreography.getType();
		if (woType.equals("NewInformationNotification"))
			this.proposalBuilder = ((NewInformationNotification) wiringChoreography).getProposalBuilderInquirerAdaptor();
		
	}

	public ControllableProcess getProposalBuilderProcess() {		
		if(proposalBuilder == null){
			throw new UnwiredComponent("proposalBuilderInquirerAdaptor");
		}else
			this.proposalBuilderProcess = new FOMProposalBuilderProcess(this.proposalBuilder);
			
		return proposalBuilderProcess;
	}
	
	public ControllableProcess getProposalDispatcherProcess() {		
		if(proposalDispatcher == null){
			throw new UnwiredComponent("proposalDispatcherAgreementMakerAdaptor");
		}else
			this.proposalBuilderProcess = new FOMProposalDispatcherProcess(this.proposalDispatcher);
			
		return proposalBuilderProcess;
	}
	
	
	public void setTradingProcess(TradingProcess tradingProcess) {
		this.tradingProcess = tradingProcess;	
	}

	public void setSortedProposalSet(SortedSet<Proposal> proposalSet) {
		this.proposalSet = proposalSet;
	}
	
	public SortedSet<Proposal> getSortedProposalSet() {
		return proposalSet;
	}
	
	public ProposalBuilder getProposalBuilder(){
		return proposalBuilder;
	}
	
	public ProposalDispatcher getProposalDispatcher(){
		return proposalDispatcher;
	}
}
