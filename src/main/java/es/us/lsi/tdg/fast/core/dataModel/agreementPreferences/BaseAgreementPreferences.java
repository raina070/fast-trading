/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.agreementPreferences;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class BaseAgreementPreferences implements AgreementPreferences {

	
	protected Set<Statement> features;
	protected Set<Statement> requirements;
	protected AssessmentMechanism assessmentMechanism;
	
	
	public BaseAgreementPreferences(AssessmentMechanism assessementMechanism)
	{
		features=new HashSet<Statement>();
		requirements=new HashSet<Statement>();
		this.assessmentMechanism = assessementMechanism;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.AgreementPreferences#getFeatures()
	 */
	public Set<Statement> getFeatures() {
		return features;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.AgreementPreferences#getRequirements()
	 */
	public Set<Statement> getRequirements() {
		return requirements;
	}
	
	public Assessment assess(Agreement agreement)
	{
		return	getAssessmentMechanism().assess(this,agreement);
	}

	public AssessmentMechanism getAssessmentMechanism() {
		return assessmentMechanism;
	}

	public String toString()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("Features:\n");
		for(Statement s:features)
			sb.append(s.toString()+"\n");
		sb.append("Requirements:\n");
		for(Statement s:requirements)
			sb.append(s.toString()+"\n");		
		return sb.toString();
	}
}
