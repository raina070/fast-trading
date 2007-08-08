package es.us.lsi.tdg.fast.core.choreographies.wiring;

import es.us.lsi.tdg.fast.core.choreographies.AbstractChoreography;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMakerProposalDispatcherAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcherAgreemenMakerAdaptor;

public class ProposalSelectionNotification extends AbstractChoreography {
	
	protected ProposalDispatcherAgreemenMakerAdaptor proposalDispatcherAgreemenMakerAdaptor;
	protected AgreementMakerProposalDispatcherAdaptor agreementMakerProposalDispatcherAdaptor;
	
	public ProposalDispatcherAgreemenMakerAdaptor getProposalDispatcherAgreemenMakerAdaptor() {
		return proposalDispatcherAgreemenMakerAdaptor;
	}

	public AgreementMakerProposalDispatcherAdaptor getAgreementMakerProposalDispatcherAdaptor() {
		return agreementMakerProposalDispatcherAdaptor;
	}

	public ProposalSelectionNotification()
	{
		this.name="AbstractProposalSelectionNotification";
		this.type="ProposalSelectionNotification";
		this.participants.add("ProposalDispatcherAgreementMakerAdaptor");
		this.participants.add("AgreementMakerAdaptorProposalDispatcher");
	}
}
