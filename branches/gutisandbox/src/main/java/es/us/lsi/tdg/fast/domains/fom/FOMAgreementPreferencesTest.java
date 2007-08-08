package es.us.lsi.tdg.fast.domains.fom;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AssessmentMechanism;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.BaseAgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseAttribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseSortedDomainConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.ComparableValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerDomain;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.SortedDomainConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;
import es.us.lsi.tdg.fast.core.dataModel.statement.StatementType;
import es.us.lsi.tdg.fast.core.dataModel.statement.Value;

public class FOMAgreementPreferencesTest {

	protected int minTime,maxTime;
	protected double maxCost,minCost;
	protected AssessmentMechanism assessmentMechanism;
	
	public FOMAgreementPreferencesTest(AgreementPreferences agreePrefs){
		
		this.assessmentMechanism = agreePrefs.getAssessmentMechanism();
		Set<Constraint> constraints= (HashSet)agreePrefs.getRequirements();
		for(Constraint constraint:constraints)
		{
			if(constraint.getAttribute().getName().equals("Cost"))
			{
				if(constraint instanceof SortedDomainConstraint)
				{
					
					Value minCostValue=((SortedDomainConstraint)constraint).getMin();
					Value maxCostValue=((SortedDomainConstraint)constraint).getMax();
					this.minCost = ((IntegerValue)minCostValue).getValue();
					this.maxCost = ((IntegerValue)maxCostValue).getValue();
				}
			}
			if(constraint.getAttribute().getName().equals("Time"))
			{
				if(constraint instanceof SortedDomainConstraint)
				{
					Value minTimeValue=((SortedDomainConstraint)constraint).getMin();
					Value maxTimeValue=((SortedDomainConstraint)constraint).getMax();
					this.minTime = ((IntegerValue)minTimeValue).getValue();
					this.maxTime = ((IntegerValue)maxTimeValue).getValue();
				}
			}
		}		
	}

	public AgreementPreferences toAgreementPreferences() throws IncompatibleAttributeException{
		
		AgreementPreferences result = new BaseAgreementPreferences(this.assessmentMechanism);
		IntegerValue costValue 		= new IntegerValue((int)this.maxCost);
		IntegerValue costValueMin 	= new IntegerValue(-100);
		BaseAttribute Cost = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
		SortedDomainConstraint costConstraint = new BaseSortedDomainConstraint((ComparableValue)costValueMin,(ComparableValue)costValue,Cost,StatementType.SERVICE);
		IntegerValue timeInitValue = new IntegerValue(this.minTime);
		IntegerValue timeEndValue = new IntegerValue(this.maxTime);
		BaseAttribute time = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
		SortedDomainConstraint timeConstraint= new BaseSortedDomainConstraint((ComparableValue)timeInitValue,(ComparableValue)timeEndValue, time,StatementType.SERVICE);
		Set<Statement> requirements = result.getRequirements();
		requirements.add((Statement)costConstraint);
		requirements.add((Statement)timeConstraint);
		return result;
	}
	
}
