/**
 * 
 */
package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.services.BaseFASTService;
import es.us.lsi.tdg.fast.core.services.FASTService;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.services.CollectorServiceImplementation;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 * @author Antonio Manuel Gutierrez Fernandez
 *
 */
public class FOMProposalCollectorProcess extends AbstractControllableProcess {

	@SuppressWarnings("unused")
	private FOMSelection selectionComponent;
	
	private FASTService service;
	
	public FOMProposalCollectorProcess(FOMSelection selectionComponent) {
		
		super("FOMProposalCollector");
		
		this.selectionComponent = selectionComponent;
		
		service = new BaseFASTService(selectionComponent);
		service.setImplementation(CollectorServiceImplementation.class);
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
