package es.us.lsi.tdg.fast.domains.fom.dataModel;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.information.BaseInformation;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.statement.Attribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseAttribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseSimpleConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseSortedDomainConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.ComparableValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerDomain;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.SimpleConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.SortedDomainConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;
import es.us.lsi.tdg.fast.core.dataModel.statement.StatementType;
import es.us.lsi.tdg.fast.core.dataModel.statement.Value;


public class FOMInformationTranslator {

	
	public static Information getInformation(FOMOfferInformation Offer) throws IncompatibleAttributeException{
		
		Information result = new BaseInformation();
		
		IntegerValue costValue = new IntegerValue((int)Offer.getCost());
		Attribute Cost = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
		SimpleConstraint costConstraint = new BaseSimpleConstraint((Value)costValue,Cost,StatementType.SERVICE);
		IntegerValue timeInitValue = new IntegerValue(Offer.getTimeInit());
		IntegerValue timeEndValue = new IntegerValue(Offer.getTimeEnd());
		Attribute time = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
		SortedDomainConstraint timeConstraint = new BaseSortedDomainConstraint((ComparableValue)timeInitValue,(ComparableValue)timeEndValue, time,StatementType.SERVICE);
		Set<Statement> requirements = result.getRequirements();
		requirements.add((Statement)costConstraint);
		requirements.add((Statement)timeConstraint);
		return result;
	}
	
	public static FOMOfferInformation getFOMInformation(Information info) {
		FOMOfferInformation result = new FOMOfferInformation();
		double cost=0,maxTime=0,minTime=0;
		Set<Statement> requirements=info.getRequirements();
		for(Statement requeriment:requirements)
		{
			if(requeriment instanceof Constraint)
			{	
				Constraint c=(Constraint)requeriment;
				if(c.getAttribute().getName().equals("Cost"))
				{
					if(c instanceof SimpleConstraint)
					{
						Value costValue=((SimpleConstraint)c).getValue();
						cost=((IntegerValue)costValue).getValue();
						result.setCost(cost);
					}
				}
				if(c.getAttribute().getName().equals("Time"))
				{
					if(c instanceof SortedDomainConstraint)
					{
						
						Value minTimeValue=((SortedDomainConstraint)c).getMin();
						Value maxTimeValue=((SortedDomainConstraint)c).getMax();
						minTime=((IntegerValue)minTimeValue).getValue();
						result.setTimeInit((int)minTime);
						maxTime=((IntegerValue)maxTimeValue).getValue();
						result.setTimeEnd((int)maxTime);
					
					}
				}
			}			
		}
		return result;
	}
	

	
}
