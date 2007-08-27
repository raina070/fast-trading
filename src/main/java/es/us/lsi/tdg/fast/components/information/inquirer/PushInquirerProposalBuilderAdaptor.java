/**
 * 
 */
package es.us.lsi.tdg.fast.components.information.inquirer;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.choreographies.IllegalChoreographyMethodCallException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.roles.InteractionModel;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class PushInquirerProposalBuilderAdaptor implements  InquirerProposalBuilderAdaptor {

	protected ProposalBuilder 				proposalBuilder;	
	public PushInquirerProposalBuilderAdaptor(ProposalBuilder proposalBuilder)
	{
		this.proposalBuilder=proposalBuilder;		
	}
	
	public void setProposalBuilder(ProposalBuilder proposalBuilder)
	{
		this.proposalBuilder=proposalBuilder;
	}
	
	
	public void newInformation(CounterPartyKnowledge info) {
		proposalBuilder.newInformation(info);		
	}
	
	public Set<CounterPartyKnowledge> getInformation() {		
		//proposalBuilder.newInformation(info);
		throw new IllegalChoreographyMethodCallException();		
	}
	
	public void potentialCounterParties(Set<CounterParty> counterPartySet){
		throw new IllegalChoreographyMethodCallException();
	}

	public InteractionModel getInteractionModel() {		
		return InteractionModel.PUSH;
	}
}
