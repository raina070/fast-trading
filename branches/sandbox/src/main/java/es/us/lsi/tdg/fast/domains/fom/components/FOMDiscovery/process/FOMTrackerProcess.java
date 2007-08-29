package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;

/**
 * 
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class FOMTrackerProcess extends AbstractControllableProcess{
	
	private Inquirer inquirer;
	
	private FOMDiscovery discoveryComponent;
	
	public FOMTrackerProcess(Inquirer inquirer) {
		this("FOMTracker",inquirer);				
	}

	public FOMTrackerProcess(String threadName,Inquirer inquirer)
	{
		super(threadName);
		this.inquirer = inquirer;
	}
		
	public FOMTrackerProcess(FOMDiscovery discoveryComponent) {
		this((Inquirer) discoveryComponent.getTracker());
		this.discoveryComponent = discoveryComponent;
	}

	protected  void  run()
	{
		
		Set<CounterParty> providers = discoveryComponent.getFOMProviders();
		Set<CounterParty> providersToBeSent;
		
		synchronized(providers){
			providersToBeSent=new HashSet<CounterParty>(providers);
			providers.clear();
		}
		
		inquirer.potentialCounterParties(providersToBeSent);	
	}
}
