package es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.process;

import java.util.List;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcher;
import es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.FOMAgreementMaking;


public class FOMAgreementMakerProcess extends AbstractControllableProcess {
	private ProposalDispatcher	proposalDispatcher;
	
	private FOMAgreementMaking	agreementMakingComponent;
	
	public FOMAgreementMakerProcess(ProposalDispatcher proposalDispatcher) {
		this("FOMAgreementMaker",proposalDispatcher);				
	}

	public FOMAgreementMakerProcess(String threadName, ProposalDispatcher proposalDispatcher)
	{
		super(threadName);
		this.proposalDispatcher 	= proposalDispatcher;

	}
		
	public FOMAgreementMakerProcess(FOMAgreementMaking agreementMakingComponent) {
		this((ProposalDispatcher) agreementMakingComponent.getAgreementMaker());
		this.agreementMakingComponent = agreementMakingComponent;
	}
	
	
	@Override
	protected  void  run()
	{
		List<Proposal> proposalSet = proposalDispatcher.getProposalsDispatched(agreementMakingComponent.getAgreementMaker());
		
		stop();	
	}
	
	
}
