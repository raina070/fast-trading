package es.us.lsi.tdg.fast.core.domainRegistry;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AssessmentMechanism;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;
import es.us.lsi.tdg.fast.components.Component;

public interface DomainManifest {
	
		public String getName();

		public AttributeCatalog getAttributeCatalog();
		
		public Set<Component> getComponents();
		
		public AssessmentMechanism getAssessMentMechanism();
		
		public Set<String> getDomainRoles();
}

