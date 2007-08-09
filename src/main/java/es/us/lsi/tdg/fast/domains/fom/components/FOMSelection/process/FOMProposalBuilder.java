package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.HashSet;

/**
 * 
 * @author Antonio Manuel
 *	This class matches FOMOffer with FOMAgreementPreferences to create FOMAgreements 
 */

public class FOMProposalBuilder {

	public Set<FOMProposal> FOMOfferToAgreement(Set<FOMOffer> FOMOffers, FOMOffer FOMPreferences){

		Set<FOMProposal> result = new HashSet<FOMProposal>();
		double costOffer=0;
		int  timeInitOffer=0, timeEndOffer=0;
		double costPref =0;
		int timeInitPref =0, timeEndPref =0;
		//Two, one or zero FOMAgreementProposals per FOMOffer
		for (FOMOffer currentOffer: FOMOffers){
			
			costOffer 		= currentOffer.getCost();
			timeInitOffer 	= currentOffer.getTimeInit();
			timeEndOffer  	= currentOffer.getTimeEnd();
			
			costPref		= FOMPreferences.getCost();
			timeInitPref 	= FOMPreferences.getTimeInit();
			timeEndPref		= FOMPreferences.getTimeEnd();
			
			if (costOffer<=costPref && timeInitOffer<=timeEndPref && timeEndOffer>=timeInitPref){
				if (timeInitOffer>=timeInitPref){
					FOMProposal Agree = new FOMProposal(timeInitOffer,costOffer);
					result.add(Agree);
					if (timeEndOffer<=timeEndPref && timeEndOffer!=timeInitPref){
						FOMProposal Agree2 = new FOMProposal(timeEndOffer,costOffer);
						result.add(Agree2);
					}else if(timeInitOffer!=timeEndPref){
						FOMProposal Agree2 = new FOMProposal(timeEndPref,costOffer);
						result.add(Agree2);
					}
				}else if (timeEndOffer<=timeEndPref){
					FOMProposal Agree = new FOMProposal(timeEndOffer,costOffer);
					result.add(Agree);
				}		
			}
		}
		
		return result;
		
		
	}
	
}
