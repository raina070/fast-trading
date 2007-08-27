/**
 * 
 */
package es.us.lsi.tdg.fast.components.selection.proposalBuilder;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.choreographies.IllegalChoreographyMethodCallException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.roles.InteractionModel;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilderInquirerAdaptor;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class PullProposalBuilderInquirerAdaptor implements
		ProposalBuilderInquirerAdaptor {
	
	private Inquirer inquirer;
	
	public PullProposalBuilderInquirerAdaptor()
	{			
	}
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder#newInformation(es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge)
	 */
	public void newInformation(CounterPartyKnowledge info) {		
		throw new IllegalChoreographyMethodCallException();
	}
	
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.roles.RoleAdaptor#getProcessingModel()
	 */
	public InteractionModel getInteractionModel() {
		return InteractionModel.PULL;
	}
	public Set<CounterPartyKnowledge> getInformation() {
		return inquirer.getInformation();		
	}
	public void potentialCounterParties(Set<CounterParty> counterParties) {
		inquirer.potentialCounterParties(counterParties);		
	}

}
