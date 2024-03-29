package es.us.lsi.tdg.fast.core.domainRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;

public class BaseDomainRegistry implements DomainRegistry {

	Map<String,DomainManifest> registry;
	
	public BaseDomainRegistry()
	{
		registry=new HashMap<String,DomainManifest>();		
	}
	
	public Set<String> getAllDomains(String domainName) {		
		return registry.keySet();
	}

	public AttributeCatalog getAttributeCatalog(String domainName) {		
		DomainManifest manifest = registry.get(domainName);
		return manifest.getAttributeCatalog();
	}

	public DomainManifest getManifest(String domainName) {		
		DomainManifest manifest = registry.get(domainName);
		return manifest;
	}

	@SuppressWarnings("unchecked")
	public void loadDomain(String domainName) {
		// This is left blank for further implementations of domain loading 
		// using persistent or dynamic methods.
		// a base implementation using dynamic class loading could be:
		try {
			// TODO detect that the class name is absolute and not relative (search for ".")
			
			String className="es.us.lsi.tdg.fast.domains."+domainName.toLowerCase()+".Manifest";
			
			Class<DomainManifest> domainClass= (Class<DomainManifest>) Class.forName(className);
			
			Object domainObject=domainClass.newInstance();
			if(domainObject instanceof DomainManifest)			
				registry.put(domainName, (DomainManifest) domainObject);
			else throw new WrongManifestException();

		} catch (WrongManifestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
