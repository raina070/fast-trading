package es.us.lsi.tdg.fast.components.selection.proposalDispatcher;

import java.util.List;
import java.util.Set;

import es.us.lsi.tdg.fast.core.choreographies.IllegalChoreographyMethodCallException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.roles.InteractionModel;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMaker;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcherAgreementMakerAdaptor;

public class PushProposalDispatcherAgreemetMakerAdaptor implements
		ProposalDispatcherAgreementMakerAdaptor {
	private AgreementMaker agreementMaker;
	
	public PushProposalDispatcherAgreemetMakerAdaptor(
			AgreementMaker agreementMaker) {		
		this.agreementMaker = agreementMaker;
	}

	public void dispatch(List<Proposal> proposals) {
		throw new IllegalChoreographyMethodCallException();

	}

	public List<Proposal> getProposalsDispatched() {
		throw new IllegalChoreographyMethodCallException();
	}

	public void createAgreement(Proposal proposal) {
		agreementMaker.createAgreement(proposal);
	}

	public Set<Agreement> createdAgreements() {
		return agreementMaker.createdAgreements();
	}

	public InteractionModel getInteractionModel() {
		return InteractionModel.PUSH;
	}

}
