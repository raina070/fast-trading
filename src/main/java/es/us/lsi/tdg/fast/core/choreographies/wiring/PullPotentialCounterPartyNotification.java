package es.us.lsi.tdg.fast.core.choreographies.wiring;

import es.us.lsi.tdg.fast.components.discovery.tracker.PullTrackerInquirerAdaptor;
import es.us.lsi.tdg.fast.components.information.inquirer.PullInquirerTrackerAdaptor;

public class PullPotentialCounterPartyNotification extends
		PotentialCounterPartyNotification {

	public PullPotentialCounterPartyNotification(){
		name = "PullPotentialCounterPartyNotification";
		trackerInquirerAdaptor = new PullTrackerInquirerAdaptor();
		inquirerTrackerAdaptor = new PullInquirerTrackerAdaptor(trackerInquirerAdaptor);
	}
	
}
