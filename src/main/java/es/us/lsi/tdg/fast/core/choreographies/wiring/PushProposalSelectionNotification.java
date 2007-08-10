package es.us.lsi.tdg.fast.core.choreographies.wiring;

import es.us.lsi.tdg.fast.components.agreementMaking.PushAgreementMakerProposalDispatcherAdaptor;
import es.us.lsi.tdg.fast.components.selection.proposalDispatcher.PushProposalDispatcherAgreemetMakerAdaptor;

public class PushProposalSelectionNotification extends
		ProposalSelectionNotification {
	public PushProposalSelectionNotification()
	{
		this.name="PushProposalSelectionNotification";
		agreementMakerProposalDispatcherAdaptor=new PushAgreementMakerProposalDispatcherAdaptor();
		proposalDispatcherAgreemenMakerAdaptor=new PushProposalDispatcherAgreemetMakerAdaptor(agreementMakerProposalDispatcherAdaptor);
	}
}
