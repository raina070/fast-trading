package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.information.BaseCounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.dataModel.information.Information;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.SortedDomainConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.SimpleConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;
import es.us.lsi.tdg.fast.core.dataModel.statement.Value;
import es.us.lsi.tdg.fast.core.roles.information.Informant;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOfferInformation;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMInformationTranslator;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalTranslator;
import es.us.lsi.tdg.fast.FAST;



public class FOMInformant implements Informant{
	private double 	c1=50,c2=20,c3=10;	
	
	public Set<FOMOfferInformation> getOffers(String PID)
	{
		Set<FOMOfferInformation> result=new HashSet<FOMOfferInformation>();
		int tInit 	= getTimeInit();
		int tFull = Integer.parseInt(FAST.properties.get("fullTime"));
		int tReal = tFull - tInit;
		
		Set<Statement> Requirements= FAST.preferenceRegistry.getPreferences(PID).getRequirements();
		double factorAux = 1;
		for(Statement requirement:Requirements)
		{
			if (requirement instanceof SimpleConstraint){
				
				if(((SimpleConstraint)requirement).getAttribute().getName()=="CostFactor"){
					//TODO store the minimum value
					Value valor=((SimpleConstraint)requirement).getValue();
					factorAux=((IntegerValue)valor).getValue();
				}
			}
		}
					
		//double factoraux = FAST.preferenceRegistry.getPreferences("1").getRequirements(). 
		double factor = factorAux*(double)tFull/tReal;
		
		int t1 = tReal/3;
		int t2 = (2*tReal/3);
				
		FOMOfferInformation Offer1 = new FOMOfferInformation(0,t1, factor*this.c1);
		FOMOfferInformation Offer2 = new FOMOfferInformation(t1+1,t2, factor*this.c2);
		FOMOfferInformation Offer3 = new FOMOfferInformation(t2+1,tFull-tInit, factor*this.c3);
	
		result.add(Offer1);
		result.add(Offer2);
		result.add(Offer3);

		return result;
	}




	private static int getTimeInit() {
		// TODO Obtain this value form the current SLAs (AgreementRegistry)
		int timeInit=0;
		Set<String> PIDSet = FAST.agreementRegistry.getRegisteredIdentifiers();
		for (String pid:PIDSet){
			Set <Agreement> AgreementSet = FAST.agreementRegistry.getAgreements(pid);
			for (Agreement Agreement:AgreementSet){
				int timeAux = (FOMProposalTranslator.getFOMProposal((Proposal)Agreement)).getTime();
				timeInit+=timeAux;
			}
		}
		return timeInit;
	}

	public Set<CounterPartyKnowledge> getKnowledge(CounterParty counterParty) {
		Set<CounterPartyKnowledge> result=new HashSet<CounterPartyKnowledge>();
		Set<FOMOfferInformation> offers=getOffers(((FOMCounterParty)counterParty).getCPID());
		Information info;
		for(FOMOfferInformation offer:offers)
		{
			try {
				info=FOMInformationTranslator.getInformation(offer);
				result.add(new BaseCounterPartyKnowledge(info,counterParty));
			} catch (IncompatibleAttributeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		return result;
	}
}
