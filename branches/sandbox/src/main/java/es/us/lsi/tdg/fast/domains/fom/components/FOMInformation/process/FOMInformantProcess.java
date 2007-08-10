package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.information.Informant;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.services.BaseFASTService;
import es.us.lsi.tdg.fast.core.services.FASTService;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.services.DiscoveryServiceImplementation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.services.InformantServiceImplementation;

public class FOMInformantProcess extends AbstractControllableProcess {

	private FOMInformation informationComponent;
	
	public FOMInformantProcess(FOMInformation informationComponent) {
		super();
		this.informationComponent = informationComponent;
	}


	@Override
	public void start() {
			
		FASTService service = new BaseFASTService(informationComponent);

		service.setImplementation(InformantServiceImplementation.class);
		
		FAST.server.publishService(service);

		super.start();
		
	}

	
	@Override
	protected void run() {
		stop();	
	}

}
