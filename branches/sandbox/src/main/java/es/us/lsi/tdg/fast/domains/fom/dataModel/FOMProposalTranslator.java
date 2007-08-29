package es.us.lsi.tdg.fast.domains.fom.dataModel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.BaseProposal;
import es.us.lsi.tdg.fast.core.dataModel.agreement.BaseTerm;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.agreement.ProposalPerformative;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Term;
import es.us.lsi.tdg.fast.core.dataModel.statement.Attribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseAttribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseSimpleConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerDomain;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.SimpleConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.StatementType;
import es.us.lsi.tdg.fast.core.dataModel.statement.Value;

public class FOMProposalTranslator {
	
	public static Proposal getAgreement(FOMProposal FOMProposal){
		
		CounterParty counterParty = new FOMCounterParty(null,null,null,null,null);
		try {
			((FOMCounterParty)counterParty).setSelectionEndPoint(new URI(FOMProposal.getCounterPartyEndPoint()));
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Set<Constraint> Constraints = new HashSet<Constraint>();
		Set<Term> myTermSet = new HashSet<Term>();
		IntegerValue costValue = new IntegerValue((int)FOMProposal.getCost());
		Attribute costAttribute = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
		SimpleConstraint costConstraint;
		try {
			costConstraint = new BaseSimpleConstraint((Value)costValue,costAttribute,StatementType.SERVICE);
			Constraints.add(costConstraint);	
			IntegerValue timeValue = new IntegerValue(FOMProposal.getTime());
			Attribute timeAttribute = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
			SimpleConstraint timeConstraint = new BaseSimpleConstraint((Value)timeValue,timeAttribute,StatementType.SERVICE);
			Constraints.add(timeConstraint);
			Term TermOffer = new BaseTerm(Constraints, counterParty);
			myTermSet.add(TermOffer);	
		} catch (IncompatibleAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<CounterParty> counterPartySet = new HashSet<CounterParty>();
		counterPartySet.add(counterParty);
		Proposal SLA = new BaseProposal(myTermSet,counterPartySet,ProposalPerformative.PROPOSAL);
		return SLA;
	}
	
	@SuppressWarnings("unchecked")
	public static FOMProposal getFOMProposal(Proposal SLA){
		FOMProposal result = new FOMProposal();
		double agreementCost=0,agreementTime=0;
		Set<CounterParty> counterPartySet = SLA.getCounterParties();
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
		for (CounterParty counterParty:counterPartySet){
			result.setCounterPartyEndPoint(((FOMCounterParty)counterParty).getSelectionEndPoint().toString());
		}
		return result;
	}
	
	
	
}
