package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.wiring.PotentialCounterPartyNotification;
import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.component.UnwiredComponent;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.discovery.tracker.TrackerInquirerAdaptor;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process.FOMTrackerProcess;

public class FOMDiscovery implements Component {

	// Adapters for the offered roles: 
	protected TrackerInquirerAdaptor trackerInquirerAdaptor=null;
	
	// Processes associated to the offered roles:
	protected ControllableProcess trackerProcess=null;
	
	private String name="FOMDiscovery";
	private String type="Discovery";
	TradingProcess tradingProcess = null;
	
	public String getName() {
		return name;
	}

	public Tracker getTracker() {
		return trackerInquirerAdaptor;
	}

	public ControllableProcess getTrackerProcess() {
		
		if(trackerInquirerAdaptor == null){
			throw new UnwiredComponent("trackerInquirerAdaptor");
		}else
			this.trackerProcess = new FOMTrackerProcess(this.trackerInquirerAdaptor);
		
		return trackerProcess;
	}

	public String getType() {
		return type;
	}

	public void setWiringChoreography(Choreography wiringChoreography) {
		String woType = wiringChoreography.getType();
		
		if (woType.equals("PotentialCounterPartyNotification"))
			this.trackerInquirerAdaptor = ((PotentialCounterPartyNotification) wiringChoreography).getTrackerInquirerAdaptor();
		
		// TODO Auto-generated method stub
	}

	public void setTradingProcess(TradingProcess tradingProcess) {
		this.tradingProcess = tradingProcess;
	}

	public TradingProcess getTradingProcess() {		
		return tradingProcess;
	} 
	

}
