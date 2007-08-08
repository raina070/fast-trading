package es.us.lsi.tdg.fast.domains.fom;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.information.*;
import es.us.lsi.tdg.fast.core.dataModel.statement.*;


public class FOMOfferInformationTranslator {

	
	public static BaseInformation getInformation(FOMOffer Offer) throws IncompatibleAttributeException{
		
		BaseInformation result = new BaseInformation();
		
		IntegerValue costValue = new IntegerValue((int)Offer.getCost());
		BaseAttribute Cost = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
		BaseSimpleConstraint c = new BaseSimpleConstraint((Value)costValue,Cost,StatementType.SERVICE);
		IntegerValue timeInitValue = new IntegerValue(Offer.getTimeInit());
		IntegerValue timeEndValue = new IntegerValue(Offer.getTimeEnd());
		BaseAttribute time = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
		BaseSortedDomainConstraint tOffer = new BaseSortedDomainConstraint((ComparableValue)timeInitValue,(ComparableValue)timeEndValue, time,StatementType.SERVICE);
		result.addRequirement((Statement)c);
		result.addRequirement((Statement)tOffer);
		
			
		return result;
	}
	
	public static FOMOffer getFOMOffer(BaseInformation Offer){
		FOMOffer result = new FOMOffer();
		double cost=0,maxTime=0,minTime=0;
		Set<Statement> requirements=Offer.getRequirements();
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
