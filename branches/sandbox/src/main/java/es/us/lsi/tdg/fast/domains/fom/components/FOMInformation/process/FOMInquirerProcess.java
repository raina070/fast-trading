package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.BaseCounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;

/**
 * 
 * @author Antonio Manuel Gutierrez Fernandez
 *
 */
public class FOMInquirerProcess extends AbstractControllableProcess {
	
	private Tracker 		tracker;
	private ProposalBuilder proposalBuilder;
	
	@SuppressWarnings("unused")
	private FOMInformation 	informationComponent;
	
	Set<CounterParty> 		FOMProviders;
	
		
	public FOMInquirerProcess(FOMInformation informationComponent) {
		super("FOMInquirer");
		this.tracker = (Tracker) informationComponent.getInquirer(Tracker.class);
		this.proposalBuilder = (ProposalBuilder) informationComponent.getInquirer(ProposalBuilder.class);
		this.informationComponent = informationComponent;
	}
	
	
	@Override
	protected  void  run()
	{
		if (FOMProviders == null){
			FOMProviders = tracker.getPotentialCounterParties();
		}else{
			FOMProviders = tracker.getNewCounterParties();
			
		}		
		for (CounterParty cp:FOMProviders){
			if (cp instanceof FOMCounterParty){
				FAST.shell.showMessage("Getting Offers from provider "+((FOMCounterParty)cp).getCPID());
				Set<Information> information = FOMInformationAdaptor.getInformation(((FOMCounterParty)cp).getInformationEndPoint().toString());				
					for (Information info:information){
						proposalBuilder.newInformation(new BaseCounterPartyKnowledge(info,cp));
					}				
			}
		}
	}
}
