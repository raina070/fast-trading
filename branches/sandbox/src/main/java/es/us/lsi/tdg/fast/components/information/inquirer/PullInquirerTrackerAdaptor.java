/**
 * 
 */
package es.us.lsi.tdg.fast.components.information.inquirer;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.choreographies.IllegalChoreographyMethodCallException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.roles.IllegalAdapterMethodCall;
import es.us.lsi.tdg.fast.core.roles.InteractionModel;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerTrackerAdaptor;



/**
 * @author Pablo Fernandez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
  
public class PullInquirerTrackerAdaptor implements InquirerTrackerAdaptor
{
	 private Set<CounterParty> detectedCounterPartySet;
	 private Set<CounterParty> unprocessedCounterPartySet;
	 private Tracker tracker;
	 	
	public PullInquirerTrackerAdaptor(Tracker tracker)
	{
		this.tracker=tracker;
		detectedCounterPartySet=new HashSet<CounterParty>();
		unprocessedCounterPartySet=new HashSet<CounterParty>();
	}
	 
	 public InteractionModel getInteractionModel() {		
		return InteractionModel.PULL;
	}

	public Set<CounterParty> getNewCounterParties() {
		updateFromTracker();
		Set<CounterParty> result;
		synchronized(unprocessedCounterPartySet)
		{
			result=new HashSet<CounterParty>(unprocessedCounterPartySet);
			unprocessedCounterPartySet.clear();
		}		
		return result;
	}

	public Set<CounterParty> getPotentialCounterParties() {
		updateFromTracker();
		return detectedCounterPartySet;
	}
	
	private void updateFromTracker()
	{
		
		Set<CounterParty> totalTracked=tracker.getPotentialCounterParties();		
		Set<CounterParty> newUnprocessed=new HashSet<CounterParty>();
		synchronized(unprocessedCounterPartySet){			
			newUnprocessed.addAll(totalTracked);
			newUnprocessed.removeAll(detectedCounterPartySet);
			detectedCounterPartySet.addAll(totalTracked);
			unprocessedCounterPartySet.addAll(newUnprocessed);
		}
		
	}

	public void potentialCounterParties(Set<CounterParty> counterParties){
		throw new IllegalAdapterMethodCall();
	}

	public Set<CounterPartyKnowledge> getInformation() {		
		throw new IllegalChoreographyMethodCallException();
	}

}
