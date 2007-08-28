package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.process.OLDAbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;

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
		inquirer.potentialCounterParties(discoveryComponent.getFOMProviders());	
	}
}
