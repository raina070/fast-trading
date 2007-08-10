package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
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
	
	private Inquirer inquirer;
	
	private FOMDiscovery discoveryComponent;
	
	public FOMDiscoveryServicerProcess(Inquirer inquirer) {
		this("FOMTracker",inquirer);				
	}

	public FOMDiscoveryServicerProcess(String threadName,Inquirer inquirer)
	{
		super(threadName);
		this.inquirer = inquirer;
	}
		
	public FOMDiscoveryServicerProcess(FOMDiscovery discoveryComponent) {
		this((Inquirer) discoveryComponent.getTracker());
		this.discoveryComponent = discoveryComponent;
	}

	
	
	public  void  start()
	{
		@SuppressWarnings("unused")
		
		FASTService service = new BaseFASTService(discoveryComponent);

		service.setImplementation(DiscoveryServiceImplementation.class);
		
		FAST.server.publishService(service);

		super.start();
		
	}
	
	
	
	protected  void  run()
	{
						
	}
}
