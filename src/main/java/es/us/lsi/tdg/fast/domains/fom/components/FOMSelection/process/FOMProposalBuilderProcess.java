package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMAgreementPreferences;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMAgreementPreferencesTranslator;

public class FOMProposalBuilderProcess extends AbstractControllableProcess{

	private Inquirer 					inquirer;	
	private FOMSelection 				selectionComponent;
	@SuppressWarnings("unused")
	private Set<CounterPartyKnowledge> 	counterpartyknowledge;
			
	public FOMProposalBuilderProcess(FOMSelection selectionComponent) {
		super("FOMProposalBuilder");
		this.inquirer = (Inquirer) selectionComponent.getProposalBuilder();
		this.selectionComponent = selectionComponent;
	}

	@Override
	protected  void  run()
	{
		Set <CounterPartyKnowledge>	counterPartyKnowledgeSet = inquirer.getInformation();
		if (counterPartyKnowledgeSet.size() > 0){
			FAST.shell.showMessage("Launching ProposalBuilder...");
			FOMAgreementPreferences FOMAgreementPreferences = FOMAgreementPreferencesTranslator.getFOMAgreementPreferences(FAST.preferenceRegistry.getPreferences(selectionComponent.getTradingProcess().getPID()));
			FAST.shell.showMessage("AgreementPreferences...");
			FAST.shell.showMessage(FOMAgreementPreferences.toString());


			Set	<Proposal> ProposalSet = new HashSet<Proposal>();
			for (CounterPartyKnowledge counterPartyKnowledge:counterPartyKnowledgeSet){
			
				Information info = counterPartyKnowledge.getServiceInformation();
				CounterParty cp = counterPartyKnowledge.getCounterParty();
				
				ProposalSet.addAll(FOMProposalAdaptor.getAgreementSet(cp,info,FOMAgreementPreferences));
								 
			}
			FAST.shell.showMessage("Sorting Proposals...");
			SortedSet<Proposal> ProposalSortedSet = FOMProposalSelection.FOMSortAgreement(ProposalSet,FAST.preferenceRegistry.getPreferences(selectionComponent.getTradingProcess().getPID()));
			SortedSet<Proposal> currentProposalSortedSet=this.selectionComponent.getSortedProposalSet();
			synchronized(currentProposalSortedSet){
				currentProposalSortedSet.addAll(ProposalSortedSet);				
			}
			counterPartyKnowledgeSet.clear();	
			
		}
	
	}

}
