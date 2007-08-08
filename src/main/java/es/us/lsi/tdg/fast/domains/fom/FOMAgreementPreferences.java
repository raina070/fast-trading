package es.us.lsi.tdg.fast.domains.fom;

import java.util.HashSet;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.*;
import es.us.lsi.tdg.fast.core.dataModel.statement.*;

public class FOMAgreementPreferences {

	protected int minTime,maxTime;
	protected double maxCost,minCost;
	protected AssessmentMechanism assessmentMechanism;
	
	public FOMAgreementPreferences(AgreementPreferences agreePrefs){
		
		this.assessmentMechanism = agreePrefs.getAssessmentMechanism();
		HashSet<Constraint> constraints= (HashSet)agreePrefs.getRequirements();
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

	public BaseAgreementPreferences toBaseAgreementPreferences() throws IncompatibleAttributeException{
		
		BaseAgreementPreferences result = new BaseAgreementPreferences(this.assessmentMechanism);
		IntegerValue costValue 		= new IntegerValue((int)this.maxCost);
		IntegerValue costValueMin 	= new IntegerValue(-100);
		BaseAttribute Cost = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
		BaseSortedDomainConstraint c = new BaseSortedDomainConstraint((ComparableValue)costValueMin,(ComparableValue)costValue,Cost,StatementType.SERVICE);
		IntegerValue timeInitValue = new IntegerValue(this.minTime);
		IntegerValue timeEndValue = new IntegerValue(this.maxTime);
		BaseAttribute time = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
		BaseSortedDomainConstraint tOffer = new BaseSortedDomainConstraint((ComparableValue)timeInitValue,(ComparableValue)timeEndValue, time,StatementType.SERVICE);
		result.addRequirement((Statement)c);
		result.addRequirement((Statement)tOffer);
		return result;
	}
}
