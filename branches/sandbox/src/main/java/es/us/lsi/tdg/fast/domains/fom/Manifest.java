package es.us.lsi.tdg.fast.domains.fom;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AssessmentMechanism;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMTrading.FOMTrading;

public class Manifest implements
		es.us.lsi.tdg.fast.core.domainRegistry.DomainManifest {

	protected String name = "FOM";
	protected AttributeCatalog attributeCatalog =  new FOMAttributeCatalog(); 
	protected Set<Component> components;
	protected AssessmentMechanism assessmentMechanism =new FOMAssessmentMechanism();
	protected Set<String> domainRoles;
	
	
	public Manifest() {
		domainRoles = new HashSet<String>();
		domainRoles.add("customer");
		domainRoles.add("provider");
		
		components = new HashSet<Component>();
		components.add(new FOMDiscovery());
		components.add(new FOMInformation());
		components.add(new FOMTrading());
		
	}

	public AttributeCatalog getAttributeCatalog() {
		return attributeCatalog;		
	}
	
	public Set<Component> getComponents() {
		return components;
	}

	public String getName() {
		return name;
	}

	public AssessmentMechanism getAssessmentMechanism() {
		return assessmentMechanism;
	}

	public Set<String> getDomainRoles() {
		return domainRoles;
	}
}
