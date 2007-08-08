package es.us.lsi.tdg.fast.core.domainRegistry;

import java.util.Set;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AssessmentMechanism;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;

public interface DomainManifest {
	
		public String getName();

		public AttributeCatalog getAttributeCatalog();
		
		public Set<Component> getComponents();
		
		public AssessmentMechanism getAssessmentMechanism();
		
		public Set<DomainRole> getDomainRoles();
}

