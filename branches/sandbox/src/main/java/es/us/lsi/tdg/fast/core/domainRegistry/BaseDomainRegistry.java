package es.us.lsi.tdg.fast.core.domainRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;

public class BaseDomainRegistry implements DomainRegistry {

	Map<String,AttributeCatalog> registry;
	
	public BaseDomainRegistry()
	{
		registry=new HashMap<String,AttributeCatalog>();		
	}
	
	public Set<String> getAllDomains(String domainName) {		
		return registry.keySet();
	}

	public AttributeCatalog getAttributeCatalog(String domainName) {		
		return registry.get(domainName);
	}

	public void loadDomain(String domainName) {
		// This is left blank for further implementations of domain loading 
		// using persistent or dynamic methods.
		// a base implementation using dynamic class loading could be:
		try {
			Class claseDominio=Class.forName(domainName+"AttributeCatalog");
			Object objetoDominio=claseDominio.newInstance();
			if(objetoDominio instanceof AttributeCatalog)			
				registry.put(domainName, (AttributeCatalog)objetoDominio);			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean searchDomain(String domainName) {
		return registry.containsKey(domainName);
	}

	public void unloadDomain(String domainName) {
		// This is left blank for further implementations of domain loading
		// using persistent or dynamic methods.
		registry.remove(domainName);
	}

}
