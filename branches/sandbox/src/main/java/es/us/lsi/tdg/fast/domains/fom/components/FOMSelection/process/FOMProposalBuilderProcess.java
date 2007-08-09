package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
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
import es.us.lsi.tdg.fast.domains.fom.FOMAssessmentMechanism;

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
		this((Inquirer)selectionComponent.getProposalBuilderInquirerAdaptor());
		this.selectionComponent = selectionComponent;
	}

	public AgreementPreferences FOMAgreementPreferences() throws IncompatibleAttributeException{
		FOMAssessmentMechanism myAssessmentMechanism = new FOMAssessmentMechanism();
		AgreementPreferences result = new BaseAgreementPreferences(myAssessmentMechanism);
		IntegerValue costValue 		= new IntegerValue(50);
		IntegerValue costValueMin 	= new IntegerValue(-100);
		BaseAttribute Cost = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
		SortedDomainConstraint costConstraint = new BaseSortedDomainConstraint((ComparableValue)costValueMin,(ComparableValue)costValue,Cost,StatementType.SERVICE);
		IntegerValue timeInitValue = new IntegerValue(10);
		IntegerValue timeEndValue = new IntegerValue(40);
		BaseAttribute time = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
		SortedDomainConstraint timeConstraint = new BaseSortedDomainConstraint((ComparableValue)timeInitValue,(ComparableValue)timeEndValue, time,StatementType.SERVICE);
		Set<Statement> requirements = result.getRequirements();
		requirements.add((Statement)costConstraint);
		requirements.add((Statement)timeConstraint);
		return result;
	}
	
	@Override
	protected  void  run()
	{
		Set <CounterPartyKnowledge>	counterPartyKnowledgeSet = inquirer.getInformation();
		Set <Information> 			infoSet = new HashSet<Information>();
		for (CounterPartyKnowledge counterPartyKnowledge:counterPartyKnowledgeSet){
			Information info = counterPartyKnowledge.getServiceInformation();
			infoSet.add(info);
		}
		Set			<Agreement> 		AgreementSet = new HashSet<Agreement>();
		
		AgreementSet = FOMAgreementOfferAdaptor.getAgreementSet(infoSet);
		
		try {
			SortedSet<Agreement> AgreementSortedSet = FOMAgreementSelection.FOMSortAgreement(AgreementSet,FOMAgreementPreferences());
		} catch (IncompatibleAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stop();	
	}

}
