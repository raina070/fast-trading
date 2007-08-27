/**
 * 
 */
package es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.process.ControllableProcess;
/**
 * 
 * This interface has the responsibility of create the best possible 
 * proposal for a CounterParty, based on the known information about 
 * that party and the agreement preferences of the user.
 * 
 * It creates agreement proposals based on the information gathered 
 * by the information organisation. Then it sends these agreements 
 * proposals to the Proposal collector.
 * 
 * @author José Antonio Parejo Maestre
 * @author Pablo Fernández Montes
 *
 */
public interface ProposalBuilder {
	/**
	 * 
	 * @param info InformationSet refers to the set of information (CP,
	 *  Service and Trading) concerning from a single and explicit 
	 *  counterparty for the current trading process.
	 */	
	public void newInformation(CounterPartyKnowledge info);
}
