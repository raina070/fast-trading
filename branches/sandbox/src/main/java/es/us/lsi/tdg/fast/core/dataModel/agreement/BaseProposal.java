package es.us.lsi.tdg.fast.core.dataModel.agreement;

import java.util.Set;

public class BaseProposal extends BaseAgreement implements Proposal{

	private ProposalPerformative proposalPerformative;
		
	public BaseProposal(Set<Term> terms, Set<CounterParty> counterParties, ProposalPerformative proposalPerformative) {
		super(terms, counterParties);
		this.proposalPerformative = proposalPerformative;		
	}

	public ProposalPerformative getPerformative() {		
		return proposalPerformative;
	}
	
	public void setPerformative(ProposalPerformative value)
	{
		proposalPerformative=value;
	}
	

}
