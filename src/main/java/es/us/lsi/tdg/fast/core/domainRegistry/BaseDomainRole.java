package es.us.lsi.tdg.fast.core.domainRegistry;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;

public class BaseDomainRole implements DomainRole {
	
	private String name;
	protected DomainManifest domainManifest;
	
	public BaseDomainRole(String name, DomainManifest domainManifes)
	{
		this.name=name;
		this.domainManifest=domainManifes;
	}
	
	
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj instanceof BaseDomainRole)
			result= this.name.equals(((BaseDomainRole)obj).name);		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.domains.DomainRole#validate(es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences)
	 */
	public boolean validate(AgreementPreferences preferences)
	{
		return true;
		
	}


	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.domains.DomainRole#getName()
	 */
	public String getName() {
		return name;
	}


	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.domains.DomainRole#getDomainManifest()
	 */
	public DomainManifest getDomainManifest() {
		return domainManifest;
	}
	
	public String toString(){
		return getName();
		
	}
	
}
