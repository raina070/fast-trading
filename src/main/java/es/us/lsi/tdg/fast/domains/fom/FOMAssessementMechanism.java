/**
 * 
 */
package es.us.lsi.tdg.fast.domains.fom;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Term;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.Assessment;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AssessmentMechanism;
import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class FOMAssessementMechanism implements AssessmentMechanism {
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AssessmentMechanism#assess(es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences, es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement)
	 */
	public Assessment assess(AgreementPreferences prefs, Agreement agreement) {
		// TODO COMPLETE THIS FUNCTION IMPORTANT!!!!!!!
		Assessment result=null;
		Set<Statement> requirements=prefs.getRequirements();
		Set<Statement> cons=prefs.getFeatures();
		double value=0;
		int costMax=0;
		int minTime=0;
		int maxTime=0;
		// We search the set of requirements and features for data
		// of constraints and value function:
		for(Statement requeriment:requirements)
		{
			if(requeriment instanceof Constraint)
			{	
				Constraint c=(Constraint)requeriment;
				if(c.getAttribute().getName().equals("Cost"))
				{
					// TODO Extract the cost value
					costMax=0;
				}
				if(c.getAttribute().getName().equals("Time"))
				{
					// TODO Extract the minTime and maxTime;
					minTime=0;
					maxTime=0;
				}
			}			
		}
		// We match preferences and agreement terms to assess the real value of the agreeement:
		double agreementCost=0;
		double agreementTime=0;
		Set<Term> terms=agreement.getTerms();
		for(Term term:terms)
		{
			// TODO We should test if I am not the conunterparty of the term 
			if(term.getCounterParty()==null)
			{
				Set<Constraint> constraints=term.getConstraints();
				for(Constraint constraint:constraints)
				{
					if(constraint.getAttribute().getName().equals("Time"))
					{
						;
					}
				}
			}
			
		}
		return result;
		
	}

}
