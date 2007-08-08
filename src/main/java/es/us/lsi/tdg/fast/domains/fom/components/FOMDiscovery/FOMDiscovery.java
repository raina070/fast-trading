package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery;

import es.us.lsi.tdg.fast.components.Component;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.discovery.tracker.TrackerInquirerAdaptor;

public class FOMDiscovery implements Component {

	// Adapters for the offered roles: 
	protected TrackerInquirerAdaptor trackerInquirerAdaptor;
	
	// Processes associated to the offered roles:
	protected ControllableProcess trackerProcess;
	
	private String name="FOMDiscovery";
	private String type="Discovery";
	
	public String getName() {
		return name;
	}

	public Tracker getTracker() {
		return trackerInquirerAdaptor;
	}

	public ControllableProcess getTrackerProcess() {
		return trackerProcess;
	}

	public String getType() {
		return type;
	} 
	

}
