/**
 * 
 */
package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.SortedSet;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.agreement.ProposalPerformative;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMaker;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 * @author Antonio Manuel Gutierrez Fernandez
 *
 */
public class FOMProposalCollectorProcess extends AbstractControllableProcess {
	private FOMSelection 	selectionComponent;
	
	public FOMProposalCollectorProcess() {
		this("FOMProposalCollector");				
	}

	public FOMProposalCollectorProcess(String threadName)
	{
		super(threadName);		
	}
		
	public FOMProposalCollectorProcess(FOMSelection selectionComponent) {
		super("FOMProposalCollector");
		this.selectionComponent = selectionComponent;
	}
	
	
	@Override
	protected  void  run()
	{
		Set<Proposal> proposals=getUnprocessedProposal();
		for(Proposal proposal:proposals){
			proposal.setPerformative(ProposalPerformative.COMMIT);
			selectionComponent.getSortedProposalSet().add(proposal);
		}
	}

	private Set<Proposal> getUnprocessedProposal() {
		// TODO Auto-generated method stub
		return null;
	}

}
