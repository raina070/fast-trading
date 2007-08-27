package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;

import es.us.lsi.tdg.fast.core.process.OLDAbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMaker;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;

import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalTranslator;

/**
 * 
 * @author Antonio Manuel Gutierrez Fernandez
 *
 */
public class FOMProposalDispatcherProcess extends OLDAbstractControllableProcess {
	
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
		synchronized(proposalSet){
			if(proposalSet.size()>0){
				for (Proposal proposal:proposalSet){
					FAST.shell.showMessage("Dispatching offer... "+FOMProposalTranslator.getFOMProposal(proposal)+ " type " + proposal.getPerformative().toString());
					agreementMaker.createAgreement(proposal);
				}			
				proposalSet.clear();
			}
		}
	}
}

