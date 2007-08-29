package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.SortedSet;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMaker;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalTranslator;

/**
 * 
 * @author Antonio Manuel Gutierrez Fernandez
 *
 */
public class FOMProposalDispatcherProcess extends AbstractControllableProcess {
	
	private AgreementMaker 	agreementMaker;
	private FOMSelection 	selectionComponent;
	
	public FOMProposalDispatcherProcess(FOMSelection selectionComponent) {
		super("FOMProposalDispatcher");
		this.agreementMaker =(AgreementMaker) selectionComponent.getProposalDispatcher();
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

