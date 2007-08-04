package es.us.lsi.tdg.fast.core.domainRegistry;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;

public interface DomainRegistry {

	// ** Domain Management Methods ** 
	public void loadDomain(String domainName);
	public void unloadDomain(String domainName);

	
	// ** Domain Usage Methods ** 
	
	/*
	 * Returns weather domain have been loaded or not. 
	 */
	public boolean searchDomain(String domainName);

	/*
	 * Returns a set of domains matching the string passed as parameter 
	 */
	public Set<String> getAllDomains(String domainName);
	
	public AttributeCatalog getAttributeCatalog(String domainName);
	
	public DomainManifest getManifest(String domainName);
	
}
