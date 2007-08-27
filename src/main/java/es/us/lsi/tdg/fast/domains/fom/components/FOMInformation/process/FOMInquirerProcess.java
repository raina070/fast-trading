package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import java.net.URI;
import es.us.lsi.tdg.fast.FAST;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.information.BaseCounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.process.OLDAbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
//import es.us.lsi.tdg.fast.core.roles.information.Informant;

import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;

/**
 * 
 * @author Antonio Manuel Gutierrez Fernandez
 *
 */
public class FOMInquirerProcess extends OLDAbstractControllableProcess {
	
	private Tracker 		tracker;
	private ProposalBuilder proposalBuilder;
	
	Set<CounterParty> 		FOMProviders;
	private FOMInformation 	informationComponent;
	
	public FOMInquirerProcess(Tracker tracker, ProposalBuilder proposalBuilder) {
		this("FOMInquirer",tracker, proposalBuilder);				
	}

	public FOMInquirerProcess(String threadName,Tracker tracker, ProposalBuilder proposalBuilder)
	{
		super(threadName);
		this.tracker 			= tracker;
		this.proposalBuilder 	= proposalBuilder;

	}
		
	public FOMInquirerProcess(FOMInformation informationComponent) {
		this((Tracker) informationComponent.getInquirer(Tracker.class), (ProposalBuilder)informationComponent.getInquirer(ProposalBuilder.class));
		this.informationComponent = informationComponent;
	}
	
	//public void start(){
	//	FOMProviders = tracker.getPotentialCounterParties();
	//}
	
	
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

		//stop();	
	}
}