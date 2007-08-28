package es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.process;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.UnknownCounterPartyException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.BaseAgreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.agreement.ProposalPerformative;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.process.OLDAbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcher;
import es.us.lsi.tdg.fast.core.services.ServiceInvoker;
import es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.FOMAgreementMaking;
import es.us.lsi.tdg.fast.domains.fom.components.fomdiscovery.services.DiscoveryEP;
import es.us.lsi.tdg.fast.domains.fom.components.fomdiscovery.services.FOMDiscoveryService;
import es.us.lsi.tdg.fast.domains.fom.components.fominformation.services.FOMInformant;
import es.us.lsi.tdg.fast.domains.fom.components.fominformation.services.InformantService;
import es.us.lsi.tdg.fast.domains.fom.components.fomselection.services.CollectorService;
import es.us.lsi.tdg.fast.domains.fom.components.fomselection.services.FOMCollector;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOfferInformation;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalTranslator;


public class FOMAgreementMakerProcess extends AbstractControllableProcess {
	private ProposalDispatcher	proposalDispatcher;
	private boolean proposed;
	private boolean commited;
	
	
	private FOMAgreementMaking	agreementMakingComponent;
	
	public FOMAgreementMakerProcess(FOMAgreementMaking agreementMakingComponent) {
		super("FOMAgreementMaker");
		this.proposalDispatcher = (ProposalDispatcher) agreementMakingComponent.getAgreementMaker();
		this.agreementMakingComponent = agreementMakingComponent;		
	}
	

	@Override
	protected  void  run()
	{

		
		List<Proposal> originalProposalSet = proposalDispatcher.getProposalsDispatched();
		
		if(originalProposalSet.size()>0){
			
			for(Proposal proposal:originalProposalSet)
			{
				if(proposal.getPerformative()==ProposalPerformative.PROPOSAL && !proposed)
					commitProposal(proposal);
				else if(proposal.getPerformative()==ProposalPerformative.COMMIT && !commited)
					acceptProposal(proposal);
					
			}	
		}
	}

	/***
	 * Extended version with REJECT
	 
	@Override
	protected  void  run()
	{
		
		List<Proposal> proposalSet = proposalDispatcher.getProposalsDispatched(agreementMakingComponent.getAgreementMaker());
		if(proposalSet.size()>1){
			for(Proposal proposal:proposalSet)
			{
				if(proposal.getPerformative()==ProposalPerformative.PROPOSAL && !proposed)
				{
					processProposal(proposal);
					proposalSet.remove(proposal);
					//proposalSet.clear();
					proposed=true;
				}else if(proposal.getPerformative()==ProposalPerformative.COMMIT){
					processProposal(proposal);
					//proposalSet.clear();
				}else if(proposal.getPerformative()==ProposalPerformative.REJECT){
					proposed=false;
				}else if(proposal.getPerformative()==ProposalPerformative.ACCEPT){
					processProposal(proposal);
				}
			}		
		}
	}
	*/
	
	/*
	private void processCommitProposal(Proposal proposal)
	{
		if (commited==false) {
			Agreement agreement=new BaseAgreement(proposal.getTerms(),proposal.getCounterParties());
			String PID=getPID();
			FAST.agreementRegistry.addAgreement(PID, agreement);
			proposal.setPerformative(ProposalPerformative.ACCEPT);
			commited = true;
		}else{
			proposal.setPerformative(ProposalPerformative.REJECT);
		}
		
	}*/
	
	private String getPID()
	{
		String PID=agreementMakingComponent.getTradingProcess().getPID();
		return PID;
	}
	
	/*
	private void processAcceptProposal(Proposal proposal)
	{
		Agreement agreement=new BaseAgreement(proposal.getTerms(),proposal.getCounterParties());
		String PID=getPID();
		FAST.agreementRegistry.addAgreement(PID, agreement);
		stop();
	}*/
	
	private void commitProposal(Proposal proposal)
	{
		FOMProposal fomOffer = FOMProposalTranslator.getFOMProposal(proposal);
		int time= fomOffer.getTime();
		double cost= fomOffer.getCost();
	
		Set<CounterParty> counterParties = proposal.getCounterParties();
		int myServerPort = Integer.parseInt(FAST.properties.get("serverPort"));
		String myDomainRoleName = FAST.currentDomainRole.getName();
		
		String myCollectorEndPoint = "http://"+FAST.properties.get("serverIP")+":"+myServerPort+"/"+myDomainRoleName+"/CollectorServiceImplementation";
		String counterPartyCollectorEndPoint=null;
		
		if (counterParties==null){
			throw new UnknownCounterParty();
		}
		
		for (CounterParty counterParty:counterParties){
			counterPartyCollectorEndPoint = ((FOMCounterParty)counterParty).getSelectionEndPoint().toString();
		}
		
		if ( counterPartyCollectorEndPoint == null){
			throw new UnknownEndPoint("Collector EndPoint is missing");
		}
			
		try {
			
			
			URL url = new URL(counterPartyCollectorEndPoint+"?wsdl");
			QName qname = new QName("http://services.FOMSelection.components.fom.domains.fast.tdg.lsi.us.es/", "CollectorService");
				
			CollectorService service;
			FOMCollector port;
			
			service = (CollectorService) ServiceInvoker.getService(url,qname,CollectorService.class);
			
			
			port = service.getFOMCollectorPort();
					
			FAST.shell.showMessage("COMMIT over AgreementOffer: " + fomOffer);
			FAST.shell.showMessage("My Collector EndPoint: " + myCollectorEndPoint);
			FAST.shell.showMessage("CounterParty Collector EndPoint: " + counterPartyCollectorEndPoint);
			
			port.commit(Integer.toString(time), Double.toString(cost), myCollectorEndPoint);
			proposed = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private void acceptProposal(Proposal proposal)
	{

		FOMProposal fomProposal = FOMProposalTranslator.getFOMProposal(proposal);
		
		int time= fomProposal.getTime();
		double cost= fomProposal.getCost();
	
		Set<CounterParty> counterParties = proposal.getCounterParties();
		String counterPartyCollectorEndPoint=null;
		
		if (counterParties==null){
			throw new UnknownCounterParty();
		}
		
		for (CounterParty counterParty:counterParties){
			counterPartyCollectorEndPoint = ((FOMCounterParty)counterParty).getSelectionEndPoint().toString();
		}
		
		if ( counterPartyCollectorEndPoint == null){
			throw new UnknownEndPoint("Collector EndPoint is missing");
		}
			
		try {
			
			URL url = new URL(counterPartyCollectorEndPoint+"?wsdl");
			QName qname = new QName("http://services.FOMSelection.components.fom.domains.fast.tdg.lsi.us.es/", "CollectorService");
				
			CollectorService service;
			FOMCollector port;
			
			service = (CollectorService) ServiceInvoker.getService(url,qname,CollectorService.class);
			
			port = service.getFOMCollectorPort();
					
			FAST.shell.showMessage("ACCEPT over AgreementOffer: " + fomProposal);
			
			FAST.shell.showMessage("CounterParty Collector EndPoint: " + counterPartyCollectorEndPoint);
			
			port.accept(Integer.toString(time), Double.toString(cost));
			FAST.shell.showMessage("SLA Reached: " + fomProposal);
			commited= true;
			FAST.agreementRegistry.addAgreement(getPID(), FOMProposalTranslator.getAgreement(new FOMProposal(time,cost, counterPartyCollectorEndPoint)));
			agreementMakingComponent.getTradingProcess().getOrchestrator().OLD_event("SLA_REACHED");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		
	}
	
}
