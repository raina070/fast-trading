package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.HashSet;

import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOffer;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOfferInformationTranslator;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMSLATranslator;

public class FOMProposalOfferAdaptor {
	public static Set<Proposal>getAgreementSet(Set<Information> infoSet){
		FOMProposalBuilder 	Proposer		= new FOMProposalBuilder();
		Set<FOMOffer> 		FOMOfferSet 	= new HashSet<FOMOffer>();
		Set<FOMProposal> 	FOMAgreementSet = new HashSet<FOMProposal>();
		Set<Proposal>		result			= new HashSet<Proposal>();
		for (Information info: infoSet){
			FOMOfferSet.add(FOMOfferInformationTranslator.getFOMOffer(info));
		}
		FOMAgreementSet = Proposer.FOMOfferToAgreement(FOMOfferSet,new FOMOffer(10,40,50));
		for (FOMProposal FOMAgreement: FOMAgreementSet){
			result.add(FOMSLATranslator.getAgreement(FOMAgreement));
		}
		return result;
	}
}
