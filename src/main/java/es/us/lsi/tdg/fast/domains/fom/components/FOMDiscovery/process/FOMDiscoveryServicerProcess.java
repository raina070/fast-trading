package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
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
	
	@SuppressWarnings("unused")
	private FOMDiscovery discoveryComponent;
	
	private FASTService service;
	
	public FOMDiscoveryServicerProcess(FOMDiscovery discoveryComponent) {
		
		super("FOMDiscoveryService");
		
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
