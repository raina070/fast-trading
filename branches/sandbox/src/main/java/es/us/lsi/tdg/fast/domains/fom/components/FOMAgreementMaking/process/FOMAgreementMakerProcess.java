package es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.process;

import java.util.List;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.BaseAgreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.agreement.ProposalPerformative;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcher;
import es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.FOMAgreementMaking;


public class FOMAgreementMakerProcess extends AbstractControllableProcess {
	private ProposalDispatcher	proposalDispatcher;
	private boolean proposed;
	private boolean commited;
	
	
	private FOMAgreementMaking	agreementMakingComponent;
	
	public FOMAgreementMakerProcess(ProposalDispatcher proposalDispatcher) {
		this("FOMAgreementMaker",proposalDispatcher);
		proposed=false;
		commited=false;
	}

	public FOMAgreementMakerProcess(String threadName, ProposalDispatcher proposalDispatcher)
	{
		super(threadName);
		this.proposalDispatcher 	= proposalDispatcher;
		commited=false;
		proposed=false;
	}
		
	public FOMAgreementMakerProcess(FOMAgreementMaking agreementMakingComponent) {
		this((ProposalDispatcher) agreementMakingComponent.getAgreementMaker());
		this.agreementMakingComponent = agreementMakingComponent;		
	}
	
	
	@Override
	protected  void  run()
	{
		List<Proposal> proposalSet = proposalDispatcher.getProposalsDispatched(agreementMakingComponent.getAgreementMaker());
		for(Proposal proposal:proposalSet)
		{
			if(proposal.getPerformative()==ProposalPerformative.PROPOSAL && !proposed)
			{
				processProposal(proposal);
				proposalSet.remove(proposal);
				//proposalSet.clear();
				proposed=true;
			}else if(proposal.getPerformative()==ProposalPerformative.COMMIT){
				processCommitProposal(proposal);
				//proposalSet.clear();
			}else if(proposal.getPerformative()==ProposalPerformative.REJECT){
				proposed=false;
			}else if(proposal.getPerformative()==ProposalPerformative.ACCEPT){
				processAcceptProposal(proposal);
			}
		}		
	}
	
	private void processCommitProposal(Proposal proposal)
	{
		if (commited==false) {
			Agreement agreement=new BaseAgreement(proposal.getTerms(),proposal.getCounterParties());
			String PID=getPID();
			FAST.agreementRegistry.addAgreement(PID, agreement);
			proposal.setPerformative(ProposalPerformative.ACCEPT);
			commited = true;
		}else{
			proposal.setPerformative(ProposalPerformative.REJECT);
		}
		
		// TODO SEEEEEEEND
	}
	
	private String getPID()
	{
		String PID=agreementMakingComponent.getTradingProcess().getPID();
		return PID;
	}
	
	private void processAcceptProposal(Proposal proposal)
	{
		Agreement agreement=new BaseAgreement(proposal.getTerms(),proposal.getCounterParties());
		String PID=getPID();
		FAST.agreementRegistry.addAgreement(PID, agreement);
		stop();
	}
	
	private void processProposal(Proposal proposal)
	{

		proposal.setPerformative(ProposalPerformative.COMMIT);
		// TODO SEEEEEENNNNNDDDD!!!!
	}
	
	private boolean compatible(Proposal commited,Proposal toCommit)
	{
		return false;
	}
	
}
