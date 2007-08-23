package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.BaseCounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.roles.information.Informant;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOfferInformation;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMInformationTranslator;



public class FOMInformant implements Informant{
	private double 	c1=50,c2=20,c3=10;	
	
	public Set<FOMOfferInformation> getOffers()
	{
		Set<FOMOfferInformation> result=new HashSet<FOMOfferInformation>();
		int tInit 	= getTimeInit();
		int tFull = 60;
		int tReal = tFull - tInit;
		double factor = (double)tFull/tReal;
		
		int t1 = tReal/3;
		int t2 = (2*tReal/3);
				
		FOMOfferInformation Offer1 = new FOMOfferInformation(tInit,t1+tInit, factor*this.c1);
		FOMOfferInformation Offer2 = new FOMOfferInformation(t1+tInit+1,t2+tInit, factor*this.c2);
		FOMOfferInformation Offer3 = new FOMOfferInformation(t2+tInit+1,tFull, factor*this.c3);
	
		result.add(Offer1);
		result.add(Offer2);
		result.add(Offer3);

		return result;
	}




	private static int getTimeInit() {
		// TODO Obtain this value form the current SLAs (AgreementRegistry)
		return 0;
	}

	public Set<CounterPartyKnowledge> getKnowledge(CounterParty counterParty) {
		Set<CounterPartyKnowledge> result=new HashSet<CounterPartyKnowledge>();
		Set<FOMOfferInformation> offers=getOffers();
		Information info;
		for(FOMOfferInformation offer:offers)
		{
			try {
				info=FOMInformationTranslator.getInformation(offer);
				result.add(new BaseCounterPartyKnowledge(info,counterParty));
			} catch (IncompatibleAttributeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		return result;
	}
}
