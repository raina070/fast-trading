package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.SortedSet;

import javax.jws.WebMethod;
import javax.jws.WebService;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.agreement.ProposalPerformative;
import es.us.lsi.tdg.fast.core.services.FASTServiceImplementation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMSLATranslator;

@WebService(name="FOMCollector", serviceName="CollectorService")
public class CollectorServiceImplementation implements
		FASTServiceImplementation {
	
	FOMSelection selectionComponent;
	
	public void setComponent(Component component) {
		selectionComponent = (FOMSelection) component;
	}
	
	@WebMethod(operationName="commit")
	public void commit(String time, String cost, String acceptEP) {
		  SortedSet<Proposal> proposalSet= selectionComponent.getSortedProposalSet();
		  
		  FOMProposal fomProposal = new FOMProposal(Integer.parseInt(time),Double.parseDouble(cost));
		  Proposal proposal = FOMSLATranslator.getAgreement(fomProposal);
		  proposal.setPerformative(ProposalPerformative.COMMIT);
		  try {
				URI acceptEPURI = new URI(acceptEP);
				FOMCounterParty provider = new FOMCounterParty("TestProvider",null,null,acceptEPURI,null);
				Set<CounterParty> counterParties = proposal.getCounterParties();
				counterParties.add(provider);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		  
		  proposalSet.add(proposal);
	}
	
	@WebMethod(operationName="accept")
	public void accept(String time, String cost) {
		  SortedSet<Proposal> proposalSet= selectionComponent.getSortedProposalSet();
		  
		  FOMProposal fomProposal = new FOMProposal(Integer.parseInt(time),Double.parseDouble(cost));
		  Proposal proposal = FOMSLATranslator.getAgreement(fomProposal);
		  proposal.setPerformative(ProposalPerformative.ACCEPT);

		  FAST.agreementRegistry.addAgreement(selectionComponent.getTradingProcess().getPID(),proposal);
		  
		  selectionComponent.getTradingProcess().getOrchestrator().event("SLA_REACHED");
		  
	}
	
	
	
}
