/**
 * 
 */
package es.us.lsi.tdg.fast.domains.fom;

import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Term;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.Assessment;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AssessmentMechanism;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.BaseAssessment;
import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.SimpleConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.SortedDomainConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;
import es.us.lsi.tdg.fast.core.dataModel.statement.Value;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class FOMAssessmentMechanism implements AssessmentMechanism {
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AssessmentMechanism#assess(es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences, es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement)
	 */
	public Assessment assess(AgreementPreferences prefs, Agreement agreement) {
		Assessment result=null;
		Set<Statement> requirements=prefs.getRequirements();
		@SuppressWarnings("unused")
		Set<Statement> features=prefs.getFeatures();
		double value=0;
		double costMax=0;
		double minTime=0;
		double maxTime=0;
		// We search the set of requirements and features for data
		// of constraints and value function:
		for(Statement requeriment:requirements)
		{
			if(requeriment instanceof Constraint)
			{	
				Constraint c=(Constraint)requeriment;
				if(c.getAttribute().getName().equals("Cost"))
				{
					if(c instanceof SortedDomainConstraint)
					{
						Value maxCostValue=((SortedDomainConstraint)c).getMax();
						costMax=((IntegerValue)maxCostValue).getValue();
					}
				}
				if(c.getAttribute().getName().equals("Time"))
				{
					if(c instanceof SortedDomainConstraint)
					{
					
						Value minTimeValue=((SortedDomainConstraint)c).getMin();
						Value maxTimeValue=((SortedDomainConstraint)c).getMax();
						minTime=((IntegerValue)minTimeValue).getValue();
						maxTime=((IntegerValue)maxTimeValue).getValue();

					}
				}
			}			
		}
		// We match preferences and agreement terms to assess the real value of the agreeement:
		double agreementCost=0;
		double agreementTime=0;
		Set<Term> terms=agreement.getTerms();
		for(Term term:terms)
		{
			// TODO We should test if I am not the counterparty of the term 
			//if(term.getCounterParty()==null)
			//{
				Set<Constraint> constraints=term.getConstraints();
				for(Constraint constraint:constraints)
				{
					if(constraint.getAttribute().getName().equals("Cost"))
					{
						if(constraint instanceof SimpleConstraint)
						{
							Value valor=((SimpleConstraint)constraint).getValue();
							agreementCost=((IntegerValue)valor).getValue();
						}
					}
					if(constraint.getAttribute().getName().equals("Time"))
					{
						if(constraint instanceof SimpleConstraint)
						{
							Value valor=((SimpleConstraint)constraint).getValue();
							agreementTime=((IntegerValue)valor).getValue();
						}
					}
				}
			//}
			
		}
		FAST.shell.showMessage("Preferences: c<"+costMax+", t=["+minTime+","+maxTime+"].");
		FAST.shell.showMessage("AgreementOffer: c="+agreementCost+", t="+agreementTime+".");
		if(agreementCost<costMax && agreementTime>=minTime && agreementTime<=maxTime )
		{	
			value=(costMax/agreementCost)*(maxTime+1)+agreementTime;
		}else
			value=-1;
		result=new BaseAssessment(value);
		return result;
		
	}

}
