/**
 * 
 */
package es.us.lsi.tdg.fast.components.selection.proposalBuilder;

import java.util.HashSet;
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
public class PushProposalBuilderInquirerAdaptor implements
		ProposalBuilderInquirerAdaptor {
	
	private Set<CounterPartyKnowledge> information;
	
	public PushProposalBuilderInquirerAdaptor()
	{	
		information = new HashSet<CounterPartyKnowledge>();
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
		return ProcessingModel.PUSH;
	}
	
	public Set<CounterPartyKnowledge> getInformation() {
		Set<CounterPartyKnowledge> result = new HashSet<CounterPartyKnowledge>(information);
		information.clear();
		return result;
	}
	
	public void potentialCounterParties(Set<CounterParty> counterParties) {
		throw new IllegalChoreographyMethodCallException();		
	}

}