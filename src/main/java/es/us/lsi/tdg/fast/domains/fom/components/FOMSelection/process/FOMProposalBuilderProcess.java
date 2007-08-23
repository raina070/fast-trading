package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.BaseAgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.statement.*;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process.FOMOfferInformationAdaptor;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOffer;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMSLATranslator;
import es.us.lsi.tdg.fast.domains.fom.FOMAssessmentMechanism;
import es.us.lsi.tdg.fast.FAST;

public class FOMProposalBuilderProcess extends AbstractControllableProcess{

	private Inquirer 					inquirer;	
	private FOMSelection 				selectionComponent;
	private Set<CounterPartyKnowledge> 	counterpartyknowledge;
	
	public FOMProposalBuilderProcess(Inquirer inquirer) {
		this("FOMProposalBuilder",inquirer);				
	}

	public FOMProposalBuilderProcess(String threadName,Inquirer inquirer)
	{
		super(threadName);
		this.inquirer 	= inquirer;

	}
		
	public FOMProposalBuilderProcess(FOMSelection selectionComponent) {
		this((Inquirer)selectionComponent.getProposalBuilder());
		this.selectionComponent = selectionComponent;
	}

	public AgreementPreferences FOMAgreementPreferences(String PID){
		
		//FOMAssessmentMechanism myAssessmentMechanism = new FOMAssessmentMechanism();
		AgreementPreferences result = FAST.preferenceRegistry.getPreferences(PID);
		
		
		return result;
	}
	
	public void fixAgreementPreferences(String PID){
		AgreementPreferences result = FAST.preferenceRegistry.getPreferences(PID);
		FOMOffer resultaux = FOMSLATranslator.getFOMOfferPreference(result);		
		try {
			BaseAttribute Cost = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
			IntegerValue costValue 		= new IntegerValue((int)resultaux.getCost());
			IntegerValue costValueMin 	= new IntegerValue(0);
			SortedDomainConstraint costConstraint = new BaseSortedDomainConstraint((ComparableValue)costValueMin,(ComparableValue)costValue,Cost,StatementType.SERVICE);
			IntegerValue timeInitValue = new IntegerValue(resultaux.getTimeInit());
			IntegerValue timeEndValue = new IntegerValue(resultaux.getTimeEnd());
			BaseAttribute time = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
			SortedDomainConstraint timeConstraint = new BaseSortedDomainConstraint((ComparableValue)timeInitValue,(ComparableValue)timeEndValue, time,StatementType.SERVICE);
			Set<Statement> requirements = result.getRequirements();
			requirements.clear();
			requirements.add((Statement)costConstraint);
			requirements.add((Statement)timeConstraint);	
		} catch (IncompatibleAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected  void  run()
	{
		Set <CounterPartyKnowledge>	counterPartyKnowledgeSet = inquirer.getInformation();
		if (counterPartyKnowledgeSet.size() > 0){
			Set <Information> 			infoSet = new HashSet<Information>();

			
			for (CounterPartyKnowledge counterPartyKnowledge:counterPartyKnowledgeSet){
			
					
				Information info = counterPartyKnowledge.getServiceInformation();
				CounterParty cp = counterPartyKnowledge.getCounterParty();
				
				FAST.shell.showMessage("Launching ProposalBuilder...");
				Set	<Proposal> ProposalSet = new HashSet<Proposal>();
	
				FOMOffer FOMOfferPreference = FOMSLATranslator.getFOMOfferPreference(FAST.preferenceRegistry.getPreferences(selectionComponent.getTradingProcess().getPID()));
				
				FAST.shell.showMessage("AgreementPreferences...");
				FAST.shell.showMessage(FOMOfferPreference.toString());
				
				ProposalSet = FOMProposalOfferAdaptor.getAgreementSet(cp,info,FOMOfferPreference);
				FAST.shell.showMessage("Sorting Proposals...");
				SortedSet<Proposal> ProposalSortedSet = FOMProposalSelection.FOMSortAgreement(ProposalSet,FAST.preferenceRegistry.getPreferences("1"));
				
				this.selectionComponent.setSortedProposalSet(ProposalSortedSet);
				 
			}
				
			
		}
	
	}

}
