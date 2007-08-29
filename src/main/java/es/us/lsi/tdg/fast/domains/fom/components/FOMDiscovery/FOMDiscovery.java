package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.wiring.PotentialCounterPartyNotification;
import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.component.UnwiredComponent;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.process.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.discovery.tracker.TrackerInquirerAdaptor;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process.FOMAdvertiserProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process.FOMDiscoveryServicerProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process.FOMTrackerProcess;

public class FOMDiscovery implements Component {

	// Adapters for the offered roles: 
	protected TrackerInquirerAdaptor trackerInquirerAdaptor=null;
	
	// Processes associated to the offered roles:
	protected ControllableProcess trackerProcess=null;
	protected ControllableProcess discoveryServiceProcess=null;
	protected ControllableProcess advertiserProcess=null;
	
	private String name="FOMDiscovery";
	private String type="Discovery";
	private TradingProcess tradingProcess = null;
	private Set<CounterParty> FOMProviders;
	
	public FOMDiscovery(){
		FOMProviders = new HashSet<CounterParty>();		
	}
	
	public Set<CounterParty> getFOMProviders() {
		return FOMProviders;
	}

	public String getName() {
		return name;
	}

	public Tracker getTracker() {
		return trackerInquirerAdaptor;
	}

	public ControllableProcess getDiscoveryServiceProcess() {
		if(discoveryServiceProcess == null)
			discoveryServiceProcess = new FOMDiscoveryServicerProcess(this);
			
		return discoveryServiceProcess;
		
	}
	
	public ControllableProcess getTrackerProcess() {
		
		if(trackerInquirerAdaptor == null){
			throw new UnwiredComponent("trackerInquirerAdaptor");
		}else
			this.trackerProcess = new FOMTrackerProcess(this);
		
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

	public ControllableProcess getAdvertiserProcess() {
		if(advertiserProcess == null)
			advertiserProcess = new FOMAdvertiserProcess(this);
			
		return advertiserProcess;
		
	} 
}
