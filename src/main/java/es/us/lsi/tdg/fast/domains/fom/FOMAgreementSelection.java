package es.us.lsi.tdg.fast.domains.fom;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseAttribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseSimpleConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseSortedDomainConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.ComparableValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerDomain;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;
import es.us.lsi.tdg.fast.core.dataModel.statement.StatementType;
import es.us.lsi.tdg.fast.core.dataModel.statement.Value;


import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class FOMAgreementSelection {

	public void FOMAgreementSort(Set<BaseAgreement> myAgreements,BaseAgreementPreferences myAgreementPreferences){
		
		Set<FOMAgreementOffer> myFOMOffer = new TreeSet<FOMAgreementOffer>();
		
		
		Set<Assessment> myAssess = new TreeSet<Assessment>();
		for (BaseAgreement Agree:myAgreements){
			
			Assessment Test = null;
			Test = myAgreementPreferences.assess(Agree);
			myAssess.add(Test);
		}
		
		for (BaseAgreement Agree:myAgreements){
			
			try {
				myFOMOffer.add(new FOMAgreementOffer(Agree, myAgreementPreferences));
			} catch (IncompatibleAttributeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		//System.out.println(myAssess.size());
		for (Assessment Assess:myAssess){
			//System.out.println(Assess);
		}
		
		for (FOMAgreementOffer Offer:myFOMOffer){
			//System.out.println(Offer);
		}
		
	

		
		
	}
	
	
	
	
}
