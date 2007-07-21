/**
 * 
 */
package es.us.lsi.tdg.fast.core.roles.selection;

/**
 * This interface has the responsibility of create a unique point
 *  where all proposals are centralized for its processing. In this
 *  way, role activities are highly linked with work-load regulation. 
 *  
 * This interface receives the agreement proposals generated by the 
 * ProposalBuilder as well as the agreement proposals coming from
 * other parties through the Proponent role and submits them to the
 *  ProposalFilter. When an external proposal is received, this role 
 *  can decide to ask for specific information to the information 
 *  organization Additionally, it can develop some work-load issues 
 *  with proposals; e.g. kept them until an event occurs: they can 
 *  be collected until the negotiation phase finishes.
 *  
 * @author Jos� Antonio Parejo Maestre
 * @author Pablo Fern�ndez Montes
 *
 */
public interface ProposalCollector {

}
