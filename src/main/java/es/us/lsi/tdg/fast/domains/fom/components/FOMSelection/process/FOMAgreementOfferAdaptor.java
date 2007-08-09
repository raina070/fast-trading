package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.HashSet;

import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;

public class FOMAgreementOfferAdaptor {
	public static Set<Agreement>getAgreementSet(Set<Information> infoSet){
		FOMProposalBuilder 	Proposer		= new FOMProposalBuilder();
		Set<FOMOffer> 		FOMOfferSet 	= new HashSet<FOMOffer>();
		Set<FOMAgreement> 	FOMAgreementSet = new HashSet<FOMAgreement>();
		Set<Agreement>		result			= new HashSet<Agreement>();
		for (Information info: infoSet){
			FOMOfferSet.add(FOMOfferInformationTranslator.getFOMOffer(info));
		}
		FOMAgreementSet = Proposer.FOMOfferToAgreement(FOMOfferSet,new FOMOffer(10,40,60));
		for (FOMAgreement FOMAgreement: FOMAgreementSet){
			try {
				result.add(FOMSLATranslator.getAgreement(FOMAgreement));
			} catch (IncompatibleAttributeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(AgreeTest);
		}
		return result;
	}
}
