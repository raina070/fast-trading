package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.BaseCounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.roles.information.Informant;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOffer;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOfferInformationTranslator;



public class FOMOfferInformant implements Informant{
	private double 	c1,c2,c3;	
	
	public FOMOfferInformant()
	{
		c1=50;
		c2=20;
		c3=10;
	}
	
	public Set<FOMOffer> getOffers()
	{
		Set<FOMOffer> result=new HashSet<FOMOffer>();
		int tInit 	= getTimeInit();
		int tFull = 60;
		int tReal = tFull - tInit;
		double factor = (double)tFull/tReal;
		
		int t1 = tReal/3;
		int t2 = (2*tReal/3);
				
		
		FOMOffer Offer1 = new FOMOffer(tInit,t1+tInit, factor*this.c1);
		FOMOffer Offer2 = new FOMOffer(t1+tInit+1,t2+tInit, factor*this.c2);
		FOMOffer Offer3 = new FOMOffer(t2+tInit+1,tFull, factor*this.c3);
		
		result.add(Offer1);
		result.add(Offer2);
		result.add(Offer3);
		return result;
	}




	private int getTimeInit() {
		// TODO Obtain this value form the current SLAs (AgreementRegistry)
		return 0;
	}

	public Set<CounterPartyKnowledge> getKnowledge(CounterParty counterParty) {
		Set<CounterPartyKnowledge> result=new HashSet<CounterPartyKnowledge>();
		Set<FOMOffer> offers=getOffers();
		Information info;
		for(FOMOffer offer:offers)
		{
			try {
				info=FOMOfferInformationTranslator.getInformation(offer);
				result.add(new BaseCounterPartyKnowledge(info,counterParty));
			} catch (IncompatibleAttributeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		return result;
	}
}
