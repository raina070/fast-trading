/**
 * 
 */
package es.us.lsi.tdg.fast.components.discovery.tracker;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.roles.ProcessingModel;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.discovery.tracker.TrackerInquirerAdaptor;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;



/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
  
public class PullTrackerInquirerAdaptor implements TrackerInquirerAdaptor
{
	private Set<CounterParty> detectedCounterPartySet;	 
	 	
	public PullTrackerInquirerAdaptor()
	{
		detectedCounterPartySet=new HashSet<CounterParty>();
	}
	 
	 public ProcessingModel getProcessingModel() {		
		return ProcessingModel.PULL;
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

}
