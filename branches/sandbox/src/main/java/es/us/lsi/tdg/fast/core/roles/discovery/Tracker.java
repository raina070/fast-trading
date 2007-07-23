/**
 * 
 */
package es.us.lsi.tdg.fast.core.roles.discovery;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.CounterParty;
import es.us.lsi.tdg.fast.core.ControllableProcess;
/**
 * 
 * This interface has the responsibility of perform an active
 * search to locate potential CounterParties (i.e. interesting 
 * parties to start an agreement search process with).
 * 
 * This interface initiates its activity with a subscription of market events
 * to the MarketMediator. In this subscription, this role 
 * specify a subset of information that is searching based on 
 * the agreement preferences of the user. This process would 
 * have as a result a set of related market events that match 
 * in some way with the subset of information sent. Finally, 
 * with this market events received, the tracker develop a set 
 * of promising counterparties to start a trading process with.
 * 
 * @author José Antonio Parejo Maestre
 * @author Pablo Fernández Montes
 *
 */
public interface Tracker {
	public Set<CounterParty> getPotentialCounterParties();
	public Set<CounterParty> getNewCounterParties();	
}
