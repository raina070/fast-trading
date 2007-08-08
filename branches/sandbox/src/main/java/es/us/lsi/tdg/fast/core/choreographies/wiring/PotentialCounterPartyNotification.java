package es.us.lsi.tdg.fast.core.choreographies.wiring;

import es.us.lsi.tdg.fast.core.choreographies.AbstractChoreography;
import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.roles.discovery.tracker.TrackerInquirerAdaptor;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerTrackerAdaptor;

public abstract class PotentialCounterPartyNotification extends AbstractChoreography
		implements Choreography {

	protected InquirerTrackerAdaptor inquirerTrackerAdaptor;
	protected TrackerInquirerAdaptor trackerInquirerAdaptor;
	
	public PotentialCounterPartyNotification() {
		super();
		this.name = "AbstractPotentialCounterPartyNotification";
		this.type = "PotentialCounterPartyNotification";
		this.participants.add("Tracker");
		this.participants.add("Inquirer");		
	}

	public InquirerTrackerAdaptor getInquirerTrackerAdaptor() {
		return inquirerTrackerAdaptor;
	}

	public TrackerInquirerAdaptor getTrackerInquirerAdaptor() {
		return trackerInquirerAdaptor;
	}
	
	
}
