/**
 * 
 */
package es.us.lsi.tdg.fast.core.roles.selection;

/**
 * 
 * This interface has the responsibility of decide for each of the 
 * promising proposals which AgreementMaker is most appropriate to 
 * handle it. One system may have several AgreementMakers with 
 * different characteristics and one of them may be better than the 
 * others for certain conditions. For instance one Agreement Maker can 
 * implement auction protocols, another one can implement bilateral 
 * negotiation protocols, and another one can implement just a 
 * take-it-or-leave-it protocol. The proposal dispatcher is the only 
 * role of the selection organisation that knows the features of each 
 * of the agreement maker and, therefore can assign each proposal to 
 * one of them.
 * 
 * After receiving, a ranking of filtered proposals, this role performs
 * a decision The Proposal dispatcher sends the proposal to the 
 * AgreementMaker.
 * 
 * Reasons to assign a proposal to a particular AgreementMaker vary 
 * depending on different aspects; as an example, we can describe the 
 * following:
 * 
 * <ul>
 *  <li>
 *  	The case can correspond to a negotiable Proposal over some 
 *  	terms and the CounterParty has specified a concrete 
 *  	negotiation protocol. In this context, the proposal should be 
 *  	redirected over an AgreementMaker in our organisation that 
 *  	could handle that specific protocol.
 *  </li> 
 *  <li>
 *  	Proposal is not acceptable as is but it can be interesting 
 *  	after a negotiation process. This Proposal can be the basis 
 *  	of the process and should be sent to an AgreementMaker with 
 *  	capabilities to organize such a process.
 *  </li>
 *  <li>
 *  	Proposal has been created by our organization and is very 
 *  	promising; in this way, it shall be dispatched to an 
 *  	agreement maker that will promote it to the counter-party 
 *  	(through our proponent).
 *  </li>
 * </ul>
 * 
 * @author José Antonio Parejo Maestre
 * @author Pablo Fernández Montes
 *
 */
public interface ProposalDisatcher {

}
