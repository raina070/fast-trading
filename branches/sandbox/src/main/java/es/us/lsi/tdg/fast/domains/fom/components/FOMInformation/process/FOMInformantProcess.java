package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.services.BaseFASTService;
import es.us.lsi.tdg.fast.core.services.FASTService;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.services.InformantServiceImplementation;

public class FOMInformantProcess extends AbstractControllableProcess {

	@SuppressWarnings("unused")
	private FOMInformation informationComponent;
	
	private FASTService service;
	
	public FOMInformantProcess(FOMInformation informationComponent) {
		super("FOMInformantProcess");
		this.informationComponent = informationComponent;
	
		service =  new BaseFASTService(informationComponent);
		service.setImplementation(InformantServiceImplementation.class);
		
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
