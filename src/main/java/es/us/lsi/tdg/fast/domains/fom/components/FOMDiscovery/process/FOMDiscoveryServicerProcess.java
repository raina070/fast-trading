package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.process.OLDAbstractControllableProcess;
import es.us.lsi.tdg.fast.core.process.ProcessModel;
import es.us.lsi.tdg.fast.core.process.event.EventBroker;
import es.us.lsi.tdg.fast.core.process.event.FASTProcessEvent;
import es.us.lsi.tdg.fast.core.process.event.FASTProcessEventType;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.services.BaseFASTService;
import es.us.lsi.tdg.fast.core.services.FASTService;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.services.DiscoveryServiceImplementation;

/**
 * 
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class FOMDiscoveryServicerProcess extends AbstractControllableProcess{
	FASTService service;
	private Inquirer inquirer;
	
	private FOMDiscovery discoveryComponent;
	
	public FOMDiscoveryServicerProcess() {
		this("FOMDiscoveryService");				
	}

	public FOMDiscoveryServicerProcess(String threadName)
	{
		super(threadName);
	}
		
	public FOMDiscoveryServicerProcess(FOMDiscovery discoveryComponent) {
		this("FOMDiscoveryService");
		this.discoveryComponent = discoveryComponent;
		
		service =  new BaseFASTService(discoveryComponent);
		service.setImplementation(DiscoveryServiceImplementation.class);
		
	}
	
	public  void  setUp(){
		FAST.server.publishService(service);
	}	

	protected void cleanUp(){
		FAST.server.unpublishService(service);
	}

	@Override
	protected void run() {
		// EMPTY because is a "service only" process
	}
	
}
