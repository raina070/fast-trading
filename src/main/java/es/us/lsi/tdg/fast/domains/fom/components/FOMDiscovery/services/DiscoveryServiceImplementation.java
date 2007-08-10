package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.services;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.services.FASTServiceImplementation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;

import es.us.lsi.tdg.fast.FAST;

@WebService(name="DiscoveryEP", serviceName="FOMDiscoveryService")
public class DiscoveryServiceImplementation implements FASTServiceImplementation{

	  private FOMDiscovery discoveryComponent;
	  
	
	  @WebMethod(operationName="advertise")
	  public void advertise(String CPID, String infoEP, String selEP, String amEP) {
		  Set<CounterParty> providers = discoveryComponent.getFOMProviders();
		  
		  
		  try {
			providers.add(new FOMCounterParty(CPID
											  	,new URI("")
									  			,new URI(infoEP)
									  			,new URI(selEP)
									  			,new URI(amEP)));
			
			FAST.shell.showMessage("New provider "+CPID+" at "+infoEP);
			
		} catch (URISyntaxException e) {
		}
	  }
	  
	  public void setComponent(Component component) {
			discoveryComponent = (FOMDiscovery) component;
	  }

} 