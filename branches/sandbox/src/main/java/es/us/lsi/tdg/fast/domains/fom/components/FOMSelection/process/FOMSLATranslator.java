package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.HashSet;

import es.us.lsi.tdg.fast.core.dataModel.statement.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.domains.fom.dataModel.*;

public class FOMSLATranslator {
	
	public static Proposal getAgreement(FOMProposal Offer){
		
		CounterParty CT = new FOMCounterParty(null,null,null,null,null);
		Set<Constraint> Constraints = new HashSet<Constraint>();
		Set<Term> myTermSet = new HashSet<Term>();
		IntegerValue costValue = new IntegerValue((int)Offer.getCost());
		Attribute costAttribute = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
		SimpleConstraint costConstraint;
		try {
			costConstraint = new BaseSimpleConstraint((Value)costValue,costAttribute,StatementType.SERVICE);
			Constraints.add(costConstraint);	
			IntegerValue timeValue = new IntegerValue(Offer.getTime());
			Attribute timeAttribute = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
			SimpleConstraint timeConstraint = new BaseSimpleConstraint((Value)timeValue,timeAttribute,StatementType.SERVICE);
			Constraints.add(timeConstraint);
			Term TermOffer = new BaseTerm(Constraints, CT);
			myTermSet.add(TermOffer);	
		} catch (IncompatibleAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Proposal SLA = new BaseProposal(myTermSet,null,ProposalPerformative.PROPOSAL);
		return SLA;
	}
	
	public static FOMProposal getFOMAgreement(Proposal SLA){
		FOMProposal result = new FOMProposal();
		double agreementCost=0,agreementTime=0;
		Set<Term> Terms= (HashSet)SLA.getTerms();
		for(Term term:Terms)
		{
			if(term.getCounterParty()==null)
			{
				Set<Constraint> constraints=(HashSet)term.getConstraints();
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
