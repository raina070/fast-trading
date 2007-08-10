/**
 * 
 */
package es.us.lsi.tdg.fast.core.agreementRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class BaseAgreementRegistry implements AgreementRegistry {

	private Map<String,Set<Agreement>> registry;
	
	public BaseAgreementRegistry()
	{
		registry=new HashMap<String,Set<Agreement>>();
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.agreementRegistry.AgreementRegistry#addAgreement(java.lang.String, es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement)
	 */
	public void addAgreement(String PID, Agreement agreement) {		
		if(registry.containsKey(PID))
			registry.get(PID).add(agreement);
		else{
			Set<Agreement> agreements=new HashSet<Agreement>();
			agreements.add(agreement);
			registry.put(PID, agreements);
		}
		FAST.shell.showMessage("Reached agreement for PID "+PID+".");
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.agreementRegistry.AgreementRegistry#getAgreements(java.lang.String)
	 */
	public Set<Agreement> getAgreements(String PID) {		
		return registry.get(PID);
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.agreementRegistry.AgreementRegistry#getRegisteredIdentifiers()
	 */
	public Set<String> getRegisteredIdentifiers() {		
		return registry.keySet();
	}

}
