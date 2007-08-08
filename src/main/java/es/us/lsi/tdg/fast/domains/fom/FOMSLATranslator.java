package es.us.lsi.tdg.fast.domains.fom;

import java.util.Set;
import java.util.HashSet;

import es.us.lsi.tdg.fast.core.dataModel.statement.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;

public class FOMSLATranslator {
	
	public static BaseAgreement getAgreement(FOMAgreement Offer) throws IncompatibleAttributeException{
		BaseCounterParty CT = new BaseCounterParty();
		
		
		HashSet<Constraint> Constraints = new HashSet<Constraint>();
		HashSet<Term> myTermSet = new HashSet<Term>();
		IntegerValue costValue = new IntegerValue((int)Offer.getCost());
		BaseAttribute costAttribute = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
		BaseSimpleConstraint  costConstraint = new BaseSimpleConstraint((Value)costValue,costAttribute,StatementType.SERVICE);
		Constraints.add(costConstraint);	
		IntegerValue timeValue = new IntegerValue(Offer.getTime());
		BaseAttribute timeAttribute = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
		BaseSimpleConstraint timeConstraint = new BaseSimpleConstraint((Value)timeValue,timeAttribute,StatementType.SERVICE);
		Constraints.add(timeConstraint);
		BaseTerm TermOffer = new BaseTerm(Constraints, CT);
		myTermSet.add(TermOffer);
		
		BaseAgreement SLA = new BaseAgreement(myTermSet,null);
		return SLA;
	}
	
	public static FOMAgreement getFOMAgreement(BaseAgreement SLA){
		FOMAgreement result = new FOMAgreement();
		double agreementCost=0,agreementTime=0;
		HashSet<Term> Terms= (HashSet)SLA.getTerms();
		for(Term term:Terms)
		{
			if(term.getCounterParty()==null)
			{
				HashSet<Constraint> constraints=(HashSet)term.getConstraints();
				for(Constraint constraint:constraints)
				{
					if(constraint.getAttribute().getName().equals("Cost"))
					{
						if(constraint instanceof SimpleConstraint)
						{
							Value valor=((SimpleConstraint)constraint).getValue();
							agreementCost=((IntegerValue)valor).getValue();
							result.setCost(agreementCost);
						}
					}
					if(constraint.getAttribute().getName().equals("Time"))
					{
						if(constraint instanceof SimpleConstraint)
						{
							Value valor=((SimpleConstraint)constraint).getValue();
							agreementTime=((IntegerValue)valor).getValue();
							result.setTime((int)agreementTime);
						}
					}
				}
			}

		}
		return result;
		
	
	}
}
