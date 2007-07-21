/**
 * 
 */
package es.us.lsi.tdg.fast.core.roles.selection;

/**
 * 
 * This interface has the responsibility of process the set of 
 * potential proposals in order to filter and rank them.
 * 
 * Proposal filter role is in charge of filtering the agreement 
 * proposals collected by the Proposal collector and the non-successful 
 * offers coming from the Binding Organisation. The filter criteria 
 * are not unique but, in most cases, they depend on the preferences 
 * given by the user, the CounterPartyModel (as part of the world 
 * model) and the status of the whole service trading process. In this 
 * process, each proposal is evaluated in order to obtain its utility 
 * for the user. In so doing, a classification is made based on this 
 * utility creating a ranking that can be used in further stages 
 * of the trading process; particularly in the process of dispatching 
 * them to the AgreementMakers. After the process, several proposals 
 * are rejected and the others (with its ranking) are sent to the 
 * ProposalDispatcher.
 * 
 * @author José Antonio Parejo Maestre
 * @author Pablo Fernández Montes
 *
 */
public interface ProposalFilter {

}
