package es.us.lsi.tdg.fast.core.dataModel.information;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;

public class BaseInformation implements Information {

	protected Set<Statement> features;
	protected Set<Statement> requirements;
	
	public Set<Statement> getFeatures() {
		// TODO Auto-generated method stub
		return features;
	}

	public Set<Statement> getRequirements() {
		// TODO Auto-generated method stub
		return requirements;
	}
	
	public BaseInformation(){
		features = new HashSet<Statement>();
		requirements = new HashSet<Statement>();
	}
	
	

}
