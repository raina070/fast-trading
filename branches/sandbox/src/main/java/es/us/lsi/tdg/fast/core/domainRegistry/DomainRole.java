package es.us.lsi.tdg.fast.core.domainRegistry;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.domains.fom.Manifest;

public interface DomainRole {

	public abstract boolean validate(AgreementPreferences preferences);

	public abstract String getName();

	public String toString();
	
	public abstract DomainManifest getDomainManifest();

}