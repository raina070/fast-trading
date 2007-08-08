package es.us.lsi.tdg.fast.core.choreographies.wiring;

import es.us.lsi.tdg.fast.components.agreementMaking.PullAgreementMakerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.components.selection.proposalDispatcher.PushProposalDispatcherAgreemetMakerAdaptor;

public class PushProposalSelectionNotification extends
		ProposalSelectionNotification {
	public PushProposalSelectionNotification()
	{
		this.name="PushProposalSelectionNotification";
		agreementMakerProposalDispatcherAdaptor=new PullAgreementMakerProposalBuilderAdaptor();
		proposalDispatcherAgreemenMakerAdaptor=new PushProposalDispatcherAgreemetMakerAdaptor(agreementMakerProposalDispatcherAdaptor);
	}
}
