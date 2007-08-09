/**
 * 
 */
package es.us.lsi.tdg.fast.components.selection.proposalBuilder;

import java.util.Set;

import es.us.lsi.tdg.fast.core.choreographies.IllegalChoreographyMethodCallException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.roles.ProcessingModel;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilderInquirerAdaptor;

/**
 * @author Pablo Fernandez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
public class PullProposalBuilderInquirerAdaptor implements
		ProposalBuilderInquirerAdaptor {
	
	private Set<CounterPartyKnowledge> information;
	
	public PullProposalBuilderInquirerAdaptor()
	{	
	}
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder#newInformation(es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge)
	 */
	public void newInformation(CounterPartyKnowledge info) {		
		information.add(info);
	}
	
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.roles.RoleAdaptor#getProcessingModel()
	 */
	public ProcessingModel getProcessingModel() {
		return ProcessingModel.PULL;
	}
	public Set<CounterPartyKnowledge> getInformation() {
		return information;
	}
	public void potentialCounterParties(Set<CounterParty> counterParties) {
		throw new IllegalChoreographyMethodCallException();		
	}

}