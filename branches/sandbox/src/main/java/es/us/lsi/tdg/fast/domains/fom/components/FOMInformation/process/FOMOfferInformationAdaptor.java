package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import java.util.HashSet;
import java.util.Set;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;

import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process.FOMOffer;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process.FOMOfferInformationTranslator;

public class FOMOfferInformationAdaptor {

	public static Set<Information>getInformation(String ep){
		FOMOfferInquirer Inquirer=new FOMOfferInquirer();
		
		Set<FOMOffer>FOMOffers = Inquirer.newCP(ep);
				
		Set<Information> result= new HashSet<Information>();

		for (FOMOffer Offer: FOMOffers){
				try {
					result.add(FOMOfferInformationTranslator.getInformation(Offer));
				} catch (IncompatibleAttributeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	
}
