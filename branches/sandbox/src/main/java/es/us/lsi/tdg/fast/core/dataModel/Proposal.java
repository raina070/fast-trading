/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel;

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
	 * A collection of variation points referring to some terms 
	 * specified in the proposal. These variation terms can be used 
	 * as guidelines to facilitate the process of finding an agreement 
	 * through the exchange of proposals.
	 */
	public Set<VariationPoint> getVariationPoints();
	/**
	 * Express the intention of the sender about that proposal. 
	 * That is to say, if the proposal is a binding one or if it 
	 * represent the acceptance or rejection of a previously made 
	 * proposal.
	 */
	public ProposalPerformative getPerformative();
	
}
