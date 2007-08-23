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
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcher;
import es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.FOMAgreementMaking;
import es.us.lsi.tdg.fast.domains.fom.components.fomdiscovery.services.DiscoveryEP;
import es.us.lsi.tdg.fast.domains.fom.components.fomdiscovery.services.FOMDiscoveryService;
import es.us.lsi.tdg.fast.domains.fom.components.fomselection.services.CollectorService;
import es.us.lsi.tdg.fast.domains.fom.components.fomselection.services.FOMCollector;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOffer;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMSLATranslator;


public class FOMAgreementMakerProcess extends AbstractControllableProcess {
	private ProposalDispatcher	proposalDispatcher;
	private boolean proposed;
	private boolean commited;
	
	
	private FOMAgreementMaking	agreementMakingComponent;
	
	public FOMAgreementMakerProcess(ProposalDispatcher proposalDispatcher) {
		this("FOMAgreementMaker",proposalDispatcher);
		proposed=false;
		commited=false;
	}

	public FOMAgreementMakerProcess(String threadName, ProposalDispatcher proposalDispatcher)
	{
		super(threadName);
		this.proposalDispatcher 	= proposalDispatcher;
		commited=false;
		proposed=false;
	}
		
	public FOMAgreementMakerProcess(FOMAgreementMaking agreementMakingComponent) {
		this((ProposalDispatcher) agreementMakingComponent.getAgreementMaker());
		this.agreementMakingComponent = agreementMakingComponent;		
	}
	

	@Override
	protected  void  run()
	{
		//Copy due to comodification errors
		
		List<Proposal> originalProposalSet = proposalDispatcher.getProposalsDispatched(agreementMakingComponent.getAgreementMaker());
		if(originalProposalSet.size()>1){
			List<Proposal> proposalSet = new LinkedList<Proposal>(originalProposalSet);
			for(Proposal proposal:proposalSet)
			{
				processProposal(proposal);
			}	
			//originalProposalSet.clear();
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
		
	}
	
	private String getPID()
	{
		String PID=agreementMakingComponent.getTradingProcess().getPID();
		return PID;
	}
	
	private void processAcceptProposal(Proposal proposal)
	{
		Agreement agreement=new BaseAgreement(proposal.getTerms(),proposal.getCounterParties());
		String PID=getPID();
		FAST.agreementRegistry.addAgreement(PID, agreement);
		stop();
	}
	
	private void processProposal(Proposal proposal)
	{

		Set<CounterParty> counterParties = proposal.getCounterParties();
		FOMCounterParty counterParty;
		
		int myServerPort = Integer.parseInt(FAST.properties.get("serverPort"));
		String myDomainRoleName = FAST.currentDomainRole.getName();
		
		String myEP = "http://localhost:"+myServerPort+"/"+myDomainRoleName+"/CollectorServiceImplementation";

		String selEP;
		
		//CounterParty[] aCP = (CounterParty[]) counterParties.toArray();
		
		selEP = myEP;
		/*
		if(aCP.){
			counterParty = (FOMCounterParty) i.next();
			selEP = counterParty.getSelectionEndPoint().toString();
		}else{
			//If multiples instances of FASTwe should deal with this:
			//throw new UnknownCounterPartyException();
			selEP = myEP;
		}
		*/
		
		
		
		FOMProposal fomOffer = FOMSLATranslator.getFOMAgreement(proposal);
		

		int time= fomOffer.getTime();
		double cost= fomOffer.getCost();
		
		try {
			URL url = new URL(selEP+"?wsdl");
			QName qname = new QName("http://services.FOMSelection.components.fom.domains.fast.tdg.lsi.us.es/", "CollectorService");
				
			CollectorService service;
			FOMCollector port;
			
			service = new CollectorService(url,qname);
			
			port = service.getFOMCollectorPort();
			
						
			if(proposal.getPerformative()==ProposalPerformative.COMMIT){
				FAST.shell.showMessage("ACCEPT over AgreementOffer: " + fomOffer);
				port.accept(Integer.toString(time), Double.toString(cost));
			}else if(proposal.getPerformative()==ProposalPerformative.PROPOSAL){
				FAST.shell.showMessage("COMMIT over AgreementOffer: " + fomOffer);
				port.commit(Integer.toString(time), Double.toString(cost), myEP);
			}

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		
		
	}
	
	private boolean compatible(Proposal commited,Proposal toCommit)
	{
		return false;
	}
	
}
