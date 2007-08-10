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


@WebService(name="FOMInformant", serviceName="InformantService")
public class InformantServiceImplementation implements FASTServiceImplementation{
	  
	private FOMInformation informationComponent;
	
	public void setComponent(Component component) {
		informationComponent = (FOMInformation) component;
	}
	
	@WebMethod(operationName="getFOMOffers")
	public List<String> getFOMOffers(){
		List<String> Offers = new LinkedList<String>();
		
		Offers.add("0");
		Offers.add("20");
		Offers.add("50");
		Offers.add("21");
		Offers.add("40");
		Offers.add("20");
		Offers.add("41");
		Offers.add("60");
		Offers.add("10");
		
		return Offers;
	}
	
	
	
}
