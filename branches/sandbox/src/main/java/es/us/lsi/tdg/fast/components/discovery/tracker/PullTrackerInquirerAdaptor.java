/**
 * 
 */
package es.us.lsi.tdg.fast.components.discovery.tracker;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.choreographies.IllegalChoreographyMethodCallException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.roles.InteractionModel;
import es.us.lsi.tdg.fast.core.roles.discovery.tracker.TrackerInquirerAdaptor;



/**
 * @author Pablo Fernandez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
  
public class PullTrackerInquirerAdaptor implements TrackerInquirerAdaptor
{
	private Set<CounterParty> detectedCounterPartySet;	 
	 	
	public PullTrackerInquirerAdaptor()
	{
		detectedCounterPartySet=new HashSet<CounterParty>();
	}
	 
	 public InteractionModel getInteractionModel() {		
		return InteractionModel.PULL;
	}

	/** TRACKER INTERFACE **/
	public Set<CounterParty> getNewCounterParties() {
		return null;
	}

	public Set<CounterParty> getPotentialCounterParties() {
		return detectedCounterPartySet;
	}

	/** INQUIRER INTERFACE **/
	public void potentialCounterParties(Set<CounterParty> counterParties) {
		detectedCounterPartySet.addAll(counterParties);
	}

	public Set<CounterPartyKnowledge> getInformation() {
		throw new IllegalChoreographyMethodCallException();
	}

}
