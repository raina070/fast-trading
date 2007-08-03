/**
 * 
 */
package es.us.lsi.tdg.fast.components.information.inquirer;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.ProcessingModel;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerTrackerAdaptor;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;



/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
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
	 
	 public ProcessingModel getProcessingModel() {		
		return ProcessingModel.PULL;
	}

	public Set<CounterParty> getNewCounterParties() {
		updateFromTracker();
		return unprocessedCounterPartySet;
	}

	public Set<CounterParty> getPotentialCounterParties() {
		updateFromTracker();
		return detectedCounterPartySet;
	}
	
	private void updateFromTracker()
	{
		Set<CounterParty> totalTracked=tracker.getPotentialCounterParties();
		Set<CounterParty> newUnprocessed=new HashSet<CounterParty>(totalTracked);
		newUnprocessed.removeAll(detectedCounterPartySet);
		detectedCounterPartySet.addAll(totalTracked);
		unprocessedCounterPartySet.addAll(newUnprocessed);
	}

}
