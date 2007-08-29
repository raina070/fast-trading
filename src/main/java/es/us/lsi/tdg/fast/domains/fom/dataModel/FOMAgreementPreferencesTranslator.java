package es.us.lsi.tdg.fast.domains.fom.dataModel;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.SortedDomainConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;
import es.us.lsi.tdg.fast.core.dataModel.statement.Value;

public class FOMAgreementPreferencesTranslator {
	public static FOMAgreementPreferences getFOMAgreementPreferences(AgreementPreferences SLA){
		FOMAgreementPreferences result = new FOMAgreementPreferences();
		
		double agreementCost=0,agreementMaxTime=-1,agreementMinTime=-1;
		Set<Statement> Requirements= SLA.getRequirements();
		for(Statement requirement:Requirements)
		{
			if (requirement instanceof SortedDomainConstraint){
				
				if(((SortedDomainConstraint)requirement).getAttribute().getName()=="Cost"){
					//TODO store the minimum value
					Value valor=((SortedDomainConstraint)requirement).getMax();
					agreementCost=((IntegerValue)valor).getValue();
					result.setCost(agreementCost);
					
				}else if (((SortedDomainConstraint)requirement).getAttribute().getName()=="InvocationMinDate"){
						/*
						 * TODO 
						 */
				}else if (((SortedDomainConstraint)requirement).getAttribute().getName()=="Time"){
						Value maxTimeValue=((SortedDomainConstraint)requirement).getMax();
						Value minTimeValue=((SortedDomainConstraint)requirement).getMin();
						agreementMaxTime=((IntegerValue)maxTimeValue).getValue();
						result.setTimeEnd((int)agreementMaxTime);
						agreementMinTime=((IntegerValue)minTimeValue).getValue();
						result.setTimeInit((int)agreementMinTime);						 	
				}
			}
		}
		return result;
	}
}
