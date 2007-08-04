package es.us.lsi.tdg.fast.domains.fom;

import java.util.Set;

import es.us.lsi.tdg.fast.components.Component;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;

public class Manifest implements
		es.us.lsi.tdg.fast.core.domainRegistry.DomainManifest {

	protected String name = "FOM";
	protected AttributeCatalog attributeCatalog =  new FOMAttributeCatalog(); 
	protected Set<Component> components; 
	
	public AttributeCatalog getAttributeCatalog() {
		return attributeCatalog;		
	}
	
	public Set<Component> getComponents() {
		components.add(new FOMInformationComponent());
		return components;
	}

	public String getName() {
		return name;
	}

}
