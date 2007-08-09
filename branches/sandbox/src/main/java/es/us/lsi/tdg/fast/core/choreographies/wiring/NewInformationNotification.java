/**
 * 
 */
package es.us.lsi.tdg.fast.core.choreographies.wiring;


import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.core.choreographies.AbstractChoreography;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilderInquirerAdaptor;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public abstract class NewInformationNotification extends AbstractChoreography {
	
	protected InquirerProposalBuilderAdaptor inquirerProposalBuilderAdaptor;
	protected ProposalBuilderInquirerAdaptor proposalBuilderInquirerAdaptor;
	
	public NewInformationNotification()
	{
		this.name="AbstractNewInformationNotification";
		this.type="NewInformationNotification";		
		this.participants.add("Inquirer");
		this.participants.add("ProposalBuilder");
	}

	public InquirerProposalBuilderAdaptor getInquirerProposalBuilderAdaptor() {
		return inquirerProposalBuilderAdaptor;
	}

	public ProposalBuilderInquirerAdaptor getProposalBuilderInquirerAdaptor() {
		return proposalBuilderInquirerAdaptor;
	}
	
	
}
