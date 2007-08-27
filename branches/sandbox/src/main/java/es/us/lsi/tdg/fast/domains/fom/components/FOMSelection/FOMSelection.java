package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection;

import java.util.SortedSet;
import java.util.TreeSet;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.UnknownChoreographyException;
import es.us.lsi.tdg.fast.core.choreographies.wiring.NewInformationNotification;
import es.us.lsi.tdg.fast.core.choreographies.wiring.ProposalSelectionNotification;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.component.UnwiredComponent;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.process.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilderInquirerAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcher;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcherAgreementMakerAdaptor;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process.FOMProposalBuilderProcess;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMGenericComparator;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process.FOMProposalCollectorProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process.FOMProposalDispatcherProcess;

public class FOMSelection implements Component {

	
	protected String 					name = "FOMSelection";
	protected String 					type = "Selection";
	TradingProcess 						tradingProcess = null;

	protected ControllableProcess proposalBuilderProcess=null;
	protected ControllableProcess proposalDispatcherProcess=null;
	protected ControllableProcess proposalCollectorProcess=null;
	
	// Adapters for the offered roles: 
	
	protected ProposalBuilderInquirerAdaptor 			proposalBuilder;
	protected ProposalDispatcherAgreementMakerAdaptor 	proposalDispatcher;
	protected FOMGenericComparator FOMGenericComparator;
	protected SortedSet<Proposal> 		proposalSet =new TreeSet<Proposal>(FOMGenericComparator);;
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	
	
	
	public FOMSelection() {
		FOMGenericComparator FOMComparator = new FOMGenericComparator();
		this.proposalSet = new TreeSet<Proposal>(FOMComparator);
	}

	public void setWiringChoreography(Choreography wiringChoreography) {
		String woType = wiringChoreography.getType();
		if (woType.equals("NewInformationNotification"))
			this.proposalBuilder = ((NewInformationNotification) wiringChoreography).getProposalBuilderInquirerAdaptor();
		else if (woType.equals("ProposalSelectionNotification"))
			this.proposalDispatcher = ((ProposalSelectionNotification) wiringChoreography).getProposalDispatcherAgreemenMakerAdaptor();
		else throw new UnknownChoreographyException();
		
	}

	public ControllableProcess getProposalBuilderProcess() {		
		if(proposalBuilder == null){
			throw new UnwiredComponent("proposalBuilderInquirerAdaptor");
		}else
			this.proposalBuilderProcess = new FOMProposalBuilderProcess(this);
			
		return proposalBuilderProcess;
	}
	
	public ControllableProcess getProposalDispatcherProcess() {		
		if(proposalDispatcher == null){
			throw new UnwiredComponent("proposalDispatcherAgreementMakerAdaptor");
		}else
			this.proposalBuilderProcess = new FOMProposalDispatcherProcess(this);
			
		return proposalBuilderProcess;
	}

	public ControllableProcess getProposalCollectorProcess() {		
		if(proposalCollectorProcess == null)
			this.proposalCollectorProcess = new FOMProposalCollectorProcess(this);
			
		return proposalCollectorProcess;
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

	public TradingProcess getTradingProcess() {		
		return tradingProcess;
	}
}
