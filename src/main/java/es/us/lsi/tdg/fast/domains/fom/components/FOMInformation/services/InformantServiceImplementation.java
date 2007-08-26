package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import es.us.lsi.tdg.fast.core.component.Component;

import es.us.lsi.tdg.fast.core.services.FASTServiceImplementation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOfferInformation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process.FOMInformant;
import es.us.lsi.tdg.fast.FAST;


@WebService(name="FOMInformant", serviceName="InformantService")
public class InformantServiceImplementation implements FASTServiceImplementation{
	  
	private FOMInformation informationComponent;
	
	public void setComponent(Component component) {
		informationComponent = (FOMInformation) component;
	}
	
	@WebMethod(operationName="getFOMOffers")
	public List<String> getFOMOffers(){
		FOMInformant FOMInformant = new FOMInformant();
		Set<FOMOfferInformation> FOMOffers = FOMInformant.getOffers(informationComponent.getTradingProcess().getPID());
		List<String> Offers = new LinkedList<String>();
		for (FOMOfferInformation FOMInformation:FOMOffers){
			
			Offers.add(Integer.toString(FOMInformation.getTimeInit()));
			Offers.add(Integer.toString(FOMInformation.getTimeEnd()));
			Offers.add(Double.toString(FOMInformation.getCost()));
			FAST.shell.showMessage("Sending Offer "+FOMInformation);	
			//Offers.add("0");
			//Offers.add("20");
			//Offers.add("50");	
		}
		
		/**
		Offers.add("21");
		Offers.add("40");
		Offers.add("20");
		Offers.add("41");
		Offers.add("60");
		Offers.add("10");
		*/
		return Offers;
	}
	
	
	
}
