package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;


import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMAgreementPreferences;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMAgreementPreferencesTranslator;
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
	
	@Override
	protected  void  run()
	{
		Set <CounterPartyKnowledge>	counterPartyKnowledgeSet = inquirer.getInformation();
		if (counterPartyKnowledgeSet.size() > 0){
			Set	<Proposal> ProposalSet = new HashSet<Proposal>();
			for (CounterPartyKnowledge counterPartyKnowledge:counterPartyKnowledgeSet){
			
				Information info = counterPartyKnowledge.getServiceInformation();
				CounterParty cp = counterPartyKnowledge.getCounterParty();
				
				FAST.shell.showMessage("Launching ProposalBuilder...");
				
	
				FOMAgreementPreferences FOMAgreementPreferences = FOMAgreementPreferencesTranslator.getFOMAgreementPreferences(FAST.preferenceRegistry.getPreferences(selectionComponent.getTradingProcess().getPID()));
				
				FAST.shell.showMessage("AgreementPreferences...");
				FAST.shell.showMessage(FOMAgreementPreferences.toString());
				
				ProposalSet.addAll(FOMProposalAdaptor.getAgreementSet(cp,info,FOMAgreementPreferences));
				FAST.shell.showMessage("Sorting Proposals...");
								 
			}
			SortedSet<Proposal> ProposalSortedSet = FOMProposalSelection.FOMSortAgreement(ProposalSet,FAST.preferenceRegistry.getPreferences(selectionComponent.getTradingProcess().getPID()));
			this.selectionComponent.setSortedProposalSet(ProposalSortedSet);
				
			
		}
	
	}

}
