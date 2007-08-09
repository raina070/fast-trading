package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;

import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMaker;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;

import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;

/**
 * 
 * @author Antonio Manuel Gutierrez Fernandez
 *
 */
public class FOMProposalDispatcherProcess extends AbstractControllableProcess {
	
	private AgreementMaker 	agreementMaker;

	private FOMSelection 	selectionComponent;
	
	public FOMProposalDispatcherProcess(AgreementMaker agreementMaker) {
		this("FOMProposalDispatcher",agreementMaker);				
	}

	public FOMProposalDispatcherProcess(String threadName,AgreementMaker agreementMaker)
	{
		super(threadName);
		this.agreementMaker = agreementMaker;
	}
		
	public FOMProposalDispatcherProcess(FOMSelection selectionComponent) {
		this((AgreementMaker) selectionComponent.getProposalDispatcher());
		this.selectionComponent = selectionComponent;
	}
	
	
	@Override
	protected  void  run()
	{
		SortedSet<Proposal> proposalSet = selectionComponent.getSortedProposalSet();
		
		for (Proposal proposal:proposalSet){
			agreementMaker.createAgreement(proposal);
		}
		stop();	
	}
}

