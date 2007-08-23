package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.HashSet;

import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOfferInformation;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMAgreementPreferences;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposal;

/**
 * 
 * @author Antonio Manuel
 *	This class matches FOMOffer with FOMAgreementPreferences to create FOMAgreements 
 */

public class FOMProposalBuilder {

	public Set<FOMProposal> FOMInformationToProposal(Set<FOMOfferInformation> FOMOffers, FOMAgreementPreferences FOMAgreementPreferences, CounterParty cp){

		Set<FOMProposal> result = new HashSet<FOMProposal>();
		double costOffer=0;
		int  timeInitOffer=0, timeEndOffer=0;
		double costPref =0;
		int timeInitPref =0, timeEndPref =0;
		//Two, one or zero FOMAgreementProposals per FOMOffer
		for (FOMOfferInformation currentOffer: FOMOffers){
			
			costOffer 		= currentOffer.getCost();
			timeInitOffer 	= currentOffer.getTimeInit();
			timeEndOffer  	= currentOffer.getTimeEnd();
			
			costPref		= FOMAgreementPreferences.getCost();
			timeInitPref 	= FOMAgreementPreferences.getTimeInit();
			timeEndPref		= FOMAgreementPreferences.getTimeEnd();
			
			if (costOffer<costPref && timeInitOffer<=timeEndPref && timeEndOffer>=timeInitPref){
				if (timeInitOffer>=timeInitPref){
					FOMProposal Agree = new FOMProposal(timeInitOffer,costOffer,((FOMCounterParty)cp).getSelectionEndPoint().toString());
					result.add(Agree);
					if (timeEndOffer<=timeEndPref && timeEndOffer!=timeInitPref){
						FOMProposal Agree2 = new FOMProposal(timeEndOffer,costOffer,((FOMCounterParty)cp).getSelectionEndPoint().toString());
						result.add(Agree2);
					}else if(timeInitOffer!=timeEndPref){
						FOMProposal Agree2 = new FOMProposal(timeEndPref,costOffer,((FOMCounterParty)cp).getSelectionEndPoint().toString());
						result.add(Agree2);
					}
				}else if (timeEndOffer<=timeEndPref){
					FOMProposal Agree = new FOMProposal(timeEndOffer,costOffer,((FOMCounterParty)cp).getSelectionEndPoint().toString());
					result.add(Agree);
					if (timeInitPref!=timeEndOffer){
						FOMProposal Agree2 = new FOMProposal(timeInitPref,costOffer,((FOMCounterParty)cp).getSelectionEndPoint().toString());
						result.add(Agree2);
					}
					
				}else	{
					FOMProposal Agree = new FOMProposal(timeEndPref,costOffer,((FOMCounterParty)cp).getSelectionEndPoint().toString());
					result.add(Agree);
					FOMProposal Agree2 = new FOMProposal(timeInitPref,costOffer,((FOMCounterParty)cp).getSelectionEndPoint().toString());
					result.add(Agree2);
		
				}
			}
		}
		
		return result;
		
		
	}
	
}
