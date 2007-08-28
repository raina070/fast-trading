package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.process.OLDAbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.information.Informant;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.services.BaseFASTService;
import es.us.lsi.tdg.fast.core.services.FASTService;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.services.DiscoveryServiceImplementation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.services.InformantServiceImplementation;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalTranslator;

public class FOMInformantProcess extends AbstractControllableProcess {

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
