/**
 * 
 */
package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.SortedSet;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.agreement.ProposalPerformative;
import es.us.lsi.tdg.fast.core.process.OLDAbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMaker;
import es.us.lsi.tdg.fast.core.services.BaseFASTService;
import es.us.lsi.tdg.fast.core.services.FASTService;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.services.DiscoveryServiceImplementation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.services.CollectorServiceImplementation;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 * @author Antonio Manuel Gutierrez Fernandez
 *
 */
public class FOMProposalCollectorProcess extends OLDAbstractControllableProcess {
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
	
	
	public  void  start()
	{
		@SuppressWarnings("unused")
		FASTService service = new BaseFASTService(selectionComponent);

		service.setImplementation(CollectorServiceImplementation.class);
		
		FAST.server.publishService(service);

		super.start();
		
	}
	
	@Override
	protected  void  run()
	{
//		Set<Proposal> proposals=getUnprocessedProposal();
//		for(Proposal proposal:proposals){
//			proposal.setPerformative(ProposalPerformative.COMMIT);
//			selectionComponent.getSortedProposalSet().add(proposal);
//		}
	}


}
