package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.HashSet;

import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOfferInformation;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMInformationTranslator;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalTranslator;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMAgreementPreferences;
import es.us.lsi.tdg.fast.FAST;

public class FOMProposalAdaptor {
	public static Set<Proposal>getAgreementSet(CounterParty cp, Information info, FOMAgreementPreferences FOMAgreementPreferences){
		
		FOMProposalBuilder 	Proposer			= new FOMProposalBuilder();
		Set<FOMOfferInformation> FOMInformationSet 	= new HashSet<FOMOfferInformation>();
		Set<FOMProposal> 	FOMProposalSet 		= new HashSet<FOMProposal>();
		Set<Proposal>		result				= new HashSet<Proposal>();

		FOMInformationSet.add(FOMInformationTranslator.getFOMInformation(info));
		
		FOMProposalSet = Proposer.FOMInformationToProposal(FOMInformationSet,FOMAgreementPreferences,cp);
		for (FOMProposal FOMAgreement: FOMProposalSet){
			FAST.shell.showMessage("New Proposal Built: " + FOMAgreement);
			result.add(FOMProposalTranslator.getAgreement(FOMAgreement));
		}
		FAST.shell.showMessage("Proposals Ordered");
		return result;
	}

}
