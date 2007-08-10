package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;

import es.us.lsi.tdg.fast.domains.fom.components.fomdiscovery.services.DiscoveryEP;
import es.us.lsi.tdg.fast.domains.fom.components.fomdiscovery.services.FOMDiscoveryService;
import es.us.lsi.tdg.fast.domains.fom.components.fominformation.services.FOMInformant;
import es.us.lsi.tdg.fast.domains.fom.components.fominformation.services.InformantService;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOffer;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOfferInformationTranslator;

public class FOMOfferInformationAdaptor {

	public static Set<Information>getInformation(String ep){
		
		Set<FOMOffer>FOMOffers = new HashSet<FOMOffer>();
		Set<Information> result= new HashSet<Information>();
		
		try {
		
			URL url = new URL(ep+"?wsdl");
			QName qname = new QName("http://services.FOMInformation.components.fom.domains.fast.tdg.lsi.us.es/", "InformantService");

			InformantService service;
			FOMInformant port;
			
			service = new InformantService(url,qname);
			
			port = service.getFOMInformantPort();
			
			List<String> StringOffers = port.getFOMOffers();
				
			FOMOffer o1 = new FOMOffer(
					Integer.parseInt(StringOffers.get(0)),
					Integer.parseInt(StringOffers.get(1)),
					Double.parseDouble(StringOffers.get(2))					
					); 
			FOMOffer o2 = new FOMOffer(
					Integer.parseInt(StringOffers.get(3)),
					Integer.parseInt(StringOffers.get(4)),
					Double.parseDouble(StringOffers.get(5))					
					); 
			FOMOffer o3 = new FOMOffer(
					Integer.parseInt(StringOffers.get(6)),
					Integer.parseInt(StringOffers.get(7)),
					Double.parseDouble(StringOffers.get(8))					
					); 
	
			FOMOffers.add(o1);
			FOMOffers.add(o2);
			FOMOffers.add(o3);
			
			
			
			for (FOMOffer Offer: FOMOffers){
					try {
						FAST.shell.showMessage("Obtained offers from "+ep+": "+Offer);
						result.add(FOMOfferInformationTranslator.getInformation(Offer));
					} catch (IncompatibleAttributeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	
		return result;
	}
	
}
