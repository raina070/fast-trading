package es.us.lsi.tdg.fast.core.choreographies.wiring;

import es.us.lsi.tdg.fast.components.information.inquirer.PushInquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.components.selection.proposalBuilder.PushProposalBuilderInquirerAdaptor;

public class PushNewInformationNotification extends NewInformationNotification {
	public PushNewInformationNotification()
	{
		this.name="PushNewInformationNotification";
		proposalBuilderInquirerAdaptor=new PushProposalBuilderInquirerAdaptor();
		inquirerProposalBuilderAdaptor=new PushInquirerProposalBuilderAdaptor(proposalBuilderInquirerAdaptor);
		
	}
}
