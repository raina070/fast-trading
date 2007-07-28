/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.agreement;

import java.util.Set;

/**
 * A proposal is an offer for an agreement made by one party to its
 * CounterParty. We can understand a proposal as a type of agreement 
 * where some terms are left open in order to be refined in later 
 * interactions amongst the parties. 
 * 
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 */
public interface Proposal extends Agreement {
	/**
	 * Express the intention of the sender about that proposal. 
	 * That is to say, if the proposal is a binding one or if it 
	 * represent the acceptance or rejection of a previously made 
	 * proposal.
	 */
	public ProposalPerformative getPerformative();
	
}
