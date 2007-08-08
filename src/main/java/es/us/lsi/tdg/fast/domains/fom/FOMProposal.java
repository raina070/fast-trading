package es.us.lsi.tdg.fast.domains.fom;

import java.util.Set;
import java.util.HashSet;

/**
 * 
 * @author Antonio Manuel
 *	This class matches FOMOffer with FOMAgreementPreferences to create FOMAgreements 
 */

public class FOMProposal {

	public Set<FOMAgreement> FOMOfferToAgreement(Set<FOMOffer> FOMOffers, FOMOffer FOMPreferences){

		Set<FOMAgreement> result = new HashSet<FOMAgreement>();
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
					FOMAgreement Agree = new FOMAgreement(timeInitOffer,costOffer);
					result.add(Agree);
					if (timeEndOffer<=timeEndPref && timeEndOffer!=timeInitPref){
						FOMAgreement Agree2 = new FOMAgreement(timeEndOffer,costOffer);
						result.add(Agree2);
					}else if(timeInitOffer!=timeEndPref){
						FOMAgreement Agree2 = new FOMAgreement(timeEndPref,costOffer);
						result.add(Agree2);
					}
				}else if (timeEndOffer<=timeEndPref){
					FOMAgreement Agree = new FOMAgreement(timeEndOffer,costOffer);
					result.add(Agree);
				}
				
			}
		}
		
		return result;
		
		
	}
	
}
