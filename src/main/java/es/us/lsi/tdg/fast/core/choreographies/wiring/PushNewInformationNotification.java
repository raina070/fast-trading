package es.us.lsi.tdg.fast.core.choreographies.wiring;

import es.us.lsi.tdg.fast.components.information.inquirer.PushInquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.components.selection.proposalBuilder.PullProposalBuilderInquirerAdaptor;

public class PushNewInformationNotification extends NewInformationNotification {
	public PushNewInformationNotification()
	{
		proposalBuilderInquierrAdaptor=new PullProposalBuilderInquirerAdaptor();
		inquirerProposalBuilderAdaptor=new PushInquirerProposalBuilderAdaptor(proposalBuilderInquierrAdaptor);
		
	}
}
