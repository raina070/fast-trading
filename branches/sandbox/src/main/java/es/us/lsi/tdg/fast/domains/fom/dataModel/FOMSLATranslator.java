package es.us.lsi.tdg.fast.domains.fom.dataModel;

import java.util.Set;
import java.util.HashSet;

import es.us.lsi.tdg.fast.core.dataModel.statement.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.domains.fom.dataModel.*;
import es.us.lsi.tdg.fast.FAST;

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
		Proposal SLA = new BaseProposal(myTermSet,new HashSet<CounterParty>(),ProposalPerformative.PROPOSAL);
		return SLA;
	}
	
	public static FOMProposal getFOMAgreement(Proposal SLA){
		FOMProposal result = new FOMProposal();
		double agreementCost=0,agreementTime=0;
		Set<Term> Terms= (HashSet)SLA.getTerms();
		for(Term term:Terms)
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
		return result;
	}
	
	public static FOMOffer getFOMOfferPreference(AgreementPreferences SLA){
		FOMOffer result = new FOMOffer();
		
		double agreementCost=0,agreementMaxTime=-1,agreementMinTime=-1;
		Set<Statement> Requirements= SLA.getRequirements();
		FAST.shell.showMessage("Numero de Preferencias de Acuerdo");
		FAST.shell.showMessage(Integer.toString(Requirements.size()));
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
